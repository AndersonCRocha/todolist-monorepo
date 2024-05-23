package io.github.andersoncrocha.todolist.application.dto;

import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskOutputDTO(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt) {

    public static TaskOutputDTO from(Task task) {
        return TaskOutputDTO.builder()
                .id(task.id())
                .title(task.title())
                .description(task.description())
                .status(task.status())
                .createdAt(task.createdAt())
                .build();
    }

}
