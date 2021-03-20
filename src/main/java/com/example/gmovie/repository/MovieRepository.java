package com.example.gmovie.repository;

import com.example.gmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);

    Movie submitRating(int i);

}
