package com.alishahidi.tasco.core.services;

import com.alishahidi.tasco.adapters.db.TaskRepository;
import com.alishahidi.tasco.core.domain.Status;
import com.alishahidi.tasco.core.domain.Task;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TaskService {
    TaskRepository taskRepository;

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    public boolean deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            taskRepository.delete(task);
            return true;
        }
        return false;
    }

    public boolean clearTasksByStatus(Status status) {
        List<Task> tasks = taskRepository.findByStatus(status);
        if (!tasks.isEmpty()) {
            taskRepository.deleteAll(tasks);
            return true;
        }
        return false;
    }

    public boolean clearAllTasks() {
        long count = taskRepository.count();
        if (count > 0) {
            taskRepository.deleteAll();
            return true;
        }
        return false;
    }
}
