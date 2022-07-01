package com.CC.MoviesSystem.dto;

import java.util.HashSet;
import java.util.Set;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {

    private MovieDetails movie;
    private Set<Comment> commentSet = new HashSet<Comment>();
    private Set<Rating> ratingSet = new HashSet<Rating>();
    
    public MovieDTO(MovieDetails movie){
        this.movie = movie;
    }
}
