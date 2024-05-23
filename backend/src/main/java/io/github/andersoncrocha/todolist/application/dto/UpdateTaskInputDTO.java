package io.github.andersoncrocha.todolist.application.dto;

import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;

public record UpdateTaskInputDTO(String title, String description, TaskStatus status) {
}
