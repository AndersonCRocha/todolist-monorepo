package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.core.exception.ObjectNotFoundException;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Delete tasks")
@ExtendWith(MockitoExtension.class)
class DeleteTaskUseCaseTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private DeleteTaskUseCase sut;

    @Test
    void execute_whenIdIsNull_thenThrowsException() {
        final var exception = assertThrows(BadInputException.class, () -> sut.execute(null));

        assertEquals("Id da tarefa é obrigatório", exception.getMessage());
    }

    @Test
    void execute_whenTaskDoesntExistsWithId_thenThrowsException() {
        when(taskRepository.getById(1L)).thenReturn(Optional.empty());

        final var exception = assertThrows(ObjectNotFoundException.class, () -> sut.execute(1L));

        assertEquals("Tarefa não encontrada com id: 1", exception.getMessage());
    }

    @Test
    void execute_whenTaskExists_thenInvokeRepositoryDelete() {
        final var task = Task.builder()
                .build();
        when(taskRepository.getById(1L)).thenReturn(Optional.of(task));

        sut.execute(1L);

        verify(taskRepository, times(1)).delete(task);
    }

}
