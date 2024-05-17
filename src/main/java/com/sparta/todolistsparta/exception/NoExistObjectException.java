package com.sparta.todolistsparta.exception;

public class NoExistObjectException extends RuntimeException {
    public NoExistObjectException() {
    }

    public NoExistObjectException(String message) {
        super(message);
    }
}
