package com.CC.MoviesSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.CC.MoviesSystem.enumeration.Reaction;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "COMMENT_REACTION")
public class CommentReaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="USERS")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="COMMENT")
    private Comment comment;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Reaction reaction = Reaction.NONE;

    public CommentReaction(User user, Comment comment, Reaction reaction){
        this.user = user;
        this.comment = comment;
        this.reaction = reaction;
    }
    
}
