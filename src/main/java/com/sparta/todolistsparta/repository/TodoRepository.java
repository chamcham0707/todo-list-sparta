package com.sparta.todolistsparta.repository;

import com.sparta.todolistsparta.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
