package com.ssi.cinema.controller;

import com.ssi.cinema.request.movie.AddMovieRequest;
import com.ssi.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
