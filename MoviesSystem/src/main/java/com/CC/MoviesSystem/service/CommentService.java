package com.CC.MoviesSystem.service;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.CommentDTO;
import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.repository.CommentRepository;
import com.CC.MoviesSystem.repository.MovieRepository;

@Service
public class CommentService {
    
    private final MovieRepository movieRepository;
    private final CommentRepository commentRepository;
    private final UserService userService;

    public CommentService(MovieRepository movieRepository, CommentRepository commentRepository, UserService userService) {
        this.movieRepository = movieRepository;
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    public CommentDTO comment(String movieId, String description, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.BASIC);

        Comment comment = new Comment(movieId, user, description);
        comment = commentRepository.save(comment);

        userService.updateUserScoreAndProfile(user);

        return new CommentDTO(comment, movieRepository.searchById(movieId));
    }
    
}