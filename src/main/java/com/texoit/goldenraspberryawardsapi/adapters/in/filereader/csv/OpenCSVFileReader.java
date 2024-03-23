package com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.processor.RowProcessor;
import com.opencsv.validators.RowFunctionValidator;
import com.opencsv.validators.RowValidator;
import com.texoit.goldenraspberryawardsapi.adapters.in.filereader.csv.processor.BlankColumnsBecomeNull;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidBeanFromCsvException;
import com.texoit.goldenraspberryawardsapi.application.ports.in.csv.CSVFileReaderInputPort;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class OpenCSVFileReader implements CSVFileReaderInputPort {

    private static final RowProcessor ROW_PROCESSOR = new BlankColumnsBecomeNull();
    private static final RowValidator FIVE_COLUMNS_ROW_VALIDATOR = new RowFunctionValidator(
            (x) -> {
                return x.length == 5;
            },
            "Row must have five columns."
    );

    @Override
    public List<String[]> read(Path filePath, CSVFileReaderConfig configuration) throws IOException, InvalidBeanFromCsvException {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(configuration.getSeparator())
                .build();

        CSVReader reader = new CSVReaderBuilder(Files.newBufferedReader(filePath))
                .withSkipLines(configuration.getLinesToSkip())
                .withCSVParser(parser)
                .withRowValidator(FIVE_COLUMNS_ROW_VALIDATOR)
                .withRowProcessor(ROW_PROCESSOR)
                .build();

        try {
            return reader.readAll();
        } catch (CsvException e) {
            throw new InvalidBeanFromCsvException(e.getMessage());
        }
    }

}