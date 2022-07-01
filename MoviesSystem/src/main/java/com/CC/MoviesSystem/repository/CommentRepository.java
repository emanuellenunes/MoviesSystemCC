package com.CC.MoviesSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CC.MoviesSystem.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<List<Comment>> findByIdMovie(String idMovie);
    Optional<List<Comment>> findByIdAnsweredComment(Long id);
    Optional<List<Comment>> removeByIdAnsweredComment(Long id);
    //@Query("DELETE c FROM Comment c WHERE c.idAnsweredComment = :id")
    //void deleteAnswersByCommentId(Long id);
}
