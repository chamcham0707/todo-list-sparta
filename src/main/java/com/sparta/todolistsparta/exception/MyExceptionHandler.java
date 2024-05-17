package com.sparta.todolistsparta.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handler() {
        return ResponseEntity.status(400).body("url에 문자가 들어왔습니다.");
    }

    @ExceptionHandler(PasswordException.class)
    public ResponseEntity<String> passwordHandler() {
        return ResponseEntity.status(400).body("2000");
    }

    @ExceptionHandler(NoExistObjectException.class)
    public ResponseEntity<String> noExistHandler() {
        return ResponseEntity.status(400).body("1000");
    }
}
