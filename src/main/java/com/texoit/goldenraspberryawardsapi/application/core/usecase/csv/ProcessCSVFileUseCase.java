package com.texoit.goldenraspberryawardsapi.application.core.usecase.csv;

import com.opencsv.exceptions.CsvException;
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
import java.util.Set;
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
    public void start(Path filePath) throws IOException, CsvException {
        List<String[]> lines = csvFileReaderInputPort.read(filePath);

        var movies = processLines(lines);

        movies.forEach(movie -> {
            Set<Studio> studios = movie.getStudios()
                    .stream()
                    .map(findStudioByNameOrSaveInputPort::execute)
                    .collect(Collectors.toSet());
            Set<Producer> producers = movie.getProducers()
                    .stream()
                    .map(findProducerByNameOrSaveInputPort::execute)
                    .collect(Collectors.toSet());
            insertMovieInputPort.insert(new Movie(movie.getYear(), movie.getTitle(), studios, producers, movie.getWinner()));
        });
    }

    private Set<Movie> processLines(List<String[]> lines) {
        return lines.stream()
                .map(this::createMovieFromLine)
                .collect(Collectors.toSet());
    }

    private Movie createMovieFromLine(String[] line) {
        var csvLineColumns = new CsvLineColumns(line);

        Set<Studio> studios = Arrays.stream(csvLineColumns.studios)
                .map(Studio::new)
                .collect(Collectors.toSet());

        Set<Producer> producers = Arrays.stream(csvLineColumns.producers)
                .map(Producer::new)
                .collect(Collectors.toSet());

        return new Movie(
                csvLineColumns.year,
                csvLineColumns.title,
                studios,
                producers,
                csvLineColumns.winner
        );
    }

    record CsvLineColumns(Integer year, String title, String[] studios, String[] producers, Boolean winner) {
        public CsvLineColumns(String[] line) {
            this(
                Integer.parseInt(line[0]),
                line[1],
                line[2].split(", "),
                line[3].split(", | and "),
                line[4].equalsIgnoreCase("yes")
            );
        }
    }
}
