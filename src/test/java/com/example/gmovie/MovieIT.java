package com.example.gmovie;

import com.example.gmovie.controller.MovieDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class MovieIT {
    private static final String baseURL = "/gm/v1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Given the GBDB is empty
     * When I visit GMDB movies
     * Then I should see no movies
     */
    @Test
    @DisplayName("No Movie")
    public void noMovie() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(baseURL + "/movies")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();

        String movieDtoString = mvcResult.getResponse().getContentAsString();
        assertThat(movieDtoString, is("[]"));
    }

    /**
     * Given a new movie has released
     * When I submit this new movie to GMDB movies
     * Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Submit a movie")
    public void submitMovie() throws Exception {
        MovieDto movieDto = new MovieDto("Speed", 5.0f);
        MvcResult mvcResult = mockMvc.perform(post(baseURL + "/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movieDto))
        ).andExpect(status().isCreated()
        ).andReturn();

        String movieDtoString = mvcResult.getResponse().getContentAsString();
        MovieDto returnedMovieDto = objectMapper.readValue(movieDtoString, MovieDto.class);

        assertThat("", returnedMovieDto, is(movieDto));
    }

    /**
     * Given the GBDB has a movie
     * When I visit GMDB movies
     * Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit when DB has one movie")
    public void getWhenDbHasOneMovie() {
    }

    /**
     * Given the GBDB has many movies
     * When I visit GMDB movies
     * Then I should see that movie in GMDB movies
     */
    @Test
    @DisplayName("Visit a movie when DB has many movie")
    public void visitAMovieWhenDbHasManyMovies() {
    }

    /**
     * Given the GBDB has many movies
     * When I visit GMDB movies
     * Then I should see a list with that movie
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

