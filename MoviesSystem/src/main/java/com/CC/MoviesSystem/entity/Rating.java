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

@Entity
@Table(name = "rating")
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String idMovie; //foreign key
    
    @Column(nullable = false)
    private long idUser; //foreign key
    
    @Column(nullable = false)
    @Max(5)
    @Min(1)
    private int stars;

    public Rating(String idMovie, long idUser, int stars){
        this.idMovie = idMovie;
        this.idUser = idUser;
        this.stars = stars;
    }
}
