package com.CC.MoviesSystem.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.Movie;
import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.exception.InvalidIdException;
import com.CC.MoviesSystem.exception.UnformattedDataException;
import com.CC.MoviesSystem.repository.MovieRepository;
import com.CC.MoviesSystem.repository.OMDBRepository;
import com.CC.MoviesSystem.repository.RatingRepository;

@Service
public class RatingService {
    
    private final OMDBRepository omdbRepository;
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserService userService;

    public RatingService(OMDBRepository omdbRepository, RatingRepository ratingRepository, MovieRepository movieRepository, UserService userService) {
        this.omdbRepository = omdbRepository;
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
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
        } else {
            //Only get points if it's a new movie and user
            userService.updateUserScoreAndProfile(user);
        }
        saveRating(rating);

        return new RatingDTO(rating, omdbRepository.findById(movieId));
    }

    private void validateRating(int score) {
        if (score < 0 || score > 10) throw new UnformattedDataException();
    }

    public RatingDTO searchRatingById(long ratingId, String token) {
        
        User user = userService.findUserByToken(token);
        userService.validateProfile(user, Profile.READER);

        Rating rating = validateReferencedRating(ratingId);

        return new RatingDTO(rating, omdbRepository.findById(rating.getIdMovie()));
    }

    private Rating validateReferencedRating(long ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(() -> new InvalidIdException("ratingId"));
    }

    private void saveRating(Rating rating){
        ratingRepository.save(rating);

        Set<Rating> ratingSet = new HashSet<Rating>();
        Set<Comment> commentSet = new HashSet<Comment>();
        Optional<Movie> existentMovie = movieRepository.findById(rating.getIdMovie());
        if (existentMovie.isPresent()) {
            ratingSet = existentMovie.get().getRatingSet();
            commentSet = existentMovie.get().getCommentSet();
        }
        ratingSet.add(rating);
        movieRepository.save(new Movie(rating.getIdMovie(), commentSet, ratingSet));
    }
    
}
