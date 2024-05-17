package com.sparta.todolistsparta.controller;

import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.service.TodoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Tag(name = "일정 추가", description = "일정을 추가해주는 메서드")
    @PostMapping("/todo/add")
    TodoResponseDto addTodo(@RequestBody TodoRequestDto requestDto) {
        return todoService.addTodo(requestDto);
    }

    @Tag(name = "선택 일정 조회", description = "선택한 일정을 조회하는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
    })
    @GetMapping("/todo/detail/{id}")
    TodoResponseDto inquiryTodo(@PathVariable Long id) {
        return todoService.inquiryTod(id);
    }

    @Tag(name = "전체 일정 조회", description = "저장된 모든 일정을 조회하는 메서드")
    @GetMapping("/todo/inquiry")
    List<TodoResponseDto> inquiryAllTodoList() {
        return todoService.inquiryAllTodoList();
    }

    @Tag(name = "일정 수정", description = "일정을 수정해주는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "2000", description = "비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    @PostMapping("/todo/edit/{id}")
    Long editTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.editTodo(id, requestDto);
    }

    @Tag(name = "일정 삭제", description = "일정을 삭제해주는 메서드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "1000", description = "삭제되었거나 해당 ID를 가진 일정이 없습니다.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "2000", description = "비밀번호가 일치하지 않습니다.", content = @Content(mediaType = "application/json")),
    })
    @DeleteMapping("/todo/delete/{id}")
    Long deleteTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.deleteTodo(id, requestDto);
    }
}
