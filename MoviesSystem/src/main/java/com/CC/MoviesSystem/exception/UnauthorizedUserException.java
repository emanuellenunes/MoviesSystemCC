package com.CC.MoviesSystem.exception;

public class UnauthorizedUserException extends RuntimeException {

    public UnauthorizedUserException() {
        super("This functionality is not available for your profile.");
    }
    
}