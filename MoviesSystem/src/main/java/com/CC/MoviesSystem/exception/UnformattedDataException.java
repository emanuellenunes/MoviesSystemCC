package com.CC.MoviesSystem.exception;

public class UnformattedDataException extends RuntimeException {

    public UnformattedDataException(String kind) {
        super("This data "+kind+" is not valid.");
    }
    
}
