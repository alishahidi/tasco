package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.core.domain.Status;
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
@CommandLine.Command(name = "clear", description = "Clear all tasks or tasks with specific status", mixinStandardHelpOptions = true)
public class ClearTaskCommand implements Callable<Integer> {

    final TaskService taskService;

    @CommandLine.Option(names = {"-s", "--status"}, description = "Task status. Valid values: ${COMPLETION-CANDIDATES}")
    Status status;

    @Override
    public Integer call() {
        if (status != null) {
            if (taskService.clearTasksByStatus(status)) {
                System.out.println("Tasks with status " + status + " cleared successfully!");
                return 0;
            } else {
                System.out.println("No tasks found with status " + status + ".");
                return 1;
            }
        } else {
            if (taskService.clearAllTasks()) {
                System.out.println("All tasks cleared successfully!");
                return 0;
            } else {
                System.out.println("No tasks to clear.");
                return 1;
            }
        }
    }
}
