package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@DisplayName("Update task")
@ExtendWith(MockitoExtension.class)
class UpdateTaskUseCaseTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private UpdateTaskUseCase sut;

    @Test
    void execute_whenIdIsNull_thenThrowsException() {
        final var exception = assertThrows(BadInputException.class, () -> sut.execute(null, null));

        assertEquals("Id da tarefa é obrigatório", exception.getMessage());
    }

    @Test
    void execute_whenStatusIsNull_thenThrowsException() {
        final var taskData = new UpdateTask(null, null, null);

        final var exception = assertThrows(BadInputException.class, () -> sut.execute(1L, taskData));

        assertEquals("Status da tarefa é obrigatório", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {"null", "''"}, nullValues = "null")
    void execute_whenTitleIsNullOrEmpty_thenThrowsException(String title) {
        final var taskData = new UpdateTask(title, null, TaskStatus.DONE);

        final var exception = assertThrows(BadInputException.class, () -> sut.execute(1L, taskData));

        assertEquals("Título da tarefa é obrigatório", exception.getMessage());
    }

    @Test
    void execute_whenTitleIsGreatherThan60Characteres_thenThrowsException() {
        final var title = "a".repeat(61);
        final var taskData = new UpdateTask(title, null, TaskStatus.DONE);

        final var exception = assertThrows(BadInputException.class, () -> sut.execute(1L, taskData));

        assertEquals("Título da tarefa deve ter no máximo 60 caracteres", exception.getMessage());
    }

    @Test
    void execute_whenPayloadIsValid_thenInvokeRepository() {
        final var updateTask = new UpdateTask("Valid title", null, TaskStatus.DONE);

        sut.execute(1L, updateTask);

        final var taskCaptor = ArgumentCaptor.forClass(UpdateTask.class);
        verify(taskRepository, only()).update(eq(1L), taskCaptor.capture());

        final var expectedRepositoryParameter = new UpdateTask("Valid title", null, TaskStatus.DONE);
        assertThat(taskCaptor.getValue()).usingRecursiveComparison()
                .isEqualTo(expectedRepositoryParameter);
    }

}
