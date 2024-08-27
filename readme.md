# Profcon (Professionals + Contacts)

## Description
This project is about one application which applies some concepts about clean code and clean architecture. This system is composed for two main domains: 
 * Professionals
 * Contacts

## Prerequisites

Ensure that the following software is installed on your system:

- **Java 21+**: You can download it from [here](https://adoptopenjdk.net/).  
- **Maven 3+**: Download and install it from [here](https://maven.apache.org/download.cgi).  
- **Spring Boot 3+**: The Spring Boot dependencies will be managed via Maven.  
- **Docker**: Install Docker from [here](https://www.docker.com/products/docker-desktop).  
- **Docker Compose**: Docker Compose is usually bundled with Docker Desktop. You can check for its installation and get it from [here](https://docs.docker.com/compose/install/).

## Installation and Setup

### 1. Clone the Repository

Clone the project repository to your local machine:

```bash
git clone https://github.com/your-repository/project-name.git
cd project-name
```

### 2. Start the database service and project

2.1 - Execute the following command on the root-project path

```bash
docker-compose up -d
```

Obs: The database should be created automatically. However, if it does not happen you can follow these steps below for creating the database manually

A - Connect to the database service on the address: `jdbc:postgresql://localhost:15432/postgres` with username and password equals `admin`

B - Create one database called `profcon`

2.2 - Execute the project with your preferred IDE