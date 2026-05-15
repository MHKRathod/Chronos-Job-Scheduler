package com.chronos.scheduler.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class JobLogResponseDTO {

    private Long id;

    private Long jobId;

    private String status;

    private String message;

    private LocalDateTime executionTime;
}