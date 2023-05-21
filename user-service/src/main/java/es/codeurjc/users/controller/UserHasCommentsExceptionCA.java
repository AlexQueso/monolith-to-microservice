package es.codeurjc.users.controller;

import es.codeurjc.users.exception.UserHasCommentsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class UserHasCommentsExceptionCA {

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(UserHasCommentsException.class)
    public void handleUserHasComments() {
    }
}
