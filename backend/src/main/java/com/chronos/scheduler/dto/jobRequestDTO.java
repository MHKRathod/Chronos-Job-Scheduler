package com.chronos.scheduler.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class jobRequestDTO {

        private String name;

        private String command;

        private LocalDateTime scheduledTime;

        private String cronExpression;
    }

