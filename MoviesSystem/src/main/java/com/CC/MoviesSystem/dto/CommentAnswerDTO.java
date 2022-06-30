package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.MovieDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentAnswerDTO extends CommentDTO {

    private Comment originalComment;

    public CommentAnswerDTO(Comment originalComment, Comment comment, MovieDetails movie){
        this.originalComment = originalComment;
        this.comment = comment;
        this.movie = movie;
    }
    
}
