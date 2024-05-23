package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.core.exception.ObjectNotFoundException;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class DeleteTaskUseCase {

    private final TaskRepository taskRepository;

    public void execute(Long id) {
        if (Objects.isNull(id)) {
            throw new BadInputException("Id da tarefa é obrigatório");
        }

        final var taskToDelete = taskRepository.getById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada com id: %s".formatted(id)));

        taskRepository.delete(taskToDelete);
    }

}
