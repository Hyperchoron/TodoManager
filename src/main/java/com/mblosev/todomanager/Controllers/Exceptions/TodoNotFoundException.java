package com.mblosev.todomanager.Controllers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(int todoId) {
        super("Задача с ид " + todoId + " не найдена.");
    }
}
