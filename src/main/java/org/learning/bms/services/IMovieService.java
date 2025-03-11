package org.learning.bms.services;

import org.learning.bms.models.Movie;

import java.util.List;
import java.util.Optional;

public interface IMovieService {
    List<Movie> findAllMovies();

    Optional<Movie> findMovieById(long movieId);
}
