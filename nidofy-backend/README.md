# Nidofy Backend Folder Guide

This is a guide for the files and folders here.

#### Docker
`docker-compose.yml` - The docker container config

`Dockerfile`         - The docker container file

#### Source code
`src/main/java/`
```
├── configs/       - Spring Boot configurations
├── controllers/
│   ├── pub/       - Endpoints under /public
│   └── priv/      - All other endpoints
├── data/          - Data objects
├── dtos/          - Request mapping objects
├── exceptions/    - Custom exceptions and global exception handler
├── models/        - Database entities
├── repositories/  - Repos for database entities
├── responses/     - Response objects (like DTOs but for output)
├── runners/       - Startup code
└── services/      - Core services (email, authentication, etc.)
```