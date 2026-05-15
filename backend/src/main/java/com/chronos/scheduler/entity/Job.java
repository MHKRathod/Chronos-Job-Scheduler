package com.chronos.scheduler.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String command;
    private LocalDateTime scheduledTime;

    private int retryCount;
    private int maxRetries;
    private int retryDelay;
    private String cronExpression;

    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
