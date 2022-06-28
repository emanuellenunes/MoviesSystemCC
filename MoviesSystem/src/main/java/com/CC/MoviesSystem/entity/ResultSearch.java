package com.CC.MoviesSystem.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSearch {

    @JsonProperty("Search")
    private List<Movie> resultList;

    @JsonProperty("Response")
    private Boolean response;
    public void setResponse(String response) {
        this.response = Boolean.valueOf(response);
    }

    @JsonProperty("totalResults")
    private Integer total;
    public void setTotal(String total) {
        this.total = Integer.parseInt(total);
    }
}