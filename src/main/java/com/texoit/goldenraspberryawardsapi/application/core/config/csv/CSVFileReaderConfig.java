package com.texoit.goldenraspberryawardsapi.application.core.config.csv;

public class CSVFileReaderConfig {

    private final char separator;
    private final int linesToSkip;

    private CSVFileReaderConfig() {
        this.separator = ',';
        this.linesToSkip = 0;
    }

    private CSVFileReaderConfig(char separator, int linesToSkip) {
        this.separator = separator;
        this.linesToSkip = linesToSkip;
    }

    public char getSeparator() {
        return separator;
    }

    public Integer getLinesToSkip() {
        return linesToSkip;
    }

    public static CSVFileReaderConfig fromDefault(){
        return new CSVFileReaderConfig();
    }

    public static CSVFileReaderConfig valueOf(char separator, int linesToSkip){
        return new CSVFileReaderConfig(separator, linesToSkip);
    }
}
