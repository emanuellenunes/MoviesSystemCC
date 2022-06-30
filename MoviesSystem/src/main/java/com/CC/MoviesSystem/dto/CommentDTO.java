package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.MovieDetails;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

    private Comment comment;
    private MovieDetails movie;
    
}
