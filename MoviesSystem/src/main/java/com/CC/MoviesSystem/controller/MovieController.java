package com.CC.MoviesSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.entity.Movie;
import com.CC.MoviesSystem.service.MovieService;

@RestController
public class MovieController {
    
    private MovieService movieSearchService;

    public MovieController(MovieService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> search(@RequestHeader String Authorization, @RequestBody String movieTitle) {
        List<Movie> moviesList = movieSearchService.searchByTitle(movieTitle, Authorization);
        return new ResponseEntity<List<Movie>>(moviesList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/")
    public ResponseEntity<Movie> searchByMovieId(@RequestHeader String Authorization, @RequestParam String movieId) {
        Movie movie = movieSearchService.searchById(movieId, Authorization);
        return new ResponseEntity<Movie>(movie, HttpStatus.ACCEPTED);
    }

    @PostMapping("/rate/")
    public ResponseEntity<String> rate(@RequestHeader String Authorization, @RequestParam String movieId, @RequestBody int rating) {
        movieSearchService.rate(new RatingDTO(rating, movieId), Authorization);
        return new ResponseEntity<String>("", HttpStatus.ACCEPTED);
    }
    
}
