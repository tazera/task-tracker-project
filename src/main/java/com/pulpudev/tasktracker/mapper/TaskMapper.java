package com.pulpudev.tasktracker.mapper;

import com.pulpudev.tasktracker.domain.dto.TaskDto;
import com.pulpudev.tasktracker.domain.entities.Task;

public interface TaskMapper {
    Task fromDto(TaskDto taskDto);
    TaskDto toDto(Task task);
}
