package com.CC.Authentication.exception;

public class ExistentEmailException extends RuntimeException {

    public ExistentEmailException() {
        super("This email is already registered in our system.");
    }
    
}
