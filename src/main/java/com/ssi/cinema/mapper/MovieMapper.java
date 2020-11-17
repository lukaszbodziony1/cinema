package com.ssi.cinema.mapper;

import com.ssi.cinema.dto.MovieDto;
import com.ssi.cinema.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public List<MovieDto> toMovie(List<Movie> movies) {
        return movies.stream().map(this::toMovie).collect(Collectors.toList());
    }

    public MovieDto toMovie(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .premiere(movie.getPremiere())
                .director(movie.getDirector())
                .minAge(movie.getMinAge())
                .genreId(movie.getGenre().getId())
                .build();
    }
}
