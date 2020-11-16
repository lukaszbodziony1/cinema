package com.ssi.cinema.service;

import com.ssi.cinema.repository.GenreRepository;
import com.ssi.cinema.request.genre.AddGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepository repository;

    @Autowired
    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public void addGenre(AddGenreRequest request) {
        repository.addGenre(request.getName());
    }
}
