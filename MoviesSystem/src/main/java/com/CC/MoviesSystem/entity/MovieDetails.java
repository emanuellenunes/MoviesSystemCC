package com.CC.MoviesSystem.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MovieDetails extends MoviePreview {

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Director")
    private String director;

    @JsonProperty("Plot")
    private String plot;

    @JsonProperty("imdbRating")
    private Float imdbRating;
    public void setRating(String imdbRating) {
        this.imdbRating = Float.parseFloat(imdbRating);
    }
}
