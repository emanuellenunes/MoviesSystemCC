package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private Rating rating;
    private MovieDetails movie;
    
}
