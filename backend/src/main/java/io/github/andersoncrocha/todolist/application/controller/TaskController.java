package io.github.andersoncrocha.todolist.application.controller;

import io.github.andersoncrocha.todolist.application.dto.CreateTaskInputDTO;
import io.github.andersoncrocha.todolist.application.dto.TaskOutputDTO;
import io.github.andersoncrocha.todolist.application.dto.UpdateTaskInputDTO;
import io.github.andersoncrocha.todolist.domain.tasks.model.CreateTask;
import io.github.andersoncrocha.todolist.domain.tasks.model.UpdateTask;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.AddTaskUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.DeleteTaskUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.GetTasksUseCase;
import io.github.andersoncrocha.todolist.domain.tasks.usecase.UpdateTaskUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final AddTaskUseCase addTask;
    private final GetTasksUseCase getTasks;
    private final UpdateTaskUseCase updateTask;
    private final DeleteTaskUseCase deleteTask;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskOutputDTO create(@RequestBody CreateTaskInputDTO input) {
        final var task = addTask.execute(new CreateTask(input.title(), input.description()));
        return TaskOutputDTO.from(task);
    }

    @PutMapping("{id}")
    public TaskOutputDTO update(@PathVariable Long id, @RequestBody UpdateTaskInputDTO input) {
        final var task = updateTask.execute(id, new UpdateTask(input.title(), input.description(), input.status()));
        return TaskOutputDTO.from(task);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        deleteTask.execute(id);
    }

    @GetMapping
    public List<TaskOutputDTO> getAll() {
        return getTasks.execute()
                .stream()
                .map(TaskOutputDTO::from)
                .toList();
    }

}
