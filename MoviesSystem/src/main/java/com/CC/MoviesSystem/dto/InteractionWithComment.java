package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.enumeration.Reaction;

import lombok.Data;

@Data
public class InteractionWithComment {

    private String description;
    private Reaction reaction;

    public InteractionWithComment(String description, Reaction reaction) {
        this.description = description;
        this.reaction = reaction;
    }
    
}