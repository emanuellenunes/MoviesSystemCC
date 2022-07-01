package com.CC.MoviesSystem.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;

import com.CC.MoviesSystem.entity.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    @Cacheable("movieCache")
    Optional<Movie> findById(String id);
}