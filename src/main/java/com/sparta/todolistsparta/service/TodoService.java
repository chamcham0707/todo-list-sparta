package com.sparta.todolistsparta.service;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.entity.Todo;
import com.sparta.todolistsparta.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    public TodoResponseDto addTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        todoRepository.save(todo);
        TodoResponseDto responseDto = new TodoResponseDto(todo);
        return responseDto;
    }

    public TodoResponseDto inquiryTod(Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isPresent()) {
            TodoResponseDto responseDto = new TodoResponseDto(result.get());
            return responseDto;
        } else {
            return null;
        }
    }
}
