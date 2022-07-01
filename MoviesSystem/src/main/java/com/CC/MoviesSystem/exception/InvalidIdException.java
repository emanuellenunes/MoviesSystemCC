package com.CC.MoviesSystem.exception;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException(String idKind) {
        super("This "+idKind+" is not valid.");
    }
    
}