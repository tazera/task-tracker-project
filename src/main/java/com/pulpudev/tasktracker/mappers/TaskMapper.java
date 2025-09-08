package com.pulpudev.tasktracker.mappers;

import com.pulpudev.tasktracker.domain.dto.TaskDto;
import com.pulpudev.tasktracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
