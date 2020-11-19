package com.ssi.cinema.mapper;

import com.ssi.cinema.dto.GenreDto;
import com.ssi.cinema.entity.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreMapper {
    public List<GenreDto> toGenreDto(List<Genre> genre) {
        return genre.stream().map(this::toGenreDto).collect(Collectors.toList());
    }

    public GenreDto toGenreDto(Genre genre) {
        return GenreDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
