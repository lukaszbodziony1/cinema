package com.ssi.cinema.request.spectacle;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class AddSpectacleRequest {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonProperty("movie_id")
    private int movieId;

    @JsonProperty("cinema_id")
    private int cinemaId;
}
