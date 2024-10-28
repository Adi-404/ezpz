package com.adinm.srd_as.service;

import java.util.List;

import com.adinm.srd_as.model.Todo;

public interface ITodoService {

    Todo addTodo(Todo todo);

    List<Todo> getAllTodos();

    Todo updateTodoById(Long id, Todo todo);

    Todo getTodoById(Long id);

    void deleteTodoById(Long id);

    Todo updateTodoDoneStatus(Long id, boolean done);

}
