package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.MovieDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    protected Comment comment;
    protected MovieDetails movie;
    
}
