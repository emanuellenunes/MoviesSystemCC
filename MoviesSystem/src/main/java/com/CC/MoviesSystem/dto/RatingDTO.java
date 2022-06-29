package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {

    private Rating rating;
    private MovieSearch movie;
    
}
