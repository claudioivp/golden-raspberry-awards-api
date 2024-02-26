package com.texoit.goldenraspberryawardsapi.application.ports.in.csv;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.nio.file.Path;

public interface ProcessCSVFileInputPort {

    void start(Path filePath) throws IOException, CsvException;

}
