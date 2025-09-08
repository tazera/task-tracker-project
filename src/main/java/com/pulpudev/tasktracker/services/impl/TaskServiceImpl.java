package com.pulpudev.tasktracker.services.impl;

import com.pulpudev.tasktracker.domain.entities.Task;
import com.pulpudev.tasktracker.domain.entities.TaskList;
import com.pulpudev.tasktracker.domain.entities.TaskPriority;
import com.pulpudev.tasktracker.domain.entities.TaskStatus;
import com.pulpudev.tasktracker.repositories.TaskListRepository;
import com.pulpudev.tasktracker.repositories.TaskRepository;
import com.pulpudev.tasktracker.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }

    // Find all the tasks that belong to a specific task list by its ID
    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Transactional
    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null != task.getId()){
            throw new IllegalArgumentException("Task already has an ID!");
        }
        if(null == task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title is required!");
        }
        TaskPriority taskPriority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus = TaskStatus.OPEN;

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() ->new IllegalArgumentException("Invalid task list ID!"));

        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );

        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Transactional
    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(taskId == null){
            throw new IllegalArgumentException("Task must have id!");
        }
        if(!Objects.equals(taskId, task.getId())){
            throw new IllegalArgumentException("Task ID mismatch!");
        }
        if(null == task.getPriority()) {
            throw new IllegalArgumentException("Task priority must be valid!");
        }
        if(null == task.getStatus()) {
            throw new IllegalArgumentException("Task priority must be valid!");
        }
        Task existingTask = taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(() -> new IllegalArgumentException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
            taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
