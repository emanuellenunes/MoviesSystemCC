package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.CommentReaction;
import com.CC.MoviesSystem.entity.MovieDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CommentReactionDTO extends CommentDTO {

    private CommentReaction commentReaction;

    public CommentReactionDTO(CommentReaction commentReaction, Comment comment, MovieDetails movie){
        this.commentReaction = commentReaction;
        this.comment = comment;
        this.movie = movie;
    }
    
}