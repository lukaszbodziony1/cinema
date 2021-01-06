package com.ssi.cinema.mapper;

import com.ssi.cinema.dto.UserDto;
import com.ssi.cinema.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public List<UserDto> toDto(List<User> users) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .age(user.getAge())
                .id(user.getId())
                .login(user.getLogin())
                .name(user.getName())
                .roles(user.getRoles())
                .surname(user.getSurname())
                .movies(user.getMovies())
                .build();
    }
}
