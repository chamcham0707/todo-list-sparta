package com.sparta.todolistsparta.dto;

import com.sparta.todolistsparta.entity.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TodoResponseDto {
    private Long todoId;
    private String title;
    private String content;
    private String admin;
    private LocalDateTime modifiedAt;

    public TodoResponseDto(Todo todo) {
        this.todoId = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.admin = todo.getAdmin();
        this.modifiedAt = todo.getModifiedAt();
    }
}
