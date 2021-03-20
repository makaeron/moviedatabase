package com.example.gmovie.controller;

import com.example.gmovie.model.Movie;
import com.example.gmovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/movies/{title}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDto view(@PathVariable String title) {
        Movie movie = movieService.view(title);
        if (movie != null) {
            MovieDto movieDto = new MovieDto();
            movieDto.setTitle(movie.getTitle());
            movieDto.setRating(movie.getRating());
            return movieDto;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movie found");
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

    @PutMapping("/movies")
    public MovieDto update(@RequestBody MovieDto movieDto) {
        Movie movie = new Movie();
        //movie.setRating(movieDto.getRating();
        movieService.setRating(movieDto.getTitle(), movieDto.getRating());
        Movie updateMovie = movieService.view(movieDto.getTitle());
        MovieDto updatedMovieDto = new MovieDto(updateMovie.getTitle(), updateMovie.getRating());
        return updatedMovieDto;
    }
}
