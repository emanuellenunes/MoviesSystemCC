package com.CC.MoviesSystem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommentEntryDTO {

    private String description;
    private List<Long> idAnsweredComment;

    public CommentEntryDTO(String description) {
        this.description = description;
    }
    
}