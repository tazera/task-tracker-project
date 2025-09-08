package com.pulpudev.tasktracker.domain.dto;

import com.pulpudev.tasktracker.domain.entities.TaskPriority;
import com.pulpudev.tasktracker.domain.entities.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}
