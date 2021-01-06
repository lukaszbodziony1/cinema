package com.ssi.cinema.service;

import com.ssi.cinema.dto.UserDto;
import com.ssi.cinema.entity.Movie;
import com.ssi.cinema.entity.Role;
import com.ssi.cinema.entity.User;
import com.ssi.cinema.exception.user.LoginFailedException;
import com.ssi.cinema.exception.user.UserExistsException;
import com.ssi.cinema.mapper.UserMapper;
import com.ssi.cinema.repository.MovieRepository;
import com.ssi.cinema.repository.RoleRepository;
import com.ssi.cinema.repository.UserRepository;
import com.ssi.cinema.request.common.CommonGetRequest;
import com.ssi.cinema.request.user.AddMovieToUserRequest;
import com.ssi.cinema.request.user.AddUserRequest;
import com.ssi.cinema.request.user.LoginRequest;
import com.ssi.cinema.request.user.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository repository;
    private final MovieRepository movieRepository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    @Autowired
    public UserService(UserRepository repository, MovieRepository movieRepository, PasswordEncoder encoder, RoleRepository roleRepository, UserMapper mapper) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    public boolean login(LoginRequest request) {
        String userPassword = repository.getUserByLogin(request.getLogin()).getPassword();
        if (!userPassword.equals(request.getPassword()))
            throw new LoginFailedException("Username or password is wrong!");
        return true;
    }

    public void addNewUser(AddUserRequest request) {
//        String encodedPassword = encoder.encode(request.getPassword());
        List<Role> roles = roleRepository.getRolesByIds(request.getRoleIds());
        boolean userExists = repository.checkIfUserExists(request.getLogin());
        if (userExists) {
            throw new UserExistsException("User with given login already exists!");
        } else
            repository.addNewUser(request, request.getPassword(), roles);
    }

    public List<UserDto> getUsers(CommonGetRequest request) {
        List<User> users = repository.getAllUsers(request);
        return mapper.toDto(users);
    }

    public UserDto getUserByLogin(String login) {
        UserDto user = mapper.toDto(repository.getUserByLogin(login));
        return user;
    }

    public void addMovieToFavourites(AddMovieToUserRequest request) {
        User user = repository.getUserByLogin(request.getLogin());
        Movie movie = movieRepository.getMovieById(request.getMovieId());
        Set<Movie> userMovies = user.getMovies();
        userMovies.add(movie);
        user.setMovies(userMovies);

        repository.updateUser(user);
    }

    public void updateUser(UpdateUserRequest request) {
        User user = repository.getUserByLogin(request.getLogin());
        List<Role> roles = roleRepository.getRolesByIds(request.getRoleIds());
        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setRoles(roles);
        user.setSurname(request.getSurname());
        repository.updateUser(user);
    }

    public void deleteUser(String login) {
        User user = repository.getUserByLogin(login);
        repository.deleteUser(user);
    }
}
