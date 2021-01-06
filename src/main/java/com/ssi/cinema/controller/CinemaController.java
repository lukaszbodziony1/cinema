package com.ssi.cinema.controller;

import com.ssi.cinema.dto.CinemaDto;
import com.ssi.cinema.dto.MovieDto;
import com.ssi.cinema.request.cinema.CreateCinemaRequest;
import com.ssi.cinema.request.cinema.UpdateCinemaRequest;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.movie.UpdateMovieRequest;
import com.ssi.cinema.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {
    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addNewCinema(@RequestBody CreateCinemaRequest request) {
        service.addCinema(request);

        return ResponseEntity.ok()
                .body("Successfully created new cinema!");
    }

    @PostMapping("/get")
    public ResponseEntity<List<CinemaDto>> getCinemas(@RequestBody CommonGetRequest request) {
        List<CinemaDto> cinemas = service.getAllCinemas(request);

        return ResponseEntity.ok()
                .body(cinemas);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CinemaDto> getCinemaById(@PathVariable int id) {
        CinemaDto cinema = service.getCinemaById(id);

        return ResponseEntity.ok()
                .body(cinema);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCinema(@PathVariable int id) {
        service.deleteCinema(id);

        return ResponseEntity.ok()
                .body("Successfully deleted cinema with id " + id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCinema(@RequestBody UpdateCinemaRequest request) {
        service.updateCinema(request);

        return ResponseEntity.ok()
                .body("Successfully updated cinema with id " + request.getId());
    }

















}
