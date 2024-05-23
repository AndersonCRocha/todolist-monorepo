package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetTasksUseCase {

    private final TaskRepository repository;

    public List<Task> execute() {
        return repository.getAll();
    }

}
