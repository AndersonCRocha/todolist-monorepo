package io.github.andersoncrocha.todolist.infra.config;

import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.AddTaskUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.DeleteTaskUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.GetTasksUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.UpdateTaskUseCase;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskBeansConfiguration {

    private final TaskRepository taskRepository;

    public TaskBeansConfiguration(
            @Qualifier("h2TaskRepository") TaskRepository taskRepository
    ) {
        this.taskRepository = taskRepository;
    }

    @Bean
    public GetTasksUseCase getTasks() {
        return new GetTasksUseCase(taskRepository);
    }

    @Bean
    public AddTaskUseCase addTask() {
        return new AddTaskUseCase(taskRepository);
    }

    @Bean
    public UpdateTaskUseCase updateTask() {
        return new UpdateTaskUseCase(taskRepository);
    }

    @Bean
    public DeleteTaskUseCase deleteTask() {
        return new DeleteTaskUseCase(taskRepository);
    }

}
