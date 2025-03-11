package org.learning.bms.controllers;

import org.learning.bms.models.Movie;
import org.learning.bms.services.IMovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable long movieId) {
        return movieService.findMovieById(movieId).orElse(null);
    }
}
