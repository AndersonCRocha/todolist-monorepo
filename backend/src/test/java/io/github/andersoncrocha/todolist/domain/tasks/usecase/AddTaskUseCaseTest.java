package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.core.exception.BadInputException;
import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
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
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@DisplayName("Add task")
@ExtendWith(MockitoExtension.class)
class AddTaskUseCaseTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private AddTaskUseCase sut;

    @ParameterizedTest
    @CsvSource(value = {"null", "''"}, nullValues = "null")
    void execute_whenTitleIsNullOrEmpty_thenThrowsException(String title) {
        final var createTask = new CreateTask(title, null);

        final var exception = assertThrows(BadInputException.class, () -> sut.execute(createTask));

        assertEquals("Título da tarefa é obrigatório", exception.getMessage());
    }

    @Test
    void execute_whenTitleIsGreatherThan60Characteres_thenThrowsException() {
        final var title = "a".repeat(61);
        final var createTask = new CreateTask(title, null);

        final var exception = assertThrows(BadInputException.class, () -> sut.execute(createTask));

        assertEquals("Título da tarefa deve ter no máximo 60 caracteres", exception.getMessage());
    }

    @Test
    void execute_whenPayloadIsValid_thenInvokeRepository() {
        final var createTask = new CreateTask("Valid title", null);

        sut.execute(createTask);

        final var taskCaptor = ArgumentCaptor.forClass(CreateTask.class);
        verify(taskRepository, only()).create(taskCaptor.capture());

        final var expectedRepositoryParameter = new CreateTask("Valid title", null);
        assertThat(taskCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedRepositoryParameter);
    }

}
