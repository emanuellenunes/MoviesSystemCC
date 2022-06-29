package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {

    private Rating rating;
    private MovieDetails movie;
    
}
