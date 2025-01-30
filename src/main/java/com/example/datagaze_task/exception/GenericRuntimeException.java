package com.example.datagaze_task.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class GenericRuntimeException extends RuntimeException{
    public GenericRuntimeException(String message) {
        super(message);
    }
}
