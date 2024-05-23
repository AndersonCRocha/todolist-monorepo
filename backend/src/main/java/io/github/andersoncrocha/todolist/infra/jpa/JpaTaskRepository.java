package io.github.andersoncrocha.todolist.infra.jpa;

import io.github.andersoncrocha.todolist.infra.jpa.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {

}
