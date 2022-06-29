package com.CC.MoviesSystem.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.MultiMoviePreview;

@FeignClient(value = "Movie", url = "${omdb.url}")
public interface MovieRepository {

    @GetMapping
    @Cacheable("movieCache")
    MovieDetails searchById(@RequestParam("i") String movieId);

    @GetMapping
    @Cacheable("multiMovieCache")
    MultiMoviePreview searchByTitle(@RequestParam("s") String movieTitle);
    
}
