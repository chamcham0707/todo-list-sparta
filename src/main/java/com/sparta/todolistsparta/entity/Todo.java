package com.sparta.todolistsparta.entity;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "todo") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Todo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;
    private String title;
    private String content;
    private String admin;
    private String password;

    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.admin = requestDto.getAdmin();
        this.password = requestDto.getPassword();
    }
}
