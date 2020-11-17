package com.ssi.cinema.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class MovieDto {
    private int id;
    private String title;
    private LocalDate premiere;
    private String director;
    @JsonProperty("min_age")
    private int minAge;
    @JsonProperty("genre_id")
    private int genreId;
}
