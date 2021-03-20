package com.example.gmovie.service;

import com.example.gmovie.model.Movie;
import com.example.gmovie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> view() {
        return movieRepository.findAll();
    }

    public Movie view(String title) {
        return movieRepository.findByTitle(title);
    }

    public Movie submit(Movie movie) {
        return movieRepository.save(movie);
    }

    public void setRating(String title, float rating) {
        Movie movie = movieRepository.findByTitle(title);
        movie.setRating((movie.getRating() + rating) / 2);
        movieRepository.save(movie);
    }
}
