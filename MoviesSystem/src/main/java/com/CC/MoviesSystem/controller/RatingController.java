package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.service.RatingService;

@RestController
public class RatingController {
    
    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate/")
    public ResponseEntity<RatingDTO> rate(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody int score) {
        RatingDTO rating = ratingService.rate(movieId, score, Authorization);
        return new ResponseEntity<RatingDTO>(rating, HttpStatus.OK);
    }
    
}