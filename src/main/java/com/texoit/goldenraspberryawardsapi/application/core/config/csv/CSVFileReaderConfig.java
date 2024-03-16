package com.texoit.goldenraspberryawardsapi.application.core.config.csv;

public class CSVFileReaderConfig {

    private final char separator;
    private final Integer linesToSkip;

    public CSVFileReaderConfig() {
        separator = ',';
        linesToSkip = 0;
    }

    public CSVFileReaderConfig(char separator, Integer linesToSkip) {
        this.separator = separator;
        this.linesToSkip = linesToSkip;
    }

    public char getSeparator() {
        return separator;
    }

    public Integer getLinesToSkip() {
        return linesToSkip;
    }
}
