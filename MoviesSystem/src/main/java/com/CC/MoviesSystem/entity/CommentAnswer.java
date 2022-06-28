package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "comment_answer")
@Data
public class CommentAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private long idOriginalComment; //foreign key
    
    @Column(nullable = false)
    private long idUser; //foreign key
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = true)
    private long idLinkedComment; //Store the comment id without using a foreign key for that
}
