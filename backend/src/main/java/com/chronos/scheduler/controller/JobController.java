package com.chronos.scheduler.controller;

import com.chronos.scheduler.dto.JobLogResponseDTO;
import com.chronos.scheduler.dto.jobRequestDTO;
import com.chronos.scheduler.dto.JobResponseDTO;
import com.chronos.scheduler.entity.Job;
import com.chronos.scheduler.service.JobService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController (JobService jobService){
        this.jobService = jobService;
    }

    @PostMapping
    public JobResponseDTO createJob(@RequestBody jobRequestDTO dto) {
        return jobService.createJob(dto);
    }

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/logs")
    public List<JobLogResponseDTO> getAllLogs() {
        return jobService.getAllLogs();
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return "job deleted successfully";
    }

    @GetMapping("/failed")
    public List<JobResponseDTO> getFailedJobs() {

        return jobService.getFailedJobs();
    }

    @DeleteMapping("/logs")
    public String deleteAllLogs() {

        jobService.deleteAllLogs();

        return "All logs deleted successfully";
    }

}
