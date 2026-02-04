package com.prod.willdo.repository;

import com.prod.willdo.model.TaskOld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskOld, Long> {
    // No code needed! Spring implements standard CRUD logic automatically.

    List<TaskOld> findByCompleted(boolean completed);

    @Query("Select t from TaskOld t where t.description like %?1%")
    List<TaskOld> searchByDescription(String description);
}