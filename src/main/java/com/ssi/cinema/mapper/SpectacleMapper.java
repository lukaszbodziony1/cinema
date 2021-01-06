package com.ssi.cinema.mapper;

import com.ssi.cinema.dto.SpectacleDto;
import com.ssi.cinema.entity.Spectacle;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpectacleMapper {
    CinemaMapper cinemaMapper;
    MovieMapper movieMapper;

    SpectacleMapper(CinemaMapper cinemaMapper, MovieMapper movieMapper) {
        this.cinemaMapper = cinemaMapper;
        this.movieMapper = movieMapper;
    }

    public List<SpectacleDto> toDto(List<Spectacle> spectacles) {
        return spectacles.stream().map(this::toDto).collect(Collectors.toList());
    }

    public SpectacleDto toDto(Spectacle spectacle) {
        return SpectacleDto.builder()
                .id(spectacle.getId())
                .cinema(cinemaMapper.toCinema(spectacle.getCinema()))
                .movie(movieMapper.toMovie(spectacle.getMovie()))
                .date(spectacle.getDate())
                .build();
    }
}
