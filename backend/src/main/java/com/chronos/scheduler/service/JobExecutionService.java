package com.chronos.scheduler.service;

import com.chronos.scheduler.entity.Job;
import com.chronos.scheduler.entity.JobExecutionLog;
import com.chronos.scheduler.entity.JobStatus;
import com.chronos.scheduler.repository.JobExecutionLogRepository;
import com.chronos.scheduler.repository.JobRepository;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JobExecutionService {

    private final JobRepository jobRepository;
    private final JobExecutionLogRepository jobExecutionLogRepository;

    public JobExecutionService(
            JobRepository jobRepository,
            JobExecutionLogRepository jobExecutionLogRepository
    ) {
        this.jobRepository = jobRepository;
        this.jobExecutionLogRepository = jobExecutionLogRepository;
    }

    @Async("taskExecutor")
    public void processJob(Job job) {

        JobExecutionLog log = new JobExecutionLog();

        log.setJobId(job.getId());

        log.setExecutionTime(LocalDateTime.now());

        try {

            System.out.println(
                    "Executing on thread: "
                            + Thread.currentThread().getName()
            );

            System.out.println(
                    "Executing job: " + job.getName()
            );

            job.setStatus(JobStatus.RUNNING);

            jobRepository.save(job);

            // simulate execution
            Thread.sleep(5000);

            // FORCE FAILURE ONLY FOR FAILURE TEST JOBS
            if (job.getName().contains("Failure")) {

                throw new RuntimeException(
                        "Simulated failure"
                );
            }

            log.setStatus("SUCCESS");

            log.setMessage("Executed successfully");

            job.setStatus(JobStatus.COMPLETED);

            // recurring job logic
            if (job.getCronExpression() != null &&
                    !job.getCronExpression().isEmpty()) {

                job.setStatus(JobStatus.PENDING);

                job.setScheduledTime(
                        LocalDateTime.now().plusMinutes(1)
                );
            }

            jobRepository.save(job);

        } catch (Exception e) {

            int retryCount =
                    job.getRetryCount() + 1;

            job.setRetryCount(retryCount);

            if (retryCount < 3) {

                int delay =
                        retryCount * job.getRetryDelay();

                job.setScheduledTime(
                        LocalDateTime.now().plusSeconds(delay)
                );

                job.setStatus(JobStatus.PENDING);

            } else {

                job.setStatus(JobStatus.FAILED);
            }

            jobRepository.save(job);

            log.setStatus("FAILED");

            log.setMessage(
                    "Retry "
                            + retryCount
                            + " scheduled: "
                            + e.getMessage()
            );
        }

        jobExecutionLogRepository.save(log);
    }
}