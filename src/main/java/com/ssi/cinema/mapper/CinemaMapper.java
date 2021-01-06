package com.ssi.cinema.mapper;

import com.ssi.cinema.dto.CinemaDto;
import com.ssi.cinema.entity.Cinema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CinemaMapper {
    public List<CinemaDto> toCinema(List<Cinema> cinemas) {
        return cinemas.stream().map(this::toCinema).collect(Collectors.toList());
    }

    public CinemaDto toCinema(Cinema cinema) {
        return CinemaDto.builder()
                .id(cinema.getId())
                .address(cinema.getAddress())
                .name(cinema.getName())
                .build();
    }
}
