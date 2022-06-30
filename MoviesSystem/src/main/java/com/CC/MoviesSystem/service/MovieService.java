package com.CC.MoviesSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.MoviePreview;
import com.CC.MoviesSystem.entity.MultiMoviePreview;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.repository.MovieRepository;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;
    private final UserService userService;

    public MovieService(MovieRepository movieRepository, UserService userService) {
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    public List<MoviePreview> searchByTitle(String title, String token) {
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);
        MultiMoviePreview multiMoviePreview = movieRepository.searchByTitle(title);
        return multiMoviePreview.getResultList();
    }

    public MovieDetails searchById(String idMovie, String token) {
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);
        return movieRepository.searchById(idMovie);
    }
    
}
