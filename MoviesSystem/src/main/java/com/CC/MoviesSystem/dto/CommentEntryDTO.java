package com.CC.MoviesSystem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommentEntryDTO {

    private String description;
    private List<Long> idLinkedComment;

    public CommentEntryDTO(String description) {
        this.description = description;
    }
    
}