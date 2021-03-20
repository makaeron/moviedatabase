package com.example.gmovie.controller;

import com.example.gmovie.model.Movie;
import com.example.gmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gm/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<MovieDto> view() {
        List<Movie> movieList = movieService.view();
        List<MovieDto> movieDtoList = movieList.stream()
                .map(m -> new MovieDto(m.getTitle(), m.getRating()))
                        .collect(Collectors.toList());
        return movieDtoList;
    }
}
