package com.prod.willdo.mapper;

import com.prod.willdo.dto.TaskDto;
import com.prod.willdo.model.TaskOld;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskDto toDto(TaskOld entity) {
        if (entity == null) return null;
        return new TaskDto(entity.getId(), entity.getDescription(), entity.isCompleted());
    }

    public TaskOld toEntity(TaskDto dto) {
        if (dto == null) return null;
        TaskOld taskOld = new TaskOld();
        taskOld.setDescription(dto.description());
        taskOld.setCompleted(dto.completed());
        // We generally ignore ID here as the DB generates it
        return taskOld;
    }
}