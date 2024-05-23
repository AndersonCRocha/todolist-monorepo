package io.github.andersoncrocha.todolist.infra.database;

import io.github.andersoncrocha.todolist.core.exception.ObjectNotFoundException;
import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
import io.github.andersoncrocha.todolist.domain.tasks.model.Task;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import io.github.andersoncrocha.todolist.domain.tasks.repository.TaskRepository;
import io.github.andersoncrocha.todolist.infra.jpa.JpaTaskRepository;
import io.github.andersoncrocha.todolist.infra.jpa.entities.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class H2TaskRepository implements TaskRepository {

    private final JpaTaskRepository jpaTaskRepository;

    @Override
    public List<Task> getAll() {
        return jpaTaskRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
                .stream()
                .map(TaskEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return jpaTaskRepository.findById(id)
                .map(TaskEntity::toDomain);
    }

    @Override
    @Transactional
    public Task create(CreateTask taskData) {
        final var task = jpaTaskRepository.save(new TaskEntity(taskData.title(), taskData.description()));
        return task.toDomain();
    }

    @Override
    @Transactional
    public Task update(Long id, UpdateTask taskData) {
        final var task = jpaTaskRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Tarefa não encontrada com id: %s".formatted(id)));
        task.update(taskData);
        return task.toDomain();
    }

    @Override
    public void delete(Task task) {
        jpaTaskRepository.deleteById(task.id());
    }

}
