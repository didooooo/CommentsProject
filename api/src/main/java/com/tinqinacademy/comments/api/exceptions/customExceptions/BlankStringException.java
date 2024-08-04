package com.tinqinacademy.comments.api.exceptions.customExceptions;

import com.tinqinacademy.comments.api.exceptions.Errors;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class BlankStringException extends Errors {
    public BlankStringException(HttpStatus status, String message, Integer statusCode) {
        super(status, message, statusCode);
    }
}
