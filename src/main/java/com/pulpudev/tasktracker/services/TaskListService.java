package com.pulpudev.tasktracker.services;

import com.pulpudev.tasktracker.domain.entities.TaskList;

import java.util.List;

public interface TaskListService {
    List<TaskList> listTaskLists();
}
