package com.CC.MoviesSystem.exception;

public class NonexistentEmailException extends RuntimeException {

    public NonexistentEmailException() {
        super("This email is not registered yet. Please, Sign Up.");
    }
    
}
