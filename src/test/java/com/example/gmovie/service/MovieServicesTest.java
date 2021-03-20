package com.example.gmovie.service;

import com.example.gmovie.model.Movie;
import com.example.gmovie.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServicesTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    /**
     *     Given the GBDB is empty
     *     When I visit GMDB movies
     *     Then I should see no movies
     */
    @Test
    @DisplayName("No Movie")
    public void noMovie() {
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());
        //Exercise
        List<Movie> movies = movieService.view();
        //assert
        verify(movieRepository).findAll();
        assertThat(movies, is(empty()));
    }

     /**
      *     Given a new movie has released
      *     When I submit this new movie to GMDB movies
      *     Then I should see that movie in GMDB movies
      */
    @Test
    @DisplayName("Submit a movie")
    public void submitMovie() {
        //Setup
        Movie movie = new Movie();// 1L is using pojo class
        movie.setId(1L);
        when(movieRepository.save(movie)).thenReturn(movie);
        //Exercise
        Movie savedMovie = movieService.submit(movie);
        //Assertion
        verify(movieRepository).save(movie);
        assertThat(savedMovie,is(movie));
    }

    /**
     *     Given the GBDB has a movie
     *     When I visit GMDB movies
     *     Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit when DB has one movie")
    public void getWhenDbHasOneMovie() {
        List<Movie> oneMovieList = Arrays.asList(new Movie[] {new Movie()});
        when(movieRepository.findAll()).thenReturn(oneMovieList);
        //Here Movie created Movie with no id
        //Exercise
        List<Movie> movies = movieService.view();
        //assert
        assertThat(movies,is(oneMovieList));
        verify(movieRepository).findAll();
    }

    /**
     *     Given the GBDB has many movies
     *     When I visit GMDB movies
     *     Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit a movie when DB has many movie")
    public void visitAMovieWhenDbHasManyMovies() {
        Movie terminator = new Movie();
        terminator.setTitle("Terminator");

        when(movieRepository.findByTitle(terminator.getTitle())).thenReturn(terminator);
        //Exercise
        Movie movie = movieService.view(terminator.getTitle());
        verify(movieRepository).findByTitle(terminator.getTitle());
        //Return array List same as submit
        assertThat(movie,is(terminator));
    }

    /**
     *    Given the GBDB has many movies
     *     When I visit GMDB movies
     *     Then I should see a list with that movie
     */
    @Test
    @DisplayName("Visit all movies when DB has many movie")
    public void visitWhenDbHasManyMovies() {
        List<Movie> twoMovieList = Arrays.asList(new Movie[] {new Movie(), new Movie()});
        when(movieRepository.findAll()).thenReturn(twoMovieList);
        //Exercise
        List<Movie> movies = movieService.view();
        verify(movieRepository).findAll();
        //Return array List same as submit
        assertThat(movies,is(twoMovieList));
    }

    /**
     * Given an existing movie
     * When I submit a 5 star rating
     * Then I can see it in the movie details.
     */
    @Test
    @DisplayName("Submit rating for a movie")
    public void submitRatingAndSeeDetails() {
        //List<Movie> oneMovieList = Arrays.asList(new Movie[] {new Movie()});
        Movie terminator = new Movie();
        terminator.setId(2L);
        terminator.setTitle("terminator");
        terminator.setRating(5);

        when(movieRepository.save(terminator)).thenReturn(terminator);
        when(movieRepository.findByTitle(terminator.getTitle())).thenReturn(terminator);

        //Execution
        movieService.submit(terminator);
        Movie movie = movieService.view(terminator.getTitle());
        assertThat(movie.getRating(),is(5));
        assertThat(movie,is(terminator));
    }

    /**
     * Given a movie with one 5 star rating and one 3 star rating
     * When I view the movie details
     * Then I expect the star rating to be 4.
     */
    @Test
    @DisplayName("Submit two ratings to see an average")
    public void submitTwoRatingsAndSeeAverageInDetail() {
        //List<Movie> oneMovieList = Arrays.asList(new Movie[] {new Movie()});
        Movie terminator = new Movie();
        terminator.setId(2L);
        terminator.setTitle("terminator");
        terminator.setRating(5);

        when(movieRepository.save(terminator)).thenReturn(terminator);
        when(movieRepository.findByTitle(terminator.getTitle())).thenReturn(terminator);

        //Execution
        movieService.submit(terminator);
        Movie movie = movieService.view(terminator.getTitle());
        assertThat(movie.getRating(),is(5.0F));
        assertThat(movie,is(terminator));

        movieService.setRating(terminator.getTitle(), 3);

        movie = movieService.view(terminator.getTitle());
        assertThat(movie.getRating(),is(4.0F));
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
