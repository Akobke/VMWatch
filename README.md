# Homelab VM Monitor

A self-hosted monitoring tool for tracking resource usage across virtual machines in a homelab environment. Built with Spring Boot and PostgreSQL.

## What It Does

The monitor collects CPU, memory, and disk usage from VMs on your network and stores historical data in PostgreSQL. A lightweight agent runs on each VM and exposes a `/metrics` endpoint. The central backend polls each agent on a schedule, aggregates the data, and provides a REST API for querying metrics.

## Architecture

```
   ┌─────────────┐       ┌─────────────┐       ┌─────────────┐
   │   Agent     │       │   Agent     │       │   Agent     │
   │  (VM #1)    │       │  (VM #2)    │  ...  │  (VM #N)    │
   │  /metrics   │       │  /metrics   │       │  /metrics   │
   └──────▲──────┘       └──────▲──────┘       └──────▲──────┘
          │                     │                     │
          └─────────────────────┼─────────────────────┘
                                │
                    ┌───────────┴───────────┐
                    │  Monitoring Backend   │
                    │   (Spring Boot)       │
                    └───────────┬───────────┘
                                │
                         ┌──────▼──────┐
                         │  PostgreSQL │
                         └─────────────┘
```

The backend uses a **pull model** — agents are passive HTTP servers that don't need to know the backend exists. Adding a new VM to monitoring is two steps: deploy the agent and register the machine in the backend.

## Tech Stack

- **Backend**: Java 17+, Spring Boot, Spring Data JPA
- **Agent**: Java 17+, Spring Boot, OSHI (for reading OS-level metrics)
- **Database**: PostgreSQL
- **Frontend**: React (planned)

## Prerequisites

- Java 17 or later
- PostgreSQL (local or remote)
- Maven

## Getting Started

### Database Setup

Create a database and user in PostgreSQL:

```sql
CREATE DATABASE monitorhub;
CREATE USER monitor_user WITH PASSWORD 'your_password_here';
GRANT ALL PRIVILEGES ON DATABASE monitorhub TO monitor_user;
\c monitorhub
GRANT ALL ON SCHEMA public TO monitor_user;
```

### Backend

1. Clone the repo
2. Configure your database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://<host>:5432/monitorhub
spring.datasource.username=monitor_user
spring.datasource.password=your_password_here
spring.jpa.hibernate.ddl-auto=update
```

3. Build and run:

```bash
cd monitoring-backend
./mvnw spring-boot:run
```

The backend starts on port 8080 by default.

### Agent

1. Deploy the agent JAR to each VM you want to monitor
2. Run:

```bash
java -jar monitoring-agent.jar
```

The agent exposes a `/metrics` endpoint on port 5674 by default.

3. Register the machine in the backend:

```bash
curl -X POST localhost:8080/api/machines \
  -H "Content-Type: application/json" \
  -d '{"hostname": "192.168.1.50", "friendlyName": "Media Server", "port": 5674}'
```

## API Overview

### Machine Management

| Method | Path                  | Description              |
|--------|-----------------------|--------------------------|
| POST   | `/api/machines`       | Register a new machine   |
| GET    | `/api/machines`       | List all machines        |
| GET    | `/api/machines/{id}`  | Get a single machine     |
| PATCH  | `/api/machines/{id}`  | Update a machine         |
| DELETE | `/api/machines/{id}`  | Remove a machine         |

### Metrics

| Method | Path                                         | Description                        |
|--------|----------------------------------------------|------------------------------------|
| GET    | `/api/machines/{id}/metrics/latest`          | Latest reading for a machine       |
| GET    | `/api/machines/{id}/metrics?from=...&to=...` | Historical metrics, paginated      |
| GET    | `/api/metrics/latest`                        | Latest reading for all machines    |

### Agent

| Method | Path       | Description                   |
|--------|------------|-------------------------------|
| GET    | `/metrics` | Current CPU, memory, disk     |

## Project Status

This project is under active development as a learning exercise.

- [x] Project design and architecture
- [x] Backend CRUD for machines
- [x] Machine-Metric entity relationship
- [x] Agent with live OS metrics
- [x] Scheduled polling
- [x] Historical metrics queries
- [ ] React frontend
- [ ] Alerting
- [ ] CI/CD pipeline


## License

Personal project. No license specified.
