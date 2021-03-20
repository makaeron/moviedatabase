package com.example.gmovie.service;

import com.example.gmovie.model.Movie;
import com.example.gmovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> view() {
        return movieRepository.findAll();
    }

    public Movie submit(Movie movie) {
        return movieRepository.save(movie);

    }
}
