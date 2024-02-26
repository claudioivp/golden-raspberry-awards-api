package com.texoit.goldenraspberryawardsapi.application.ports.in.csv;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface CSVFileReaderInputPort {

    List<String[]> read(Path filePath) throws IOException, CsvException;

}
