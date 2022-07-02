package com.CC.MoviesSystem.repository;

import java.util.Optional;

import com.CC.MoviesSystem.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    Optional<Movie> findById(String id);
}