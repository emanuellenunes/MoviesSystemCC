package com.CC.MoviesSystem.dto;

import java.util.Set;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MovieDTO {

    private MovieDetails movie;
    private Set<Comment> commentList;
    private Set<Rating> ratingList;
    
    public MovieDTO(MovieDetails movie){
        this.movie = movie;
    }
}
