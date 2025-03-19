# **Auth Service Demo**

## Overview
This project demonstrates a simple authentication service built with **Spring Boot**. It provides user authentication features such as registration, login, JWT token management, and password recovery.

## Prerequisites
Before setting up the project, ensure you have the following installed:
- **Git**: For cloning the repository.
- **Docker & Docker Compose**: For containerizing and running the application.

## Setting Up the Project

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/PialKanti/SpringAuthService.git
cd SpringAuthService
```

### 2. Build the Application
Once you have the project, you can either run the application directly using Docker Compose or follow the manual steps outlined below to run it locally.

### 3. Running the Application with Docker Compose
**Prerequisite:** Ensure Docker is installed and running on your system.

To build and run the application using Docker Compose, execute the following command. This will start the containers in the background:

```bash
docker compose up -d
```

- The application will be accessible at http://localhost:8080.
- Docker Compose will automatically build the necessary images and start the required containers, including any dependencies such as databases, Redis, or other services.

#### Stopping the Application

To stop the application without removing the containers, run:

```bash
docker compose stop
```
This will stop the containers but retain the state of the services. You can restart them later using `docker compose start`.

#### Removing Containers and Volumes
To stop and remove the containers and networks defined in your Docker Compose configuration, use:

```bash
docker compose down
```

This command will stop the containers and remove them, but will not remove the volumes. Any persistent data (such as databases) will be preserved.
\
\
If you want to remove both containers and volumes (along with any persistent data), use:

```bash
docker compose down -v
```

This will completely remove the containers and associated volumes.

## Accessing Swagger UI
Once the application is running, you can access the Swagger UI for API documentation and testing the endpoints:

- **Swagger UI:** *[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)*

This link will provide you with an interactive API interface where you can see all the available endpoints and make requests directly.

## Running Locally Without Docker Compose (Optional)
If you prefer to run the application locally without Docker, you can follow these steps:

### 1. Build the Project
You can build the project using Maven or Maven Wrapper:

```bash
./mvnw clean install -DskipTests
```

### 2. Run the Application
Once the project is built, run the application using the following command:

```bash
./mvnw spring-boot:run
```

The application will be available at *[http://localhost:8080](http://localhost:8080)*, and you can access Swagger UI at:
- **Swagger UI:** *[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)*

## Features
- **User Registration:** Register a new user with basic details.
- **User Login:** Authenticate a user and obtain JWT access and refresh tokens.
- **User Logout:** Invalidate the refresh token and delete it from cookies.
- **Access Token Refresh:** Refresh the access token using the stored refresh token.
- **Email Verification:** Verify the user's email address via a verification token.
- **Forgot Password:** Send a password reset email to the user.
- **Reset Password:** Reset the user's password using a reset token.

## **Contributing**
Contributions are welcome! Feel free to open an issue or submit a pull request.