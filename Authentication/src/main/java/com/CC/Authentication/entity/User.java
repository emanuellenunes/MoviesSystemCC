package com.CC.Authentication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.CC.Authentication.dto.UserDTO;
import com.CC.Authentication.enumeration.Profile;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false) //, columnDefinition = "varchar(255) default BASIC"
    @Enumerated(EnumType.STRING)
    private Profile profile = Profile.READER; //Store the comment id without using a foreign key for that, since Profile is not a TABLE in the database

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int score;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String token;

    public User(String name, String email, String password, String token){
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public int scoreIncrement(){
        this.score++;
        return score;
    }
    
    public UserDTO toDTO(String kind) {
        return new UserDTO(this.name, this.email, this.password, this.token, kind);
    }
}
