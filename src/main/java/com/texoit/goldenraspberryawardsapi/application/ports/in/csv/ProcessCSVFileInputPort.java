package com.texoit.goldenraspberryawardsapi.application.ports.in.csv;

import com.texoit.goldenraspberryawardsapi.application.core.config.csv.CSVFileReaderConfig;
import com.texoit.goldenraspberryawardsapi.application.core.config.csv.InvalidBeanFromCsvException;

import java.io.IOException;
import java.nio.file.Path;

public interface ProcessCSVFileInputPort {

    void start(Path filePath, CSVFileReaderConfig configuration) throws IOException, InvalidBeanFromCsvException;

}
