package com.CC.MoviesSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CC.MoviesSystem.entity.Rating;
import com.CC.MoviesSystem.entity.User;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByIdMovieAndUser(String idMovie, User user);
    Optional<Rating> findById(long id);
    Optional<List<Rating>> findByIdMovie(String idMovie);
}
