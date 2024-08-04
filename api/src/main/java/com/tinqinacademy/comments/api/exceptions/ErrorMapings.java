package com.tinqinacademy.comments.api.exceptions;

import com.tinqinacademy.comments.api.exceptions.customExceptions.BlankStringException;
import com.tinqinacademy.comments.api.exceptions.customExceptions.CommentNotFoundException;
import lombok.Getter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Getter
@Component
public class ErrorMapings implements ApplicationRunner {
    public Map<Class<? extends Throwable>,Errors> exceptionToError;
    private void fillMap(){
       exceptionToError.put(BlankStringException.class, new BlankStringException(HttpStatus.BAD_REQUEST,"Blank String",HttpStatus.BAD_REQUEST.value()));
       exceptionToError.put(CommentNotFoundException.class, new CommentNotFoundException(HttpStatus.BAD_REQUEST,"Comment doesn't exist",HttpStatus.BAD_REQUEST.value()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        exceptionToError = new HashMap<>();
        fillMap();
    }
}
