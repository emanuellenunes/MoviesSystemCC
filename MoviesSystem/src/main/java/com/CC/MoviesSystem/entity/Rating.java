package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import lombok.Data;

@Data
@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String idMovie; //foreign key
    
    @Column(nullable = false)
    private long idUser; //foreign key
    
    @Column(nullable = false)
    @Max(10)
    @Min(0)
    private int score;

    public Rating(String idMovie, long idUser, int score){
        this.idMovie = idMovie;
        this.idUser = idUser;
        this.score = score;
    }
}
