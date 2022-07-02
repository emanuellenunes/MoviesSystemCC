package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.service.RatingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "RatingController", description = "Rating-related operations")
public class RatingController {
    
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @ApiOperation(value = "Rate a movie")
    @PostMapping("/rate/")
    public ResponseEntity<RatingDTO> rate(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody int score) {
        try {
            RatingDTO rating = ratingService.rate(movieId, score, Authorization);
            return new ResponseEntity<RatingDTO>(rating, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Show comment by its Id")
    @Transactional
    @GetMapping("/rating/id={ratingId}")
    public ResponseEntity<RatingDTO> searchRatingById(@RequestHeader String Authorization, @PathVariable long ratingId) {
        try {
            RatingDTO rating = ratingService.searchRatingById(ratingId, Authorization);
            return new ResponseEntity<RatingDTO>(rating, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}