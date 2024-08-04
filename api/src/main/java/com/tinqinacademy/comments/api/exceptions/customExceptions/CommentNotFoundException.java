package com.tinqinacademy.comments.api.exceptions.customExceptions;

import com.tinqinacademy.comments.api.exceptions.Errors;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class CommentNotFoundException extends Errors {
    public CommentNotFoundException(HttpStatus status, String message, Integer statusCode) {
        super(status, message, statusCode);
    }
}
