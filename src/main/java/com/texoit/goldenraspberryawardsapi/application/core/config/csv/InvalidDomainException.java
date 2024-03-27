package com.texoit.goldenraspberryawardsapi.application.core.config.csv;

public class InvalidDomainException extends RuntimeException {
    public InvalidDomainException(String message) {
        super(message);
    }
}