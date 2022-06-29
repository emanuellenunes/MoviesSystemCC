package com.CC.MoviesSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.MovieSearch;
import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.service.MovieService;

@RestController
public class MovieController {
    
    private MovieService movieSearchService;

    public MovieController(MovieService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieSearch>> search(@RequestHeader String Authorization, @Valid @RequestBody String movieTitle) {
        List<MovieSearch> moviesList = movieSearchService.searchByTitle(movieTitle, Authorization);
        return new ResponseEntity<List<MovieSearch>>(moviesList, HttpStatus.ACCEPTED);
    }

    @GetMapping("/search/")
    public ResponseEntity<MovieSearch> searchByMovieId(@RequestHeader String Authorization, @Valid @RequestParam String movieId) {
        MovieSearch movie = movieSearchService.searchById(movieId, Authorization);
        return new ResponseEntity<MovieSearch>(movie, HttpStatus.ACCEPTED);
    }

    @PostMapping("/rate/")
    public ResponseEntity<RatingDTO> rate(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody int score) {
        RatingDTO rating = movieSearchService.rate(movieId, score, Authorization);
        return new ResponseEntity<RatingDTO>(rating, HttpStatus.ACCEPTED);
    }
    
}
