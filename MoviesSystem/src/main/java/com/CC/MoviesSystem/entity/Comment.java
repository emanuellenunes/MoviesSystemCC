package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String idMovie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERS")
    private User user;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int likeCount;
    
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int unlikeCount;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean repeated;

    @Column(nullable = true)
    private long idAnsweredComment; //Store the comment id without using a foreign key for that
    
    @Column(nullable = true)
    private long idLinkedComment; //Store the comment id without using a foreign key for that
}
