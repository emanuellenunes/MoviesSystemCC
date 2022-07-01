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
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String idMovie;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USERS")
    private User user;
    
    @Column(nullable = false, length = 260)
    private String description;
    
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int likeCount;
    
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int unlikeCount;
    
    @Column(columnDefinition = "boolean default false")
    private boolean repeated;

    @Column(nullable = true)
    private Long idAnsweredComment; //Store the comment id without using a foreign key for that
    
    @Column(nullable = true)
    private String idLinkedComment; //Store the comment id without using a foreign key for that

    //Simple Comment
    public Comment(String idMovie, User user, String description) {
        this.idMovie = idMovie;
        this.user = user;
        this.description = description;
    }

    //Comment Answer
    public Comment(String idMovie, User user, String description, String idLinkedComment, long idAnsweredComment) {
        this.idMovie = idMovie;
        this.user = user;
        this.description = description;
        this.idLinkedComment = idLinkedComment;
        this.idAnsweredComment = idAnsweredComment;
    }

    //Comment with Linked Comments
    public Comment(String idMovie, User user, String description, String idLinkedComment) {
        this.idMovie = idMovie;
        this.user = user;
        this.description = description;
        this.idLinkedComment = idLinkedComment;
    }

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void increaseUnlikeCount() {
        this.unlikeCount++;
    }

    public void decreaseLikeCount() {
        this.likeCount--;
    }

    public void decreaseUnlikeCount() {
        this.unlikeCount--;
    }
}
