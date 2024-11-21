package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.core.services.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CommandLine.Command(name = "delete", description = "Delete a task by its ID", mixinStandardHelpOptions = true)
public class DeleteTaskCommand implements Callable<Integer> {

    final TaskService taskService;

    @CommandLine.Option(names = {"-i", "--id"}, required = true, description = "Task ID to delete")
    Long id;

    @Override
    public Integer call() {
        if (taskService.deleteTask(id)) {
            System.out.println("Task with ID " + id + " deleted successfully!");
            return 0;
        } else {
            System.out.println("Task with ID " + id + " not found.");
            return 1;
        }
    }
}
