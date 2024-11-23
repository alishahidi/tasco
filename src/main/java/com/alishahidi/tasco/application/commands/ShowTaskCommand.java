package com.alishahidi.tasco.application.commands;

import com.alishahidi.tasco.core.domain.Status;
import com.alishahidi.tasco.core.domain.Task;
import com.alishahidi.tasco.core.services.TaskService;
import de.vandermeer.asciitable.AsciiTable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CommandLine.Command(name = "show", description = "Show tasks with filtering and sorting options", mixinStandardHelpOptions = true)
public class ShowTaskCommand implements Callable<Integer> {

    final TaskService taskService;

    @CommandLine.Option(names = {"-p", "--priority"}, description = "Filter tasks by priority")
    Integer priority;

    @CommandLine.Option(names = {"-s", "--status"}, description = "Filter tasks by status. Valid values: ${COMPLETION-CANDIDATES}")
    Status status;

    @CommandLine.Option(names = {"-o", "--order"}, description = "Specify sort order: asc or desc (default: asc)", defaultValue = "asc")
    String sortOrder;

    @Override
    public Integer call() {
        List<Task> tasks = taskService.findAll();

        if (priority != null) {
            tasks = tasks.stream()
                    .filter(task -> priority.equals(task.getPriority()))
                    .collect(Collectors.toList());
        }

        if (status != null) {
            tasks = tasks.stream()
                    .filter(task -> status == task.getStatus())
                    .collect(Collectors.toList());
        }

        tasks = sortTasksByPriority(tasks, sortOrder);

        printTasks(tasks);

        return 0;
    }

    private List<Task> sortTasksByPriority(List<Task> tasks, String sortOrder) {
        Comparator<Task> comparator = Comparator.comparing(Task::getPriority, Comparator.nullsLast(Comparator.naturalOrder()));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }
        return tasks.stream().sorted(comparator).collect(Collectors.toList());
    }

    private void printTasks(List<Task> tasks) {
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Title", "Description", "Priority", "Status");
        table.addRule();

        for (Task task : tasks) {
            table.addRow(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription() != null ? task.getDescription() : "",
                    task.getPriority() != null ? task.getPriority() : "N/A",
                    task.getStatus() != null ? task.getStatus() : "N/A"
            );
            table.addRule();
        }

        System.out.println(table.render());

        if (tasks.isEmpty()) {
            System.out.println(CommandLine.Help.Ansi.AUTO.string("@|red No tasks found for the given criteria.|@"));
        }
    }
}
