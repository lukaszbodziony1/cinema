package com.ssi.cinema.service;

import com.ssi.cinema.dto.MovieDto;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.mapper.MovieMapper;
import com.ssi.cinema.repository.MovieRepository;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.movie.AddMovieRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository repository;
    private final MovieMapper mapper;

    @Autowired
    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addNewMovie(AddMovieRequest request) {
        repository.addNewMovie(request);
    }

    public List<MovieDto> getAllMovies(CommonGetRequest request) {
        List<Movie> movies = repository.getAllMovies(request);
        return mapper.toMovie(movies);
    }

    public MovieDto getMovieById(int id) {
        Movie movie = repository.getMovieById(id);
        return mapper.toMovie(movie);
    }
}
