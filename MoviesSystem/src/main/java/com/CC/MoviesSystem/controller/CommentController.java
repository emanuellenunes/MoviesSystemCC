package com.CC.MoviesSystem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.dto.CommentEntryDTO;
import com.CC.MoviesSystem.dto.CommentReactionDTO;
import com.CC.MoviesSystem.dto.CommentReactionEntryDTO;
import com.CC.MoviesSystem.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "CommentController", description = "Comment-related operations")
@RequestMapping("/comment")
public class CommentController {
    
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Add a comment to a movie")
    @PostMapping("/")
    public ResponseEntity<CommentDTO> comment(@RequestHeader String Authorization, @RequestParam String movieId, @Valid @RequestBody CommentEntryDTO commentDTO) {
        try {
            CommentDTO comment = commentService.comment(movieId, commentDTO, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Show comment by its Id")
    @Transactional
    @GetMapping("/id={commentId}")
    public ResponseEntity<CommentDTO> searchCommentById(@RequestHeader String Authorization, @PathVariable long commentId) {
        try {
            CommentDTO comment = commentService.searchCommentById(commentId, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Reply to a movie comment")
    @Transactional
    @PostMapping("/id={commentId}/answer")
    public ResponseEntity<CommentDTO> answerComment(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody CommentEntryDTO commentDTO) {
        try {
            CommentDTO comment = commentService.answerComment(commentDTO, commentId, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "React to a movie comment")
    @Transactional
    @PostMapping("/id={commentId}/react")
    public ResponseEntity<CommentReactionDTO> reactToComment(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody CommentReactionEntryDTO reactionDTO) {
        try {
            CommentReactionDTO comment = commentService.reactToComment(reactionDTO, commentId, Authorization);
            return new ResponseEntity<CommentReactionDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Mark a movie comment as repeated")
    @Transactional
    @PostMapping("/id={commentId}/mark-repeated")
    public ResponseEntity<CommentDTO> markCommentAsDuplicated(@RequestHeader String Authorization, @PathVariable long commentId, @Valid @RequestBody boolean mark) {
        try {
            CommentDTO comment = commentService.markCommentAsDuplicated(mark, commentId, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @ApiOperation(value = "Delete a movie comment")
    @Transactional
    @DeleteMapping("/id={commentId}/delete")
    public ResponseEntity<CommentDTO> deleteComment(@RequestHeader String Authorization, @PathVariable long commentId) {
        try {
            CommentDTO comment = commentService.deleteComment(commentId, Authorization);
            return new ResponseEntity<CommentDTO>(comment, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}