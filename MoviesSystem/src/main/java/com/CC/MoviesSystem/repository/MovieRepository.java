package com.CC.MoviesSystem.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.CC.MoviesSystem.entity.ResultSearch;

@FeignClient(value = "Movie", url = "${omdb.url}")
public interface MovieRepository {

    @GetMapping
    ResultSearch searchById(@RequestParam("i") String movieId);

    @GetMapping
    ResultSearch searchByTitle(@RequestParam("s") String movieTitle);
    //ResultSearch searchByTitle(@RequestParam("t") String movieTitle);
    
}
