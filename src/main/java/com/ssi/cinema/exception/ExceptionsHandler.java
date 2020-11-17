package com.ssi.cinema.exception;

import com.ssi.cinema.exception.genre.CreateNewGenreEmptyNameException;
import com.ssi.cinema.exception.genre.CreatingNewGenreException;
import com.ssi.cinema.exception.genric.GettingObjectsException;
import com.ssi.cinema.exception.genric.WrongOrderNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CreateNewGenreEmptyNameException.class)
    public ResponseEntity<String> handleCreateNewGenreEmptyNameException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There was a problem while creating new Genre, name can't be empty!");
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(CreatingNewGenreException.class)
    public ResponseEntity<String> handleCreateNewGenreException() {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("There was a problem while creating new Genre, it won't be save!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(GettingObjectsException.class)
    public ResponseEntity<String> handleGetObjectsException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There was a problem while processing objects from database! ");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongOrderNameException.class)
    public ResponseEntity<String> handleWrongOrderNameException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("There was a problem while processing objects from database! " +
                        "You have to pass proper order_name property! (asc or desc)");
    }
}