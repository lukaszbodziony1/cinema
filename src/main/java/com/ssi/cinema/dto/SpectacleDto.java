package com.ssi.cinema.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class SpectacleDto {
    private int id;
    private LocalDate date;
    private MovieDto movie;
    private CinemaDto cinema;
}
