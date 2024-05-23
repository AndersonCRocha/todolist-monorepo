package io.github.andersoncrocha.todolist.domain.tasks.usecase;

import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Get tasks")
@ExtendWith(MockitoExtension.class)
class GetTasksUseCaseTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private GetTasksUseCase sut;

    @Test
    void execute_whenInvoked_thenInvokeRepository() {
        final var mockedValues = generateMockedValues();
        when(taskRepository.getAll()).thenReturn(mockedValues);

        final var result = sut.execute();

        assertThat(result).isEqualTo(generateMockedValues());
    }

    private List<Task> generateMockedValues() {
        return List.of(Task.builder()
                .id(1L)
                .title("Titulo 1")
                .description("Descrição 1")
                .status(TaskStatus.IN_PROGRESS)
                .build(), Task.builder()
                .id(2L)
                .title("Titulo 2")
                .description("Descrição 2")
                .status(TaskStatus.PENDING)
                .build());
    }

}
