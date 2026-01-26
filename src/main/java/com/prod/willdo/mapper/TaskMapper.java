package com.prod.willdo.mapper;

import com.prod.willdo.dto.TaskDto;
import com.prod.willdo.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toDto(Task entity) {
        if (entity == null) return null;
        return new TaskDto(entity.getId(), entity.getDescription(), entity.isCompleted());
    }

    public Task toEntity(TaskDto dto) {
        if (dto == null) return null;
        Task task = new Task();
        task.setDescription(dto.description());
        task.setCompleted(dto.completed());
        // We generally ignore ID here as the DB generates it
        return task;
    }
}