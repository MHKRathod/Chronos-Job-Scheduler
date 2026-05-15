# Chronos - Distributed Job Scheduler

Chronos is a full-stack job scheduling and monitoring system built using Spring Boot, React.js, and MySQL. The system allows users to create, manage, and monitor scheduled jobs with support for retries, recurring execution, authentication, asynchronous workers, and failure notifications.

---

# Features

- Create and schedule jobs
- Recurring job execution using cron expressions
- Retry mechanism for failed jobs
- Execution logs monitoring
- Secure APIs using Spring Security
- Real-time frontend dashboard
- Failed job notifications
- Asynchronous worker execution
- Concurrent job processing using thread pool
- MySQL database integration

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Spring Scheduler
- Spring Security
- Spring Async
- JPA / Hibernate
- MySQL

## Frontend
- React.js
- Axios
- Tailwind CSS

---

# System Architecture

The system follows a layered architecture:

1. React frontend sends REST API requests.
2. Spring Boot backend handles job management and authentication.
3. Scheduler continuously checks pending jobs.
4. Async worker threads execute jobs concurrently.
5. MySQL stores job details and execution logs.

---

# Key Concepts Implemented

- Job Scheduling
- Retry Mechanism
- Cron Jobs
- Asynchronous Processing
- Thread Pool Workers
- Concurrent Execution
- REST APIs
- Authentication & Authorization
- Monitoring Dashboard
- Failure Notifications

---

# API Endpoints

## Create Job
POST /jobs

## Get All Jobs
GET /jobs

## Delete Job
DELETE /jobs/{id}

## Get Execution Logs
GET /jobs/logs

## Clear Logs
DELETE /jobs/logs

## Get Failed Jobs
GET /jobs/failed

---

# Authentication

The project uses Spring Security Basic Authentication.

## Default Credentials

Username: admin  
Password: admin123

---

# How to Run

## Backend

1. Open backend in IntelliJ
2. Configure MySQL database
3. Run Spring Boot application

## Frontend

```bash
npm install
npm start