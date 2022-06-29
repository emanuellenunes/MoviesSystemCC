package com.CC.MoviesSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.MovieSearch;
import com.CC.MoviesSystem.dto.MultiMovieSearch;
import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.exception.InvalidTokenException;
import com.CC.MoviesSystem.exception.UnauthorizedUser;
import com.CC.MoviesSystem.repository.RatingRepository;
import com.CC.MoviesSystem.repository.UserRepository;
import com.CC.MoviesSystem.repository.MovieRepository;

@Service
public class MovieService {
    
    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
    }

    public List<MovieSearch> searchByTitle(String title, String token) {
        User user = findUserByToken(token);
        validateProfile(user, Profile.READER);
        MultiMovieSearch multiMovieSearch = movieRepository.searchByTitle(title);
        return multiMovieSearch.getResultList();
    }

    public MovieSearch searchById(String idMovie, String token) {
        User user = findUserByToken(token);
        validateProfile(user, Profile.READER);
        return movieRepository.searchById(idMovie);
    }

    public RatingDTO rate(String movieId, int score, String token) {
        
        User user = findUserByToken(token);
        validateProfile(user, Profile.READER);
        validateRating(score);

        Optional<Rating> existentRating = ratingRepository.findByIdMovieAndUser(movieId,user);
        Rating rating = new Rating(movieId, user, score);
        if (existentRating.isPresent()) {
            rating.setId(existentRating.get().getId());
        }
        ratingRepository.save(rating);

        updateUserScoreAndProfile(user);

        return new RatingDTO(rating, movieRepository.searchById(movieId));
    }

    private void validateRating(int score) {
        assert score >= 0 && score <= 10 : new InvalidTokenException();
    }

    private User findUserByToken(String Authorization) {
        String token = Authorization.replace("Bearer ", "");
        return userRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException());
    }

    private void validateProfile(User user, Profile profile){
        if (user.getProfile().ordinal() < profile.ordinal()) throw new UnauthorizedUser();
    }

    private void updateUserScoreAndProfile(User user) {
        user.scoreIncrement();
        user.setProfile(upgradeProfile(user));
        userRepository.save(user);
    }

    private Profile upgradeProfile(User user) {
        Profile currProfile = user.getProfile();
        int score = user.getScore();
        if (currProfile == Profile.READER && score >= 20 && score < 100) {
            currProfile = Profile.BASIC;
        } else if (currProfile == Profile.BASIC && score >= 100 && score < 1000) {
            currProfile = Profile.ADVANCED;
        } else if (currProfile == Profile.ADVANCED && score >= 1000) {
            currProfile = Profile.MODERATOR;
        }
        return currProfile;
    }
    
}
