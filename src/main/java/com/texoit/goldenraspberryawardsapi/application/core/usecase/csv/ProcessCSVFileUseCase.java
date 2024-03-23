package com.texoit.goldenraspberryawardsapi.application.core.usecase.csv;

import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidBeanFromCsvException;
import com.texoit.goldenraspberryawardsapi.application.core.domain.movie.Movie;
import com.texoit.goldenraspberryawardsapi.application.core.domain.producer.Producer;
import com.texoit.goldenraspberryawardsapi.application.core.domain.studio.Studio;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.CSVFileReaderInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.ProcessCSVFileInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.movie.InsertMovieInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.producer.FindProducerByNameOrSaveInputPort;
import com.texoit.goldenraspberryawardsapi.application.ports.in.studio.FindStudioByNameOrSaveInputPort;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProcessCSVFileUseCase implements ProcessCSVFileInputPort {

    private final CSVFileReaderInputPort csvFileReaderInputPort;
    private final FindStudioByNameOrSaveInputPort findStudioByNameOrSaveInputPort;
    private final FindProducerByNameOrSaveInputPort findProducerByNameOrSaveInputPort;
    private final InsertMovieInputPort insertMovieInputPort;

    public ProcessCSVFileUseCase(CSVFileReaderInputPort csvFileReaderInputPort, FindStudioByNameOrSaveInputPort findStudioByNameOrSaveInputPort, FindProducerByNameOrSaveInputPort findProducerByNameOrSaveInputPort, InsertMovieInputPort insertMovieInputPort) {
        this.csvFileReaderInputPort = csvFileReaderInputPort;
        this.findStudioByNameOrSaveInputPort = findStudioByNameOrSaveInputPort;
        this.findProducerByNameOrSaveInputPort = findProducerByNameOrSaveInputPort;
        this.insertMovieInputPort = insertMovieInputPort;
    }

    @Override
    public void start(Path filePath, CSVFileReaderConfig configuration) throws IOException, InvalidBeanFromCsvException {
        List<String[]> lines = csvFileReaderInputPort.read(filePath, configuration);

        var movies = processLines(csvFileReaderInputPort.read(filePath, configuration));

        movies.forEach(movie -> {
            List<Studio> studios = movie.getStudios()
                    .stream()
                    .map(findStudioByNameOrSaveInputPort::execute)
                    .collect(Collectors.toList());
            List<Producer> producers = movie.getProducers()
                    .stream()
                    .map(findProducerByNameOrSaveInputPort::execute)
                    .collect(Collectors.toList());
            insertMovieInputPort.insert(new Movie(movie.getYear(), movie.getTitle(), studios, producers, movie.getWinner()));
        });
    }

    private List<Movie> processLines(List<String[]> lines) {
        return lines.stream()
                .map(this::createMovieFromLine)
                .collect(Collectors.toList());
    }

    private Movie createMovieFromLine(String[] line) {
        var csvLineColumns = new CsvLineColumns(line);

        List<Studio> studios = Arrays.stream(csvLineColumns.studios)
                .map(Studio::new)
                .collect(Collectors.toList());

        List<Producer> producers = Arrays.stream(csvLineColumns.producers)
                .map(Producer::new)
                .collect(Collectors.toList());

        return createMovie(csvLineColumns, studios, producers);
    }

    private Movie createMovie(CsvLineColumns csvLineColumns, List<Studio> studios, List<Producer> producers) throws InvalidBeanFromCsvException {
        validateMovieAttributes(csvLineColumns, studios, producers);

        return new Movie(
                csvLineColumns.year,
                csvLineColumns.title,
                studios,
                producers,
                csvLineColumns.winner
        );
    }

    private void validateMovieAttributes(CsvLineColumns csvLineColumns, List<Studio> studios, List<Producer> producers) throws InvalidBeanFromCsvException {
        if (csvLineColumns.year == null ||
                csvLineColumns.title == null ||
                csvLineColumns.title.isBlank() ||
                studios == null ||
                studios.isEmpty() ||
                studios.stream().anyMatch(studio -> studio.getName() == null || studio.getName().isBlank()) ||
                producers == null ||
                producers.isEmpty() ||
                producers.stream().anyMatch(producer -> producer.getName() == null || producer.getName().isBlank())) {
            throw new InvalidBeanFromCsvException("One or more required attributes in the line " + csvLineColumns + " are not present.");
        }
    }

    record CsvLineColumns(Integer year, String title, String[] studios, String[] producers, Boolean winner) {
        public CsvLineColumns(String[] line) {
            this(
                Optional.ofNullable(line[0])
                        .flatMap(str -> Optional.of(Integer.parseInt(str)))
                        .orElseThrow(null),
                line[1],
                Optional.ofNullable(line[2])
                        .map(str -> str.split(", ")).orElse(null),
                Optional.ofNullable(line[3])
                        .map(str -> str.split(", | and ")).orElse(null),
                Optional.ofNullable(line[4])
                        .map(str -> str.equalsIgnoreCase("yes"))
                        .orElse(false)
            );
        }
    }
}
