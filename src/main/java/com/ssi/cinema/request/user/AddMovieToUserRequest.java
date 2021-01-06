package com.ssi.cinema.request.user;

import lombok.Getter;

@Getter
public class AddMovieToUserRequest {
    String login;

    int movieId;
}
