package com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.CSVFileReaderInputPort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class OpenCSVFileReader implements CSVFileReaderInputPort {

    @Override
    public List<String[]> read(Path filePath, CSVFileReaderConfig configuration) throws IOException, CsvException {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(configuration.getSeparator())
                .build();

        CSVReader reader = new CSVReaderBuilder(Files.newBufferedReader(filePath))
                .withSkipLines(configuration.getLinesToSkip())
                .withCSVParser(parser)
                .build();

        return reader.readAll();
    }

}