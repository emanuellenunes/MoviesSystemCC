package com.CC.MoviesSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.MovieDTO;
import com.CC.MoviesSystem.entity.MoviePreview;
import com.CC.MoviesSystem.exception.InvalidIdException;
import com.CC.MoviesSystem.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "MovieController", description = "Movie-related searches")
public class MovieController {
    
    private MovieService movieSearchService;

    public MovieController(MovieService movieSearchService) {
        this.movieSearchService = movieSearchService;
    }

    @ApiOperation(value = "Show movies summary by text")
    @GetMapping("/search")
    public ResponseEntity<List<MoviePreview>> search(@RequestHeader String Authorization, @Valid @RequestBody String movieTitle) {
        List<MoviePreview> moviesList = movieSearchService.searchByTitle(movieTitle, Authorization);
        return new ResponseEntity<List<MoviePreview>>(moviesList, HttpStatus.OK);
    }

    @ApiOperation(value = "Show movie details by Id")
    @Transactional
    @GetMapping("/movie/id={movieId}")
    public ResponseEntity<MovieDTO> searchByMovieId(@RequestHeader String Authorization, @PathVariable String movieId) {
        MovieDTO movie = new MovieDTO();
        try {
            movie = movieSearchService.searchById(movieId, Authorization);
        } catch(InvalidIdException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch(Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(movie);
        
    }
    
}
