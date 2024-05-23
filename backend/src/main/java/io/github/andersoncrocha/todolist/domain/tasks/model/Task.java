package io.github.andersoncrocha.todolist.domain.tasks.model;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Task(Long id, String title, String description, TaskStatus status, LocalDateTime createdAt) {
}
