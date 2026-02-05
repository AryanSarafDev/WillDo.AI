package com.prod.willdo.dto;

import jakarta.validation.constraints.NotBlank;
//deprecated
public record TaskDto(Long id, @NotBlank(message = "Description is required") String description, boolean completed) {

}