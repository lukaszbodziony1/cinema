package com.ssi.cinema.exception.genric;

public class WrongOrderNameException extends RuntimeException {
    public WrongOrderNameException(String message) {
        super(message);
    }
}
