package com.chronos.scheduler.scheduler;

import com.chronos.scheduler.service.JobExecutionService;
import com.chronos.scheduler.entity.JobStatus;
import com.chronos.scheduler.entity.Job;
import com.chronos.scheduler.entity.JobExecutionLog;
import com.chronos.scheduler.repository.JobExecutionLogRepository;
import com.chronos.scheduler.repository.JobRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
@Component
public class JobScheduler {

    private final JobRepository jobRepository;
    private final JobExecutionLogRepository jobExecutionLogRepository;
    JobExecutionService jobExecutionService;

    public JobScheduler(
            JobRepository jobRepository,
            JobExecutionLogRepository jobExecutionLogRepository,
            JobExecutionService jobExecutionService
    ) {
        this.jobRepository = jobRepository;
        this.jobExecutionLogRepository = jobExecutionLogRepository;
        this.jobExecutionService = jobExecutionService;
    }
    // Scheduler checks every 5 sec
    @Scheduled(fixedRate = 5000)
    public void executeJobs() {

        List<Job> jobs =
                jobRepository.findByStatusAndScheduledTimeBefore(
                        JobStatus.PENDING,
                        LocalDateTime.now()
                );

        for (Job job : jobs) {

            jobExecutionService.processJob(job);
        }
    }

    // Async worker execution
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

            // Simulate job execution
            Thread.sleep(5000);

            log.setStatus("SUCCESS");

            log.setMessage("Executed successfully");

            job.setStatus(JobStatus.COMPLETED);

            // Recurring job logic
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