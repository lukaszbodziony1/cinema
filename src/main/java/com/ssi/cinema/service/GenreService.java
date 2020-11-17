package com.ssi.cinema.service;

import com.ssi.cinema.dto.GenreDto;
import com.ssi.cinema.entity.Genre;
import com.ssi.cinema.mapper.GenreMapper;
import com.ssi.cinema.repository.GenreRepository;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.genre.AddGenreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    private final GenreRepository repository;
    private final GenreMapper mapper;

    @Autowired
    public GenreService(GenreRepository repository, GenreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void addGenre(AddGenreRequest request) {
        repository.addGenre(request.getName());
    }

    public List<GenreDto> getAllGenres(CommonGetRequest request) {
        List<Genre> genres = repository.getAllGenres(request);
        return mapper.toGenreDto(genres);
    }
}
