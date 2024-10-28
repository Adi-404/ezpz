package com.adinm.srd_as.service;

import com.adinm.srd_as.model.Todo;
import com.adinm.srd_as.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService implements ITodoService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo getTodoById(Long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodoById(Long id, Todo todo) {
        return todoRepository.findById(id)
                .map(todoObj -> {
                    todoObj.setTitle(todo.getTitle());
                    todoObj.setDescription(todo.getDescription());
                    todoObj.setDone(todo.isDone());
                    todoObj.setCreatedDate(todo.getCreatedDate());
                    return todoRepository.save(todoObj);
                })
                .orElse(null);
    }

    @Override
    public void deleteTodoById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new RuntimeException("Todo not found");
        } else {
            todoRepository.deleteById(id);
        }
    }

    // New method to update only the "done" status
    @Override
    public Todo updateTodoDoneStatus(Long id, boolean done) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setDone(done);
                    return todoRepository.save(todo);
                })
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }
}