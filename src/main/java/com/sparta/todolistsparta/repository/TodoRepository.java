package com.sparta.todolistsparta.repository;

import com.sparta.todolistsparta.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByOrderByModifiedAtDesc();
}
