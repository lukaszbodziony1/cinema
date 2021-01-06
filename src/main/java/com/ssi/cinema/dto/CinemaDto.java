package com.ssi.cinema.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CinemaDto {
    private int id;
    private String name;
    private String address;
}
