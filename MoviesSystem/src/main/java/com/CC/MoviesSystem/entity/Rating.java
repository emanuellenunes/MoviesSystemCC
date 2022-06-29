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
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "RATING")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String idMovie; //foreign key
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USERS")
    private User user;
    
    @Column(nullable = false)
    @Max(10)
    @Min(0)
    private int score;

    public Rating(String idMovie, User user, int score){
        this.idMovie = idMovie;
        this.user = user;
        this.score = score;
    }
}
