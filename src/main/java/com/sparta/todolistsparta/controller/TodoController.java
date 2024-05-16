package com.sparta.todolistsparta.controller;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @PostMapping("/todo/add")
    TodoResponseDto addTodo(@RequestBody TodoRequestDto requestDto) {
        System.out.println(requestDto.getTitle());
        System.out.println(requestDto.getContent());
        System.out.println(requestDto.getAdmin());
        System.out.println(requestDto.getPassword());
        return todoService.addTodo(requestDto);
    }

    @GetMapping("/todo/detail/{id}")
    TodoResponseDto inquiryTodo(@PathVariable Long id) {
        return todoService.inquiryTod(id);
    }
}
