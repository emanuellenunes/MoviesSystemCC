package com.CC.MoviesSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.MovieDTO;
import com.CC.MoviesSystem.entity.Movie;
import com.CC.MoviesSystem.entity.MovieDetails;
import com.CC.MoviesSystem.entity.MoviePreview;
import com.CC.MoviesSystem.entity.MultiMoviePreview;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.repository.MovieRepository;
import com.CC.MoviesSystem.repository.OMDBRepository;

@Service
public class MovieService {
    
    private final OMDBRepository omdbRepository;
    private final MovieRepository movieRepository;
    private final UserService userService;

    public MovieService(OMDBRepository omdbRepository, MovieRepository movieRepository, UserService userService) {
        this.omdbRepository = omdbRepository;
        this.movieRepository = movieRepository;
        this.userService = userService;
    }

    public List<MoviePreview> searchByTitle(String title, String token) {
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);
        MultiMoviePreview multiMoviePreview = omdbRepository.findByTitle(title);
        return multiMoviePreview.getResultList();
    }

    public MovieDTO searchById(String idMovie, String token) {
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);

        MovieDetails movieDetails = omdbRepository.findById(idMovie);
        Optional<Movie> movie = movieRepository.findById(idMovie);
        if (movie.isPresent()) {
            return new MovieDTO(movieDetails, movie.get().getCommentSet(), movie.get().getRatingSet());
        }
        return new MovieDTO(movieDetails);
    }
    
}
