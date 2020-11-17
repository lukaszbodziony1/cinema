package com.ssi.cinema.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GenreDto {
    private int id;
    private String name;
}
