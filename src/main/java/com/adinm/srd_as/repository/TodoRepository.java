package com.adinm.srd_as.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adinm.srd_as.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
