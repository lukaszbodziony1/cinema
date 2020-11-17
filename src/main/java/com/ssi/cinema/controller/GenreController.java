package com.ssi.cinema.controller;

import com.ssi.cinema.dto.GenreDto;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.genre.AddGenreRequest;
import com.ssi.cinema.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService service;

    @Autowired
    public GenreController(GenreService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewGenre(@RequestBody AddGenreRequest request) {

        service.addGenre(request);

        return ResponseEntity.ok()
                .body("Successfully created new genre!");
    }

    @PostMapping("/get")
    public ResponseEntity<List<GenreDto>> getAllGenre(@RequestBody CommonGetRequest request) {
        List<GenreDto> genres = service.getAllGenres(request);

        return ResponseEntity.ok()
                .body(genres);
    }
}
