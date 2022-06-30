package com.CC.MoviesSystem.exception;

public class NoMoviesWithTitleException extends RuntimeException {

    public NoMoviesWithTitleException(String title) {
        super("There are no movies with title "+title+".");
    }
    
}
