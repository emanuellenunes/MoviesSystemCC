package com.CC.MoviesSystem.exception;

public class NoMoviesWithTitle extends RuntimeException {

    public NoMoviesWithTitle(String title) {
        super("There are no movies with title "+title+".");
    }
    
}
