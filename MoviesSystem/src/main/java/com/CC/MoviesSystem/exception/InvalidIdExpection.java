package com.CC.MoviesSystem.exception;

public class InvalidIdExpection extends RuntimeException {

    public InvalidIdExpection(String idKind) {
        super("This "+idKind+" is not valid.");
    }
    
}