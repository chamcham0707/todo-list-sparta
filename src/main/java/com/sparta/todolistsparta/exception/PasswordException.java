package com.sparta.todolistsparta.exception;

public class PasswordException extends RuntimeException {
    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }
}
