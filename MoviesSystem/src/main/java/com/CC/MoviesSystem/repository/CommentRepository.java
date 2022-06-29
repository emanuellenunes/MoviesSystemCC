package com.CC.MoviesSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdMovieAndUser(String idMovie, User user);
    Optional<List<Comment>> findByIdMovie(String idMovie);
}
