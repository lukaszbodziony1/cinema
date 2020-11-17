package com.ssi.cinema.request.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class AddMovieRequest {
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiere;

    private String director;

    @JsonProperty("min_age")
    private int minAge;

    @JsonProperty("genre_id")
    private int genreId;
}
