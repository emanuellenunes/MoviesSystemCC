package com.CC.MoviesSystem.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
@Table(name = "MOVIE")
public class Movie {

    @Id
    private String id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="COMMENT")
    private Set<Comment> commentList;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="RATING")
    private Set<Rating> ratingList;

    public Movie(String id) {
        this.id = id;
    }
    
}
