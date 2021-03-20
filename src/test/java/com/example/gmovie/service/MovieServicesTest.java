package com.example.gmovie.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieServicesTest {

    /**
     *     Given the GBDB is empty
     *     When I visit GMDB movies
     *     Then I should see no movies
     */
    @Test
    @DisplayName("No Movie")
    public void noMovie() {
    }

     /**
      *     Given a new movie has released
      *     When I submit this new movie to GMDB movies
      *     Then I should see that movie in GMDB movies
      */
    @Test
    @DisplayName("Submit a movie")
    public void submitMovie() {
    }

    /**
     *     Given the GBDB has a movie
     *     When I visit GMDB movies
     *     Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit when DB has one movie")
    public void getWhenDbHasOneMovie() {
    }

    /**
     *     Given the GBDB has many movies
     *     When I visit GMDB movies
     *     Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit a movie when DB has many movie")
    public void visitAMovieWhenDbHasManyMovies() {
    }

    /**
     *    Given the GBDB has many movies
     *     When I visit GMDB movies
     *     Then I should see a list with that movie
     */
    @Test
    @DisplayName("Visit all movies when DB has many movie")
    public void visitWhenDbHasManyMovies() {
    }

    /**
     * Given an existing movie
     * When I submit a 5 star rating
     * Then I can see it in the movie details.
     */
    @Test
    @DisplayName("Submit rating for a movie")
    public void submitRatingAndSeeDetails() {
    }

    /**
     * Given a movie with one 5 star rating and one 3 star rating
     * When I view the movie details
     * Then I expect the star rating to be 4.
     */
    @Test
    @DisplayName("Submit two ratings to see an average")
    public void submitTwoRatingsAndSeeAverageInDetail() {
    }

    /**
     * Given an existing movie
     * When I submit a star rating and text review
     * Then I can see my contribution on the movie details.
     */
    @Test
    @DisplayName("Submit Rating and Description to see in Detail")
    public void submitRatingAndDescriptionToSeeThemInDetails() {
    }


    /**
     * Given an existing movie
     * When I submit a text review without a star rating
     * Then I receive a friendly message that a star rating is required.
     */
    @Test
    @DisplayName("Submit Description without a Rating")
    public void submitDescriptionWithoutRating() {
    }
}
