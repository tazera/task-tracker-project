package com.pulpudev.tasktracker.services.Impl;

import com.pulpudev.tasktracker.domain.entities.TaskList;
import com.pulpudev.tasktracker.repositories.TaskListRepository;
import com.pulpudev.tasktracker.services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }
}
