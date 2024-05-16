package com.sparta.todolistsparta.dto;

import lombok.Getter;

@Getter
public class TodoRequestDto {
    private String title;
    private String content;
    private String admin;
    private String password;
}
