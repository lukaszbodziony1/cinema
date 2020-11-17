package com.ssi.cinema.service;

import com.ssi.cinema.repository.MovieRepository;
import com.ssi.cinema.request.movie.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    private final MovieRepository repository;

    @Autowired
    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public void addNewMovie(AddMovieRequest request) {
        repository.addNewMovie(request);
    }
}
