package com.chronos.scheduler.service;

import com.chronos.scheduler.dto.JobLogResponseDTO;
import com.chronos.scheduler.dto.jobRequestDTO;
import com.chronos.scheduler.dto.JobResponseDTO;
import com.chronos.scheduler.entity.Job;
import com.chronos.scheduler.entity.JobStatus;
import com.chronos.scheduler.repository.JobRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.chronos.scheduler.repository.JobExecutionLogRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobExecutionLogRepository jobExecutionLogRepository;

    public JobService(
            JobRepository jobRepository,
            JobExecutionLogRepository jobExecutionLogRepository
    ) {
        this.jobRepository = jobRepository;
        this.jobExecutionLogRepository = jobExecutionLogRepository;
    }

    //crteating jobs
    public JobResponseDTO createJob(jobRequestDTO dto) {

        Job job = Job.builder()
                .name(dto.getName())
                .command(dto.getCommand())
                .scheduledTime(dto.getScheduledTime())
                .cronExpression(dto.getCronExpression())
                .status(JobStatus.PENDING)
                .retryCount(0)
                .maxRetries(3)
                .retryDelay(5)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Job savedJob = jobRepository.save(job);

        return mapToResponseDTO(savedJob);
    }
    //Get All Jobs
    public List<JobResponseDTO> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public void deleteAllLogs() {
        jobExecutionLogRepository.deleteAll();
    }

    // DELETE JOB
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    //getfailed job
    public List<JobResponseDTO> getFailedJobs() {

        return jobRepository.findByStatus(JobStatus.FAILED)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Mapping Method
    private JobResponseDTO mapToResponseDTO(Job job) {

        return JobResponseDTO.builder()
                .id(job.getId())
                .name(job.getName())
                .command(job.getCommand())
                .scheduledTime(job.getScheduledTime())
                .status(job.getStatus())
                .retryCount(job.getRetryCount())
                .createdAt(job.getCreatedAt())
                .build();
    }

    public List<JobLogResponseDTO> getAllLogs() {

        return jobExecutionLogRepository.findAll()
                .stream()
                .map(log -> JobLogResponseDTO.builder()
                        .id(log.getId())
                        .jobId(log.getJobId())
                        .status(log.getStatus())
                        .message(log.getMessage())
                        .executionTime(log.getExecutionTime())
                        .build())
                .toList();
    }

}
