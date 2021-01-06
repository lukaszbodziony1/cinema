package com.ssi.cinema.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AddUserRequest {
    private String login;

    private String password;

    private String name;

    private String surname;

    @JsonProperty("role_ids")
    private List<Integer> roleIds;

    private int age;
}
