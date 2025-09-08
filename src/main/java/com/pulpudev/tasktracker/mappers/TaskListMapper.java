package com.pulpudev.tasktracker.mappers;

import com.pulpudev.tasktracker.domain.dto.TaskListDto;
import com.pulpudev.tasktracker.domain.entities.TaskList;

public interface TaskListMapper{

    TaskList fromDto(TaskListDto taskListDto);
    TaskListDto toDto(TaskList taskList);

}
