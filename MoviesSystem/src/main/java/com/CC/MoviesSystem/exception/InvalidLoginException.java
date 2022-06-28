package com.CC.MoviesSystem.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException() {
        super("Invalid password.");
    }
    
}
