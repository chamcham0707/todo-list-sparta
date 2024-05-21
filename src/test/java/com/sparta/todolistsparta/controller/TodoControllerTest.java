package com.sparta.todolistsparta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.todolistsparta.dto.TodoRequestDto;
import com.sparta.todolistsparta.dto.TodoResponseDto;
import com.sparta.todolistsparta.entity.Todo;
import com.sparta.todolistsparta.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest // 스프링 부트 애플리케이션 전체 로드. 애플리케이션의 컨텍스트를 시작하고 모든 빈을 로드
@ExtendWith(SpringExtension.class) // JUint 5와 함께 스프링 테스트 컨텍스트 프레임워크를 통합하기 위해 사용
class TodoControllerTest {

    // TodoController를 주입받아 테스트할 컨트롤러를 설정
    @Autowired
    private TodoController todoController;

    // TodoService를 모킹
    // 스프링 컨텍스트에 TodoService의 모킹된 버전을 등록하여 테스트 중에 모킹된 객체가 사용
    @MockBean
    private TodoService todoService;

    // 스프링 MVC 테스트를 지원하는 클래스
    // 이를 통해 HTTP 요청과 응답을 시뮬레이션
    private MockMvc mockMvc;

    // JSON 직렬화 및 역직렬화를 위한 Jackson의 유틸리티 클래스
    @Autowired
    private ObjectMapper objectMapper;

    // 각 테스트가 실행되기 전에 setUp 메소드 호출
    @BeforeEach
    public void setup() {
        // MockMvc를 초기화하고 TodoController를 설정
        this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @DisplayName("일정 추가 성공") // JUnit5에서 테스트 이름 지정
    @Test // 이 메소드가 테스트 메소드임을 나타낸다.
    void testAddTodo() throws Exception {
        // Given
        // requestDto와 responseDto를 생성하여 테스트에 사용할 데이터를 설정
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("testTitle1");
        requestDto.setContent("testContent1");
        requestDto.setAdmin("testAdmin1@gmail.com");
        requestDto.setPassword("testPassword1");

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setTitle(requestDto.getTitle());
        responseDto.setContent(requestDto.getContent());
        responseDto.setAdmin(requestDto.getAdmin());

        // todoService.addTodo가 호출될 때, responseDto를 반환하도록 모킹
        Mockito.when(todoService.addTodo(Mockito.any(TodoRequestDto.class))).thenReturn(responseDto);

        // When
        mockMvc.perform( // HTTP 요청 시뮬레이션
                        MockMvcRequestBuilders.post("/api/todo") // post 요청을 '/api/todo' URL로 전송
                                .contentType(MediaType.APPLICATION_JSON) // 요청의 Content-Type을 JSON으로 설정
                                .content(objectMapper.writeValueAsString(requestDto)) // requestDto 객체를 JSON 문자열로 변환하여 요청 본문에 포함시킨다.
                )
                // Then
                // andExpect: 응답에 대한 검증 수행
                .andExpect(MockMvcResultMatchers.status().isOk()) // status().isOk(): 응답 상태가 200 OK인지 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(requestDto.getTitle())) // 응답 JSON의 title 필드 값이 requestDto의 title 값과 일치하는지 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(requestDto.getContent())) // 응답 JSON의 content 필드 값이 requestDto의 content 값과 일치하는지 확인
                .andExpect(MockMvcResultMatchers.jsonPath("$.admin").value(requestDto.getAdmin())); // 응답 JSON의 admin 필드 값이 requestDto의 admin 값과 일치하는지 확인
    }

    @DisplayName("선택 일정 조회 성공")
    @Test
    void testInquiryTodo() throws Exception {
        // Given
        Long id = 1L;

        TodoResponseDto responseDto = new TodoResponseDto();
        responseDto.setTodoId(id);
        responseDto.setTitle("test title");
        responseDto.setContent("test content");
        responseDto.setAdmin("test@gamil.com");

        // Mocking the service method
        Mockito.when(todoService.inquiryTodo(Mockito.anyLong())).thenReturn(responseDto);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo/{id}", id))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(responseDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(responseDto.getContent()));
    }

    @DisplayName("전체 일정 조회 성공")
    @Test
    void inquiryAllTodoList() throws Exception {
        List<TodoResponseDto> responseDtos = new ArrayList<>();

        TodoResponseDto responseDto1 = new TodoResponseDto();
        responseDto1.setTitle("title1");
        responseDto1.setContent("content1");

        TodoResponseDto responseDto2 = new TodoResponseDto();
        responseDto2.setTitle("title2");
        responseDto2.setContent("content2");

        responseDtos.add(responseDto1);
        responseDtos.add(responseDto2);

        Mockito.when(todoService.inquiryAllTodoList()).thenReturn(responseDtos);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/todo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(responseDto1.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(responseDto1.getContent()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value(responseDto2.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].content").value(responseDto2.getContent()));

    }

    @Test
    void editTodo() throws Exception {
        // given
        Long id = 1L;

        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("title1");
        requestDto.setContent("content1");
        requestDto.setAdmin("admin1@gamil.com");
        requestDto.setPassword("password1");

        Mockito.when(todoService.editTodo(Mockito.anyLong(), Mockito.any(TodoRequestDto.class))).thenReturn(id);

        // when
        mockMvc.perform(MockMvcRequestBuilders.put("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(id));
    }

    @Test
    void deleteTodo() throws Exception {
        // given
        Long id = 1L;

        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("title1");
        requestDto.setContent("content1");
        requestDto.setAdmin("admin1@gamil.com");
        requestDto.setPassword("password1");

        Mockito.when(todoService.deleteTodo(Mockito.anyLong(), Mockito.any(TodoRequestDto.class))).thenReturn(id);

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/todo/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(id));
    }
}