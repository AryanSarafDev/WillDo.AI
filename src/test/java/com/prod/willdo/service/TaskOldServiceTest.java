package com.prod.willdo.service;

import com.prod.willdo.dto.TaskDto;
import com.prod.willdo.mapper.TaskMapper;
import com.prod.willdo.model.TaskOld;
import com.prod.willdo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // Enables Mockito support
class TaskOldServiceTest {

    @Mock
    private TaskRepository repository; // Creates a "fake" repository

    @Mock
    private TaskMapper mapper; // Creates a "fake" mapper

    @InjectMocks
    private TaskService service; // Injects the mocks into the real service

    @Test
    void shouldCreateTaskSuccessfully() {
        // 1. Prepare Data
        TaskDto inputDto = new TaskDto(null, "Buy Milk", false);
        TaskOld entity = new TaskOld();
        entity.setDescription("Buy Milk");

        TaskOld savedEntity = new TaskOld();
        savedEntity.setId(1L); // The DB would usually generate this
        savedEntity.setDescription("Buy Milk");

        TaskDto expectedResult = new TaskDto(1L, "Buy Milk", false);

        // 2. Define Mock Behavior (The "Script")
        // When the service calls mapper.toEntity, return our entity object
        when(mapper.toEntity(inputDto)).thenReturn(entity);
        // When the service calls repository.save, return our savedEntity object
        when(repository.save(any(TaskOld.class))).thenReturn(savedEntity);
        // When the service calls mapper.toDto, return the result DTO
        when(mapper.toDto(savedEntity)).thenReturn(expectedResult);

        // 3. Execute the Method
        TaskDto result = service.createTask(inputDto);

        // 4. Assertions (Check results)
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.description()).isEqualTo("Buy Milk");
    }
}