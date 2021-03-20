package com.example.gmovie.controller;

import com.example.gmovie.model.Movie;
import com.example.gmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gm/v1")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<MovieDto> view() {
        List<Movie> movieList = movieService.view();
        List<MovieDto> movieDtoList = movieList.stream()
                .map(m -> new MovieDto(m.getTitle(), m.getRating()))
                        .collect(Collectors.toList());
        return movieDtoList;
    }

    @PostMapping("/movies")
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto submit(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setRating(movieDto.getRating());
        Movie savedMovie = movieService.submit(movie);
        MovieDto savedMovieDto = new MovieDto(savedMovie.getTitle(), savedMovie.getRating());
        return savedMovieDto;
    }
}
