package com.ssi.cinema.dto;

import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
public class UserDto {
    private int id;
    private String login;
    private String name;
    private String surname;
    private List<Role> roles;
    private Set<Movie> movies;
    private int age;
}
