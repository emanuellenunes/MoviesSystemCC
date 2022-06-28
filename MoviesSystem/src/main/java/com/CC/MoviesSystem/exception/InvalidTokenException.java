package com.CC.MoviesSystem.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("This token is not valid.");
    }
    
}
