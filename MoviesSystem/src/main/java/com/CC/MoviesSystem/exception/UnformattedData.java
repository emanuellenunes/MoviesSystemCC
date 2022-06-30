package com.CC.MoviesSystem.exception;

public class UnformattedData extends RuntimeException {

    public UnformattedData() {
        super("This data format/value is not valid.");
    }
    
}
