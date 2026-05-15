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
public class JobExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long jobId;
    private LocalDateTime executionTime;
    private String status;
    private String message;

}
