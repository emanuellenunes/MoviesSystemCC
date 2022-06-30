package com.CC.MoviesSystem.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.exception.InvalidTokenException;
import com.CC.MoviesSystem.repository.MovieRepository;
import com.CC.MoviesSystem.repository.RatingRepository;

@Service
public class RatingService {
    
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final UserService userService;

    public RatingService(MovieRepository movieRepository, RatingRepository ratingRepository, UserService userService) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public RatingDTO rate(String movieId, int score, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);
        validateRating(score);

        Optional<Rating> existentRating = ratingRepository.findByIdMovieAndUser(movieId,user);
        Rating rating = new Rating(movieId, user, score);
        if (existentRating.isPresent()) {
            rating.setId(existentRating.get().getId());
        }
        ratingRepository.save(rating);

        userService.updateUserScoreAndProfile(user);

        return new RatingDTO(rating, movieRepository.searchById(movieId));
    }

    private void validateRating(int score) {
        assert score >= 0 && score <= 10 : new InvalidTokenException();
    }
    
}
