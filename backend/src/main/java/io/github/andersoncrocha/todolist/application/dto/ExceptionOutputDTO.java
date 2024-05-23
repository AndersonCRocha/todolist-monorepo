package io.github.andersoncrocha.todolist.application.dto;

import lombok.Builder;

@Builder
public record ExceptionOutputDTO(int status, String message, Object error) {

}
