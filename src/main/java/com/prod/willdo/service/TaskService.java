package com.prod.willdo.service;

import com.prod.willdo.dto.TaskDto;
import com.prod.willdo.mapper.TaskMapper;
import com.prod.willdo.model.Task;
import com.prod.willdo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.prod.willdo.exception.TaskNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    // We inject the Mapper here alongside the Repository
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TaskDto createTask(TaskDto taskDto) {
        // Convert DTO -> Entity
        Task task = mapper.toEntity(taskDto);
        // Save Entity
        Task savedTask = repository.save(task);
        // Convert Saved Entity -> DTO
        return mapper.toDto(savedTask);
    }

    public List<TaskDto> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto) // Convert list of Entities to list of DTOs
                .collect(Collectors.toList());
    }

    public List<TaskDto> filterByStatus(boolean completed) {
        return repository.findByCompleted(completed)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public List<TaskDto> searchTasks(String query) {
        return repository.searchByDescription(query)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public TaskDto getTaskById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}