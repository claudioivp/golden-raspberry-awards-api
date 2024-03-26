package com.texoit.goldenraspberryawardsapi.application.core.config.csv;

public class InvalidBeanFromCsvException extends RuntimeException {
    public InvalidBeanFromCsvException(String message) {
        super(message);
    }
}