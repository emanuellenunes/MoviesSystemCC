package com.CC.MoviesSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class MovieDTO {

    private String title;
    private String id;

    public MovieDTO(String title) {
        this.title = title;
    }
    
}
