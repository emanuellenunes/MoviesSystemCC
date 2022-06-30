package com.CC.MoviesSystem.exception;

public class UnformattedDataException extends RuntimeException {

    public UnformattedDataException() {
        super("This data format/value is not valid.");
    }
    
}
