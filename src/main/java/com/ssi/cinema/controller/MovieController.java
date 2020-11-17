package com.ssi.cinema.controller;

import com.ssi.cinema.dto.MovieDto;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.movie.AddMovieRequest;
import com.ssi.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService service;

    @Autowired
    public MovieController(MovieService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewGenre(@RequestBody AddMovieRequest request) {
        service.addNewMovie(request);

        return ResponseEntity.ok()
                .body("Successfully created new movie!");
    }

    @PostMapping("/get")
    public ResponseEntity<List<MovieDto>> getMovies(@RequestBody CommonGetRequest request) {
        List<MovieDto> movies = service.getAllMovies(request);

        return ResponseEntity.ok()
                .body(movies);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable int id) {
        MovieDto movie = service.getMovieById(id);

        return ResponseEntity.ok()
                .body(movie);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable int id) {
        service.deleteMovie(id);

        return ResponseEntity.ok()
                .body("Successfully deleted movie with id " + id);
    }
}
