package com.CC.MoviesSystem.exception;

public class UnauthorizedUserException extends RuntimeException {

    public UnauthorizedUserException() {
        super("You are not authorized to perform this action.");
    }
    
}