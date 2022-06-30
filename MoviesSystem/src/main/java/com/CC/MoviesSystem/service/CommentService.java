package com.CC.MoviesSystem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.CommentAnswerDTO;
import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.dto.CommentReactionDTO;
import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.CommentReaction;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.enumeration.Reaction;
import com.CC.MoviesSystem.exception.InvalidIdExpection;
import com.CC.MoviesSystem.repository.CommentReactionRepository;
import com.CC.MoviesSystem.repository.CommentRepository;
import com.CC.MoviesSystem.repository.MovieRepository;

@Service
public class CommentService {
    
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final CommentReactionRepository commentReactionRepository;
    private final UserService userService;

    public CommentService(MovieRepository movieRepository, CommentRepository commentRepository, CommentReactionRepository commentReactionRepository, UserService userService) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.commentReactionRepository = commentReactionRepository;
        this.userService = userService;
    }

    public CommentDTO comment(String movieId, String description, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.BASIC);

        Comment comment = new Comment(movieId, user, description);
        comment = commentRepository.save(comment);

        return new CommentDTO(comment, movieRepository.searchById(movieId));
    }

    public CommentDTO answerComment(String description, long idAnsweredComment, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.BASIC);

        Comment originalComment = validateReferencedComment(idAnsweredComment);

        Comment comment = new Comment(originalComment.getIdMovie(), user, description, idAnsweredComment);
        comment = commentRepository.save(comment);

        userService.updateUserScoreAndProfile(user);

        return new CommentAnswerDTO(originalComment, comment, movieRepository.searchById(originalComment.getIdMovie()));
    }

    public CommentReactionDTO reactToComment(Reaction reaction, long commentId, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.ADVANCED);

        Comment comment = validateReferencedComment(commentId);
        
        CommentReaction commentReaction = new CommentReaction(user, comment, reaction);

        Optional<CommentReaction> existentReaction = commentReactionRepository.findByUserAndComment(user, comment);
        if (existentReaction.isPresent()) {
            long id = existentReaction.get().getId();
            commentReaction.setId(id);

            Reaction currReaction = existentReaction.get().getReaction();
            if (currReaction.equals(Reaction.UNLIKE) && !reaction.equals(Reaction.UNLIKE)) {
                comment.decreaseUnlikeCount();
            } else if (currReaction.equals(Reaction.LIKE) && !reaction.equals(Reaction.LIKE)) {
                comment.decreaseLikeCount();
            }
        }
        if (reaction.equals(Reaction.LIKE)) {
            comment.increaseLikeCount();
        } else if (reaction.equals(Reaction.UNLIKE)) {
            comment.increaseUnlikeCount();
        }

        comment = commentRepository.save(comment);
        commentReaction = commentReactionRepository.save(commentReaction);
        userService.updateUserScoreAndProfile(user);

        return new CommentReactionDTO(commentReaction, comment, movieRepository.searchById(comment.getIdMovie()));
    }

    private Comment validateReferencedComment(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new InvalidIdExpection("originalCommentId"));
    }
    
}