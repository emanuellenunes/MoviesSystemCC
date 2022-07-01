package com.CC.MoviesSystem.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Reaction {
    NONE, LIKE, UNLIKE;
 
    @JsonCreator
    public static Reaction toReaction(String reactionString) {
        return Reaction.valueOf(reactionString);
    }
}
