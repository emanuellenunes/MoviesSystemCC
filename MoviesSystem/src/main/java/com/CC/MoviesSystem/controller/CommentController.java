package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.dto.CommentEntryDTO;
import com.CC.MoviesSystem.dto.CommentReactionDTO;
import com.CC.MoviesSystem.enumeration.Reaction;
import com.CC.MoviesSystem.service.CommentService;

@RestController
public class CommentController {
    
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/")
    public ResponseEntity<CommentDTO> comment(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody CommentEntryDTO commentDTO) {
        CommentDTO comment = commentService.comment(movieId, commentDTO, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }

    @GetMapping("/comment/id={commentId}")
    public ResponseEntity<CommentDTO> searchCommentById(@RequestHeader String Authorization, @PathVariable long commentId) {
        CommentDTO comment = commentService.searchCommentById(commentId, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }

    @PostMapping("/comment/id={commentId}/answer")
    public ResponseEntity<CommentDTO> answerComment(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody CommentEntryDTO commentDTO) {
        CommentDTO comment = commentService.answerComment(commentDTO, commentId, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }

    @PostMapping("/comment/id={commentId}/react")
    public ResponseEntity<CommentReactionDTO> reactToComment(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody Reaction reaction) {
        CommentReactionDTO comment = commentService.reactToComment(reaction, commentId, Authorization);
        return new ResponseEntity<CommentReactionDTO>(comment, HttpStatus.OK);
    }

    @PostMapping("/comment/id={commentId}/mark-repeated")
    public ResponseEntity<CommentDTO> markCommentAsDuplicated(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody boolean mark) {
        CommentDTO comment = commentService.markCommentAsDuplicated(mark, commentId, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }

    @DeleteMapping("/comment/id={commentId}/delete")
    public ResponseEntity<CommentDTO> deleteComment(@RequestHeader String Authorization, @PathVariable long commentId) {
        CommentDTO comment = commentService.deleteComment(commentId, Authorization);
        return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
    }
    
}