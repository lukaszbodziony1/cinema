package com.ssi.cinema.controller;

import com.ssi.cinema.dto.UserDto;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.user.AddMovieToUserRequest;
import com.ssi.cinema.request.user.AddUserRequest;
import com.ssi.cinema.request.user.LoginRequest;
import com.ssi.cinema.request.user.UpdateUserRequest;
import com.ssi.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody AddUserRequest request) {
        service.addNewUser(request);

        return ResponseEntity.ok()
                .body("Successfully created new user!");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginRequest request) {
        boolean response = service.login(request);

        return ResponseEntity.ok()
                .body(response);
    }

    @PostMapping("/get")
    public ResponseEntity<List<UserDto>> getUsers(@RequestBody CommonGetRequest request) {
        List<UserDto> users = service.getUsers(request);

        return ResponseEntity.ok()
                .body(users);
    }

    @GetMapping("/get/{login}")
    public ResponseEntity<UserDto> getUserByLogin(@PathVariable String login) {
        UserDto user = service.getUserByLogin(login);

        return ResponseEntity.ok()
                .body(user);
    }

    @PostMapping("/movie/add")
    public ResponseEntity<String> addMovieToFavourites(@RequestBody AddMovieToUserRequest request) {
        service.addMovieToFavourites(request);

        return ResponseEntity.ok()
                .body("Successfully added movie to favourites!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest request) {
        service.updateUser(request);

        return ResponseEntity.ok()
                .body("Successfully updated user " + request.getLogin() + "!");
    }

    @DeleteMapping("/delete/{login}")
    public ResponseEntity<String> updateUser(@PathVariable String login) {
        service.deleteUser(login);

        return ResponseEntity.ok()
                .body("Successfully deleted user " + login + "!");
    }
}
