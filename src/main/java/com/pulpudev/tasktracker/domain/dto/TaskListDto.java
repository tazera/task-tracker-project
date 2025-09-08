package com.pulpudev.tasktracker.domain.dto;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;

import java.util.List;
import java.util.UUID;

public record TaskListDto(
        UUID id,
        String title,
        String description,
        Integer count,
        Double progress,
        List<TaskDto> tasks

) {
}
