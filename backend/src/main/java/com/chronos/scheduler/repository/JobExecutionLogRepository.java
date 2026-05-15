package com.chronos.scheduler.repository;

import com.chronos.scheduler.entity.JobExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
public interface JobExecutionLogRepository extends JpaRepository<JobExecutionLog,Long>{
    void deleteAll();

}
