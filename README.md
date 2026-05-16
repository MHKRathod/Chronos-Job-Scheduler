# Chronos - Distributed Job Scheduler

Chronos is a full-stack distributed job scheduling and monitoring system built using React.js, Spring Boot, Spring Security, and MySQL.

The application allows users to securely authenticate, create scheduled jobs, monitor execution logs, retry failed jobs, and track system activity through a real-time dashboard.

---

# Features

## Authentication & Security
- User Signup and Login
- Spring Security integration
- BCrypt password encryption
- Database-backed authentication
- Protected frontend routes
- Protected REST APIs

## Job Scheduling
- Create and manage scheduled jobs
- Recurring job execution using cron expressions
- Concurrent job execution using thread pool workers
- Asynchronous processing using Spring Async
- Retry mechanism for failed jobs

## Monitoring & Dashboard
- Real-time job monitoring dashboard
- Execution logs tracking
- Failed job notifications
- Auto-refreshing job status updates

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- Spring Scheduler
- Spring Async
- Spring Data JPA
- Hibernate
- MySQL

## Frontend
- React.js
- React Router DOM
- Axios
- Tailwind CSS

---

# System Architecture

The system follows a layered full-stack architecture:

1. React frontend sends REST API requests.
2. Spring Boot backend handles authentication and job management.
3. Scheduler continuously scans pending jobs.
4. Worker threads execute jobs asynchronously.
5. Execution logs and job details are stored in MySQL.
6. Dashboard displays real-time job status and monitoring information.

---

# Key Concepts Implemented

- Distributed Job Scheduling
- Cron-based Scheduling
- Retry Mechanism
- Asynchronous Processing
- Thread Pool Workers
- Concurrent Execution
- REST APIs
- Authentication & Authorization
- Protected Routes
- Monitoring Dashboard
- Failure Notifications

---

# API Endpoints

## Authentication

### Signup
POST /auth/signup

Example Request:

```json
{
  "username": "hari",
  "password": "1234",
  "role": "ADMIN"
}
```

# How to Run

## Backend Setup

1. Open backend project in IntelliJ IDEA
2. Configure MySQL database in `application.properties`

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chronos
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

3. Run the Spring Boot application

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

## Frontend Setup

1. Open frontend folder in terminal

2. Install dependencies

```bash
npm install
```

3. Start React application

```bash
npm start
```

Frontend runs on:

```text
http://localhost:3000
```

---

# Application Flow

1. Open frontend application
2. Signup using a new account
3. Login using credentials
4. Access protected dashboard
5. Create and monitor jobs
6. View logs and failed notifications
7. Logout securely