package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class UpdateTaskUseCase {

    private final TaskRepository taskRepository;

    public Task execute(Long id, UpdateTask taskData) {
        if (Objects.isNull(id)) {
            throw new BadInputException("Id da tarefa é obrigatório");
        }

        if (Objects.isNull(taskData.status())) {
            throw new BadInputException("Status da tarefa é obrigatório");
        }

        final var title = taskData.title();

        if (title == null || title.isEmpty()) {
            throw new BadInputException("Título da tarefa é obrigatório");
        }

        if (title.length() > 60) {
            throw new BadInputException("Título da tarefa deve ter no máximo 60 caracteres");
        }

        return taskRepository.update(id, taskData);
    }

}
