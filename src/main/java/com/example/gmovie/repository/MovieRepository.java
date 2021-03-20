package com.example.gmovie.repository;

import com.example.gmovie.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByTitle(String title);

    Movie submitRating(float rating);

}
