package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.core.domain.Status;
import com.alishahidi.tasco.core.domain.Task;
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
@CommandLine.Command(name = "add", description = "Add a new task", mixinStandardHelpOptions = true)
public class AddTaskCommand implements Callable<Integer> {

    final TaskService taskService;

    @CommandLine.Option(names = {"-t", "--title"}, required = true, description = "Task title")
    String title;

    @CommandLine.Option(names = {"-d", "--description"}, description = "Task description")
    String description;

    @CommandLine.Option(names = {"-p", "--priority"}, description = "Task priority")
    Integer priority;

    @CommandLine.Option(names = {"-s", "--status"}, description = "Task status. Valid values: ${COMPLETION-CANDIDATES}")
    Status status;

    @Override
    public Integer call() {
        Task task = Task.builder()
                .title(title)
                .description(description)
                .priority(priority)
                .status(status)
                .build();

        Task addedTask = taskService.save(task);
        System.out.println(CommandLine.Help.Ansi.AUTO.string("@|green Task added successfully!|@"));

        printTaskDetails(addedTask);

        return 23;
    }

    private void printTaskDetails(Task task) {
        System.out.println("Task Details:");
        System.out.println("----------------------");
        System.out.println("ID: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Description: " + (task.getDescription() != null ? task.getDescription() : "N/A"));
        System.out.println("Priority: " + (task.getPriority() != null ? task.getPriority() : "N/A"));
        System.out.println("Status: " + task.getStatus());
        System.out.println("Created Date: " + task.getCreatedDate());
        System.out.println("----------------------");
    }
}