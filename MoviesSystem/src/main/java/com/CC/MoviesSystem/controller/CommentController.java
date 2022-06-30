package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.service.CommentService;

@RestController
public class CommentController {
    
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/")
    public ResponseEntity<CommentDTO> rate(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody String description) {
        CommentDTO comment = commentService.comment(movieId, description, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }
    
}