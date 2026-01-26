package com.prod.willdo.repository;

import com.prod.willdo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // No code needed! Spring implements standard CRUD logic automatically.

    List<Task> findByCompleted(boolean completed);

    @Query("Select t from Task t where t.description like %?1%")
    List<Task> searchByDescription(String description);
}