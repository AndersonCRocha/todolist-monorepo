package io.github.andersoncrocha.todolist.infra.database;

import io.github.andersoncrocha.todolist.core.exception.ObjectNotFoundException;
import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.TaskStatus;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryTaskRepository implements TaskRepository {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> getAll() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::createdAt)
                        .reversed())
                .toList();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return tasks.stream()
                .filter(task -> Objects.equals(id, task.id()))
                .findFirst();
    }

    @Override
    public Task create(CreateTask taskData) {
        final var task = Task.builder()
                .id(idGenerator.getAndIncrement())
                .title(taskData.title())
                .description(taskData.description())
                .status(TaskStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        tasks.add(task);
        return task;
    }

    @Override
    public Task update(Long id, UpdateTask taskData) {
        final var oldTask = tasks.stream()
                .filter(task -> Objects.equals(task.id(), id))
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada com id: %s".formatted(id)));

        final var newTask = Task.builder()
                .id(id)
                .title(taskData.title())
                .description(taskData.description())
                .status(taskData.status())
                .createdAt(oldTask.createdAt())
                .build();
        tasks.add(newTask);
        return newTask;
    }

    @Override
    public void delete(Task task) {
        tasks.remove(task);
    }

}
