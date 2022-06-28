package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment_reaction")
@Data
public class CommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long idUser; //foreign key

    @Column(nullable = false)
    private String idComment; //foreign key

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Reaction reaction;
    
}
