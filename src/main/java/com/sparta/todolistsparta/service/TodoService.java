package com.sparta.todolistsparta.service;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.entity.Todo;
import com.sparta.todolistsparta.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<TodoResponseDto> inquiryAllTodoList() {
        List<Todo> results = todoRepository.findAll();
        if (!results.isEmpty()) {
            List<TodoResponseDto> responseDtos = results.stream()
                    .map(todo -> new TodoResponseDto(todo)) // Assuming you have a constructor in TodoResponseDto to map from Todo
                    .collect(Collectors.toList());
            return responseDtos;
        }
        return Collections.emptyList();
    }

    public Long editTodo(Long id, TodoRequestDto requestDto) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isPresent()) {
            Todo todo = result.get();
            if (Objects.equals(todo.getPassword(), requestDto.getPassword())) {
                todo.Update(requestDto);
                todoRepository.save(todo);
                return id;
            } else {
                return -1L;
            }
        } else {
            return -2L;
        }
    }

    public Long deleteTodo(Long id) {
        Optional<Todo> result = todoRepository.findById(id);
        if (result.isPresent()) {
            todoRepository.delete(result.get());
            return id;
        } else {
            return null;
        }
    }
}
