package com.sparta.todolistsparta.exception;

import org.springframework.validation.ObjectError;

public class ValidException extends RuntimeException {
    public ValidException() {
    }

    public ValidException(String message) {
        super(message);
    }
}
