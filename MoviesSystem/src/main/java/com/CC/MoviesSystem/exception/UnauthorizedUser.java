package com.CC.MoviesSystem.exception;

public class UnauthorizedUser extends RuntimeException {

    public UnauthorizedUser() {
        super("This functionality is not available for your profile.");
    }
    
}