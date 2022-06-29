package com.CC.MoviesSystem.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CC.MoviesSystem.dto.MovieSearch;
import com.CC.MoviesSystem.dto.MultiMovieSearch;

@FeignClient(value = "Movie", url = "${omdb.url}")
public interface MovieRepository {

    @GetMapping
    @Cacheable("movieCache")
    MovieSearch searchById(@RequestParam("i") String movieId);

    @GetMapping
    @Cacheable("multiMovieCache")
    MultiMovieSearch searchByTitle(@RequestParam("s") String movieTitle);
    
}
