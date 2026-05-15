package com.chronos.scheduler.repository;


import com.chronos.scheduler.entity.Job;
import com.chronos.scheduler.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job,Long>{

    List<Job> findByStatusAndScheduledTimeBefore(
            JobStatus status,
            LocalDateTime time
    );

    List<Job> findByStatus(JobStatus status);
}
