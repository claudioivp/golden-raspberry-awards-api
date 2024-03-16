package com.texoit.goldenraspberryawardsapi.application.ports.in.csv;

import com.opencsv.exceptions.CsvException;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface CSVFileReaderInputPort {

    List<String[]> read(Path filePath, CSVFileReaderConfig configuration) throws IOException, CsvException;

}
