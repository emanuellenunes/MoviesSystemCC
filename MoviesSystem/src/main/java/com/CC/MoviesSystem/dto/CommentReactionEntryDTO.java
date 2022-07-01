package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.enumeration.Reaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentReactionEntryDTO {

    private Reaction reaction;
    
}