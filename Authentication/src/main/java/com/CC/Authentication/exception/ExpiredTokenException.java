package com.CC.Authentication.exception;

public class ExpiredTokenException extends RuntimeException {

    public ExpiredTokenException() {
        super("This token is expired. Please, try to generate a new one.");
    }
    
}
