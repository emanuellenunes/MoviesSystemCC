package com.CC.MoviesSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.RatingDTO;
import com.CC.MoviesSystem.entity.Movie;
import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.entity.ResultSearch;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.exception.InvalidTokenException;
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

    public List<Movie> searchByTitle(String title, String token) {
        //User user = findUserByToken(token);
        ResultSearch resultSearch = movieRepository.searchByTitle(title);
        return resultSearch.getResultList();
    }

    public Movie searchById(String idMovie, String token) {
        //User user = findUserByToken(token);
        ResultSearch resultSearch = movieRepository.searchById(idMovie);
        return resultSearch.getResultList().get(0);
    }

    public void rate(RatingDTO ratingDTO, String token) {
        User user = findUserByToken(token);
        Rating rating = new Rating(ratingDTO.getIdMovie(), user.getId(), ratingDTO.getRating());

        Optional<Rating> ratingFound = ratingRepository.findByIdMovieAndIdUser(rating.getIdMovie(),rating.getIdUser());
        if (ratingFound.isPresent()) {
            rating.setId(ratingFound.get().getId());
        }
        ratingRepository.save(rating);
    }

    private User findUserByToken(String Authorization) {
        String token = Authorization.replace("Bearer ", "");
        return userRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException());
    }
    
}
