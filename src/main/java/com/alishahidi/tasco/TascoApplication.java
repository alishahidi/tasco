package com.alishahidi.tasco;

import com.alishahidi.tasco.application.commands.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@CommandLine.Command(subcommands = {
        AddTaskCommand.class,
        ShowTaskCommand.class,
        UpdateTaskCommand.class,
        DeleteTaskCommand.class,
        ClearTaskCommand.class
})
public class TascoApplication implements CommandLineRunner, ExitCodeGenerator {
    private int exitCode;

    final CommandLine.IFactory factory;

    @Override
    public void run(String... args) {
        exitCode = new CommandLine(this, factory).execute(args);
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public static void main(String[] args) {
        System.exit(SpringApplication.exit(SpringApplication.run(TascoApplication.class, args)));
    }
}
