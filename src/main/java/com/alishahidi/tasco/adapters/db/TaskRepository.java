package com.alishahidi.tasco.adapters.db;

import com.alishahidi.tasco.core.domain.Status;
import com.alishahidi.tasco.core.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);
}
