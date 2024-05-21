package com.sparta.todolistsparta.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto {
    private String title;

    @NotBlank(message = "할 일 내용은 필수 입력값입니다.")
    @Size(max=200, message = "할 일은 200자 이내로 작성해주세요.")
    private String content;

    //@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    @Email
    private String admin;

    @NotBlank(message = "비밀번호는 필수 입력값입니다.")
    private String password;
}
