package com.CC.Authentication.exception;

public class InvalidLoginException extends RuntimeException {

    public InvalidLoginException() {
        super("Invalid password.");
    }
    
}
