package com.CC.MoviesSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.MoviePreview;
import com.CC.MoviesSystem.service.MovieService;

@RestController
public class MovieController {
    
    private MovieService movieSearchService;

    public MovieController(MovieService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<MoviePreview>> search(@RequestHeader String Authorization, @Valid @RequestBody String movieTitle) {
        List<MoviePreview> moviesList = movieSearchService.searchByTitle(movieTitle, Authorization);
        return new ResponseEntity<List<MoviePreview>>(moviesList, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<MovieDetails> searchByMovieId(@RequestHeader String Authorization, @Valid @RequestParam String movieId) {
        MovieDetails movie = movieSearchService.searchById(movieId, Authorization);
        return new ResponseEntity<MovieDetails>(movie, HttpStatus.OK);
    }
    
}
