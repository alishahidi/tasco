package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.core.domain.Status;
import com.alishahidi.tasco.core.domain.Task;
import com.alishahidi.tasco.core.services.TaskService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Optional;
import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CommandLine.Command(name = "update", description = "Update an existing task", mixinStandardHelpOptions = true)
public class UpdateTaskCommand implements Callable<Integer> {

    final TaskService taskService;

    @CommandLine.Option(names = {"-i", "--id"}, required = true, description = "ID of the task to update")
    Long id;

    @CommandLine.Option(names = {"-t", "--title"}, description = "Update the task title")
    String title;

    @CommandLine.Option(names = {"-d", "--description"}, description = "Update the task description")
    String description;

    @CommandLine.Option(names = {"-p", "--priority"}, description = "Update the task priority")
    Integer priority;

    @CommandLine.Option(names = {"-s", "--status"}, description = "Update the task status. Valid values: ${COMPLETION-CANDIDATES}")
    Status status;

    @CommandLine.Option(names = {"-c", "--complete"}, description = "Mark the task as complete")
    boolean markAsComplete;

    @Override
    public Integer call() {
        Optional<Task> optionalTask = taskService.findById(id);

        if (optionalTask.isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|red Task with ID " + id + " not found.|@"));
            return 1;
        }

        Task task = optionalTask.get();

        if (title != null) {
            task.setTitle(title);
        }

        if (description != null) {
            task.setDescription(description);
        }

        if (priority != null) {
            task.setPriority(priority);
        }

        if (status != null) {
            task.setStatus(status);
        }

        if (markAsComplete) {
            task.setStatus(Status.COMPLETE);
        }

        taskService.save(task);

        System.out.println(CommandLine.Help.Ansi.AUTO.string("@|green Task updated successfully!|@"));
        printTaskDetails(task);

        return 0;
    }

    private void printTaskDetails(Task task) {
        System.out.println("Updated Task Details:");
        System.out.println("----------------------");
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Description: " + (task.getDescription() != null ? task.getDescription() : "N/A"));
        System.out.println("Priority: " + (task.getPriority() != null ? task.getPriority() : "N/A"));
        System.out.println("Status: " + task.getStatus());
        System.out.println("----------------------");
    }
}
