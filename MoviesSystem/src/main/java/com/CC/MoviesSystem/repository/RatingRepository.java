package com.CC.MoviesSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CC.MoviesSystem.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByIdMovieAndIdUser(String idMovie, long idUser);
}
