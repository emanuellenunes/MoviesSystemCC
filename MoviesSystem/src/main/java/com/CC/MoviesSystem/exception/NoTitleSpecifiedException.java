package com.CC.MoviesSystem.exception;

public class NoTitleSpecifiedException extends RuntimeException {

    public NoTitleSpecifiedException(String moviesList) {
        super("You need to specify which of the following movies you want to rate \n"+moviesList);
    }
    
}