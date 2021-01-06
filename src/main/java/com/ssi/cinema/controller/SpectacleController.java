package com.ssi.cinema.controller;

import com.ssi.cinema.dto.SpectacleDto;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.spectacle.AddSpectacleRequest;
import com.ssi.cinema.service.SpectacleService;
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
@RequestMapping("/spectacle")
public class SpectacleController {

    private final SpectacleService service;

    @Autowired
    public SpectacleController(SpectacleService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addSpectacle(@RequestBody AddSpectacleRequest request) {
        service.addNewSpectacle(request);

        return ResponseEntity.ok()
                .body("Successfully created new spectacle!");
    }

    @PostMapping("/get")
    public ResponseEntity<List<SpectacleDto>> getSpectacles(@RequestBody CommonGetRequest request) {
        List<SpectacleDto> spectacles = service.getSpectacles(request);

        return ResponseEntity.ok()
                .body(spectacles);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SpectacleDto> getSpectacleById(@PathVariable int id) {
        SpectacleDto spectacle = service.getSpectacleById(id);

        return ResponseEntity.ok()
                .body(spectacle);
    }

    @GetMapping("/get/cinema/{id}")
    public ResponseEntity<List<SpectacleDto>> getSpectacleForCinemaById(@PathVariable int id) {
        List<SpectacleDto> spectacle = service.getSpectaclesForCinema(id);

        return ResponseEntity.ok()
                .body(spectacle);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSpectacle(@PathVariable int id) {
        service.deleteSpectacle(id);

        return ResponseEntity.ok()
                .body("Successfully deleted spectacle from database!");
    }
}
