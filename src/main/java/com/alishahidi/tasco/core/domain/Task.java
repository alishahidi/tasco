package com.alishahidi.core.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Task {
    Long id;
    String title;
    String description;
    Priority priority;
    LocalDate dueDate;
    boolean completed;
    List<String> tags;

    public void markAsCompleted() {
        this.completed = true;
    }

    public boolean isOverdue() {
        return !completed && dueDate.isBefore(LocalDate.now());
    }
}
