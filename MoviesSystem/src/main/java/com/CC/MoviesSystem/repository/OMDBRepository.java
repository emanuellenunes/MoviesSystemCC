package com.CC.MoviesSystem.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.MultiMoviePreview;

@FeignClient(value = "Movie", url = "${omdb.url}")
public interface OMDBRepository {

    @GetMapping
    @Cacheable("OMDBCache")
    MovieDetails findById(@RequestParam("i") String movieId);

    @GetMapping
    @Cacheable("multiOMDBCache")
    MultiMoviePreview findByTitle(@RequestParam("s") String movieTitle);
    
}
