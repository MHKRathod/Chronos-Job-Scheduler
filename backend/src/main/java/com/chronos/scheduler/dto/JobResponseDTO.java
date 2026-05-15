package com.chronos.scheduler.dto;

import com.chronos.scheduler.entity.JobStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class JobResponseDTO {

    private Long id;

    private String name;

    private String command;

    private LocalDateTime scheduledTime;

    private JobStatus status;

    private int retryCount;

    private LocalDateTime createdAt;
}