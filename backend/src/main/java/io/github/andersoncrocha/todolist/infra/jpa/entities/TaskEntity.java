package io.github.andersoncrocha.todolist.infra.jpa.entities;

import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(generator = "seq_tasks")
    @SequenceGenerator(name = "seq_tasks", allocationSize = 1)
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.PENDING;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public TaskEntity(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public Task toDomain() {
        return Task.builder()
                .id(id)
                .title(title)
                .description(description)
                .status(status)
                .createdAt(createdAt)
                .build();
    }

    public void update(UpdateTask taskData) {
        this.title = taskData.title();
        this.description = taskData.description();
        this.status = taskData.status();
    }

}
