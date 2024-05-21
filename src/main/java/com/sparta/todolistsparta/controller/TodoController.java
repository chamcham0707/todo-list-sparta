package com.sparta.todolistsparta.controller;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.service.TodoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Tag(name = "일정 추가", description = "일정을 추가해주는 메서드")
    @PostMapping("/todo")
    ResponseEntity<TodoResponseDto> addTodo(@RequestBody @Valid TodoRequestDto requestDto) {
        return ResponseEntity.status(200).body(todoService.addTodo(requestDto));
    }

    @Tag(name = "선택 일정 조회", description = "선택한 일정을 조회하는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/todo/{id}")
    ResponseEntity<TodoResponseDto> inquiryTodo(@PathVariable Long id) {
        return ResponseEntity.status(200).body(todoService.inquiryTodo(id));
    }

    @Tag(name = "전체 일정 조회", description = "저장된 모든 일정을 조회하는 메서드")
    @GetMapping("/todo")
    ResponseEntity<List<TodoResponseDto>> inquiryAllTodoList() {
        return ResponseEntity.status(200).body(todoService.inquiryAllTodoList());
    }

    @Tag(name = "일정 수정", description = "일정을 수정해주는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "2000", description = "비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    @PutMapping ("/todo/{id}")
    ResponseEntity<Long> editTodo(@PathVariable Long id, @RequestBody @Valid TodoRequestDto requestDto) {
        return ResponseEntity.status(200).body(todoService.editTodo(id, requestDto));
    }

    @Tag(name = "일정 삭제", description = "일정을 삭제해주는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "2000", description = "비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/todo/{id}")
    ResponseEntity<Long> deleteTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return ResponseEntity.status(200).body(todoService.deleteTodo(id, requestDto));
    }
}
