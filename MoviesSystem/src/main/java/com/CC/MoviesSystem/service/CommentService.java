package com.CC.MoviesSystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.CommentAnswerDTO;
import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.dto.CommentEntryDTO;
import com.CC.MoviesSystem.dto.CommentReactionDTO;
import com.CC.MoviesSystem.dto.CommentReactionEntryDTO;
import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.CommentReaction;
import com.CC.MoviesSystem.entity.Movie;
import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.enumeration.Reaction;
import com.CC.MoviesSystem.exception.InvalidIdException;
import com.CC.MoviesSystem.exception.UnauthorizedUserException;
import com.CC.MoviesSystem.repository.CommentReactionRepository;
import com.CC.MoviesSystem.repository.CommentRepository;
import com.CC.MoviesSystem.repository.MovieRepository;
import com.CC.MoviesSystem.repository.OMDBRepository;

@Service
public class CommentService {
    
    private final OMDBRepository omdbRepository;
    private final CommentRepository commentRepository;
    private final CommentReactionRepository commentReactionRepository;
    private final MovieRepository movieRepository;
    private final UserService userService;

    public CommentService(OMDBRepository omdbRepository, CommentRepository commentRepository, CommentReactionRepository commentReactionRepository, MovieRepository movieRepository, UserService userService) {
        this.omdbRepository = omdbRepository;
        this.commentRepository = commentRepository;
        this.commentReactionRepository = commentReactionRepository;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    public CommentDTO comment(String movieId, CommentEntryDTO commentDTO, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.BASIC);

        String idLinkedComment = null;
        if (!commentDTO.getIdLinkedComment().isEmpty()) idLinkedComment = commentDTO.getIdLinkedComment().toString();
        Comment comment = new Comment(movieId, user, commentDTO.getDescription(), idLinkedComment);
        comment = saveComment(comment);

        return new CommentDTO(comment, omdbRepository.findById(movieId));
    }

    public CommentDTO searchCommentById(long commentId, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);
        
        Comment comment = validateReferencedComment(commentId);

        return new CommentDTO(comment, omdbRepository.findById(comment.getIdMovie()));
    }

    public CommentDTO answerComment(CommentEntryDTO commentDTO, long idAnsweredComment, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.BASIC);

        Comment answeredComment = validateReferencedComment(idAnsweredComment);

        //Can only answer primary comments
        if (answeredComment.getIdAnsweredComment() != null) {
            throw new InvalidIdException("idAnsweredComment belongs to an answer so it");
        }
        String idLinkedComment = null;
        if (!commentDTO.getIdLinkedComment().isEmpty()) idLinkedComment = commentDTO.getIdLinkedComment().toString();
        Comment comment = new Comment(answeredComment.getIdMovie(), user, commentDTO.getDescription(), idLinkedComment, idAnsweredComment);
        comment = saveComment(comment);

        userService.updateUserScoreAndProfile(user);

        return new CommentAnswerDTO(answeredComment, comment, omdbRepository.findById(answeredComment.getIdMovie()));
    }

    public CommentReactionDTO reactToComment(CommentReactionEntryDTO reactionDTO, long commentId, String token) {
        
        Reaction reaction = reactionDTO.getReaction();
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.ADVANCED);

        Comment comment = validateReferencedComment(commentId);
        
        CommentReaction commentReaction = new CommentReaction(user, comment, reaction);

        Optional<CommentReaction> existentReaction = commentReactionRepository.findByUserAndComment(user, comment);
        if (existentReaction.isPresent()) {
            long id = existentReaction.get().getId();
            commentReaction.setId(id);

            Reaction currReaction = existentReaction.get().getReaction();
            if (!currReaction.equals(reaction)) {
                if (currReaction.equals(Reaction.LIKE)) {
                    comment.decreaseLikeCount();
                } else if (currReaction.equals(Reaction.UNLIKE)) {
                    comment.decreaseUnlikeCount();
                }
                if (reaction.equals(Reaction.LIKE)) {
                    comment.increaseLikeCount();
                } else if (reaction.equals(Reaction.UNLIKE)) {
                    comment.increaseUnlikeCount();
                }
            }

        } else {
            if (reaction.equals(Reaction.LIKE)) {
                comment.increaseLikeCount();
            } else if (reaction.equals(Reaction.UNLIKE)) {
                comment.increaseUnlikeCount();
            }
        }

        comment = saveComment(comment);
        commentReaction = commentReactionRepository.save(commentReaction);

        return new CommentReactionDTO(commentReaction, comment, omdbRepository.findById(comment.getIdMovie()));
    }

    public CommentDTO markCommentAsDuplicated(@Valid boolean mark, long commentId, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.MODERATOR);

        Comment comment = validateReferencedComment(commentId);

        if(mark != comment.isRepeated()) {
            comment.setRepeated(mark);
            comment = saveComment(comment);
        }
        return new CommentDTO(comment, omdbRepository.findById(comment.getIdMovie()));
    }

    public CommentDTO deleteComment(long commentId, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.MODERATOR);

        Comment comment = validateReferencedComment(commentId);

        //Can only delete its own comments
        if (!comment.getUser().equals(user)) throw new UnauthorizedUserException();

        deleteComment(comment);
        return new CommentDTO(comment, omdbRepository.findById(comment.getIdMovie()));
    }

    private Comment validateReferencedComment(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new InvalidIdException("commentId"));
    }

    private Comment saveComment(Comment comment){

        Set<Rating> ratingSet = new HashSet<Rating>();
        Set<Comment> commentList = new HashSet<Comment>();
        Optional<Movie> existentMovie = movieRepository.findById(comment.getIdMovie());
        if (existentMovie.isPresent()) {
            ratingSet = existentMovie.get().getRatingSet();
            commentList = existentMovie.get().getCommentSet();
        }
        commentList.add(comment);
        movieRepository.save(new Movie(comment.getIdMovie(), commentList, ratingSet));

        return commentRepository.save(comment);
    }

    private Comment deleteComment(Comment comment){

        Long commentId = comment.getId();

        Optional<List<Comment>> commentAnswers = commentRepository.findByIdAnsweredComment(commentId);

        if (commentAnswers.isPresent()){
            //For each comment, remove its reactions and remove it from its Movie Entity
            for (Comment c : commentAnswers.get()) {
                commentReactionRepository.removeByComment(c);
                deleteFromMovieDB(c);
            }
            //Delete all comment answers
            commentRepository.removeByIdAnsweredComment(commentId);
        }
        //Remove the comment's reactions and remove it from its Movie Entity
        commentReactionRepository.removeByComment(comment);
        deleteFromMovieDB(comment);
        //Finally delete the comment itself
        commentRepository.delete(comment);

        return comment;
    }

    private void deleteFromMovieDB(Comment comment){
        Set<Rating> ratingSet = new HashSet<Rating>();
        Set<Comment> commentList = new HashSet<Comment>();
        Optional<Movie> existentMovie = movieRepository.findById(comment.getIdMovie());
        if (existentMovie.isPresent()) {
            ratingSet = existentMovie.get().getRatingSet();
            commentList = existentMovie.get().getCommentSet();
        }
        commentList.remove(comment);
        movieRepository.save(new Movie(comment.getIdMovie(), commentList, ratingSet));
    }
    
}