package com.sparta.todolistsparta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TodoListSpartaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoListSpartaApplication.class, args);
    }

}
