package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.dto.CommentReactionDTO;
import com.CC.MoviesSystem.dto.InteractionWithComment;
import com.CC.MoviesSystem.service.CommentService;

@RestController
public class CommentController {
    
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/")
    public ResponseEntity<CommentDTO> comment(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody String description) {
        CommentDTO comment = commentService.comment(movieId, description, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }

    @PostMapping("/comment/answer/id={originalCommentId}")
    public ResponseEntity<CommentDTO> answerComment(@RequestHeader String Authorization, @PathVariable long originalCommentId, @Valid @RequestBody InteractionWithComment interaction) {
        if (!interaction.getDescription().isEmpty()) {
            CommentDTO comment = commentService.answerComment(interaction.getDescription(), originalCommentId, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<CommentDTO>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/comment/react/id={originalCommentId}")
    public ResponseEntity<CommentReactionDTO> reactComment(@RequestHeader String Authorization, @PathVariable long originalCommentId, @Valid @RequestBody InteractionWithComment interaction) {
        if (!interaction.getReaction().toString().isEmpty()) {
            CommentReactionDTO comment = commentService.reactToComment(interaction.getReaction(), originalCommentId, Authorization);
            return new ResponseEntity<CommentReactionDTO>(comment, HttpStatus.OK);
        }
        return new ResponseEntity<CommentReactionDTO>(HttpStatus.BAD_REQUEST);
    }
    
}