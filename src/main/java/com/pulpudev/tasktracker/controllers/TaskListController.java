package com.pulpudev.tasktracker.controllers;

import com.pulpudev.tasktracker.domain.dto.TaskListDto;
import com.pulpudev.tasktracker.domain.entities.TaskList;
import com.pulpudev.tasktracker.mappers.TaskListMapper;
import com.pulpudev.tasktracker.services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }


    @GetMapping
    public List<TaskListDto> listTaskLists(){
        return taskListService.listTaskLists().stream().map(taskListMapper::toDto).toList();
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto){
        TaskList createdTaskList =  taskListService.createTaskList(
                taskListMapper.fromDto(taskListDto)
        );
        return taskListMapper.toDto(createdTaskList);
    }

    @GetMapping(path = "/{id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable("id") UUID taskListId){
        return taskListService.getTaskListById(taskListId).map(taskListMapper::toDto);
    }

    @PutMapping(path = "/{id}")
    public TaskListDto updateTaskList(
            @PathVariable("id") UUID taskListId,
            @RequestBody TaskListDto taskListDto){

         TaskList updatedTaskList = taskListService.updateTaskList(
                    taskListId,
                    taskListMapper.fromDto(taskListDto)
                    );

            return taskListMapper.toDto(updatedTaskList);

    }

    @DeleteMapping(path = "/{id}")
    public void deleteTaskList(@PathVariable("id") UUID taskListId){
        taskListService.deleteTaskList(taskListId);
    }
}
