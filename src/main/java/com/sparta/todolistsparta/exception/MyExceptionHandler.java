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
        return ResponseEntity.status(400).body("비밀번호가 일치하지 않습니다.");
    }

    @ExceptionHandler(NoExistObjectException.class)
    public ResponseEntity<String> noExistHandler() {
        return ResponseEntity.status(400).body("삭제되었거나 해당 ID를 가진 일정이 없습니다.");
    }

    @ExceptionHandler(ValidException.class)
    public ResponseEntity<String> validHandler() {
        return ResponseEntity.status(400).body("valid 에러");
    }

    @ExceptionHandler(UnacceptableExtensionsException.class)
    public ResponseEntity<String> unacceptableExtensionsHandler() {
        return ResponseEntity.status(400).body("허용되지 않은 확장자입니다. (JPEG, JPG, PNG 확장자만 가능합니다.)");
    }
}
