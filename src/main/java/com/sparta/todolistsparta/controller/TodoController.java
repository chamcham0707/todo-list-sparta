package com.sparta.todolistsparta.controller;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;
    @PostMapping("/todo/add")
    TodoResponseDto addTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.addTodo(requestDto);
    }

    @GetMapping("/todo/detail/{id}")
    TodoResponseDto inquiryTodo(@PathVariable Long id) {
        return todoService.inquiryTod(id);
    }

    @GetMapping("/todo/inquiry")
    List<TodoResponseDto> inquiryAllTodoList() {
        return todoService.inquiryAllTodoList();
    }

    @PostMapping("/todo/edit/{id}")
    TodoResponseDto editTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.editTodo(id, requestDto);
    }

    @DeleteMapping("/todo/delete/{id}")
    Long deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id);
    }
}
