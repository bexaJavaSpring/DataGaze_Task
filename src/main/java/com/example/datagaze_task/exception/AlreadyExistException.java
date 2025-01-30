package com.example.datagaze_task.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlreadyExistException extends RuntimeException{
    public AlreadyExistException(String message) {
        super(message);
    }

}
