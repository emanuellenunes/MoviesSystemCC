package com.CC.Authentication.exception;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException() {
        super("This token is not valid.");
    }
    
}
