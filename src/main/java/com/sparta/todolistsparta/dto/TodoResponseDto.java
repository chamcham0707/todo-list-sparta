package com.sparta.todolistsparta.dto;

import com.sparta.todolistsparta.entity.Todo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private String content;
    private String admin;
    private LocalDateTime createAt;
    private String password;

    public TodoResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.admin = todo.getAdmin();
        this.createAt = todo.getCreateAt();
    }

    public TodoResponseDto(Todo todo, Long id) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.admin = todo.getAdmin();
        this.createAt = todo.getCreateAt();
        this.password = todo.getPassword();
    }
}
