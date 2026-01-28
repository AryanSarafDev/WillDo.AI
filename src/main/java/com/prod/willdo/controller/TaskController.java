package com.prod.willdo.controller;

import com.prod.willdo.dto.TaskDto;
import com.prod.willdo.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public TaskDto createTask(@RequestBody @Valid TaskDto taskDto) {
        return service.createTask(taskDto);
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/filter")
    public List<TaskDto> filterByStatus(@RequestParam boolean completed) {
        return service.filterByStatus(completed);
    }

    @GetMapping("/search")
    public List<TaskDto> searchTasks(@RequestParam String query) {
        return service.searchTasks(query);
    }
    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        return service.getTaskById(id);
    }
}