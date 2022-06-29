package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.CC.MoviesSystem.enumeration.Reaction;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENT_REACTION")
public class CommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long idUser;

    @Column(nullable = false)
    private long idComment;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
    
}
