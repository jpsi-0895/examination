package com.shubhamjajoo.examsite.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public APIException(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }
}
