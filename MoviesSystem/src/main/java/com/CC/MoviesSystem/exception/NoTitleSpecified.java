package com.CC.MoviesSystem.exception;

public class NoTitleSpecified extends RuntimeException {

    public NoTitleSpecified(String moviesList) {
        super("You need to specify which of the following movies you want to rate \n"+moviesList);
    }
    
}