package com.pulpudev.tasktracker.controllers;

import com.pulpudev.tasktracker.domain.dto.TaskDto;
import com.pulpudev.tasktracker.mappers.TaskMapper;
import com.pulpudev.tasktracker.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/tasks-lists/{task_list_id}/tasks")
public class TasksController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TasksController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId){
        return  taskService.listTasks(taskListId).stream().map(taskMapper::toDto).toList();
    }

    @PostMapping
    public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto taskDto){
        return taskMapper.toDto(taskService.createTask(taskListId, taskMapper.fromDto(taskDto)));
    }

    @GetMapping
    @RequestMapping(path = "/{task_id}")
    public TaskDto getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId){
        return taskService.getTask(taskListId, taskId)
                .map(taskMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Task not found!"));
    }

    @PutMapping(path = "/{task_id}")
    public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId, @RequestBody TaskDto taskDto){
        return taskMapper.toDto(taskService.updateTask(taskListId, taskId, taskMapper.fromDto(taskDto)));
    }

    @DeleteMapping(path = "/{task_id}")
    public void deleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable("task_id") UUID taskId){
        taskService.deleteTask(taskListId, taskId);
    }
}
