package io.github.andersoncrocha.todolist.domain.tasks.repository;

import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> getAll();

    Optional<Task> getById(Long id);

    Task create(CreateTask task);

    Task update(Long id, UpdateTask taskData);

    void delete(Task task);

}
