package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddTaskUseCase {

    private final TaskRepository repository;

    public Task execute(CreateTask taskData) {
        final var title = taskData.title();

        if (title == null || title.isEmpty()) {
            throw new BadInputException("Título da tarefa é obrigatório");
        }

        if (title.length() > 60) {
            throw new BadInputException("Título da tarefa deve ter no máximo 60 caracteres");
        }

        return repository.create(taskData);
    }

}
