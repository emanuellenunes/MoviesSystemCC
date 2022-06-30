package com.CC.MoviesSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CC.MoviesSystem.entity.Comment;
import com.CC.MoviesSystem.entity.CommentReaction;
import com.CC.MoviesSystem.entity.User;

@Repository
public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {
    Optional<CommentReaction> findByUserAndComment(User user, Comment comment);
}
