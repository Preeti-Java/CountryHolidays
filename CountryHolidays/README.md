# Spring Boot Project

## Table of Contents
- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running the Project](#running-the-project)
- [Testing with JUnit](#testing-with-junit)
- [Accessing Swagger UI](#accessing-swagger-ui)
- [Using Postman](#using-postman)
- [REST API Endpoints](#rest-api-endpoints)
- [Troubleshooting](#troubleshooting)


## Introduction
This is a Spring Boot project that demonstrates the use of REST APIs to manage country and holiday-related information. It uses Spring Boot, Spring Data JPA, Hibernate, H2 (in-memory) database, and Springdoc OpenAPI for Swagger documentation. The project also includes JUnit 4 test cases for the main controller and service layers.

## Prerequisites
- Java 17 or higher
- Postman (for API testing)
- An IDE (e.g., IntelliJ, Eclipse, or STS)

## Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repository-url.git
   
2. Navigate to the project directory:
cd your-project-directory

3. Build the project:
mvn clean install

Running the Project
------------------------------------------------------------------
1. Start the Spring Boot application:
 mvn spring-boot:run
 By default, the application will start on http://localhost:8080.
 
Testing with JUnit
------------------------------------------------------------------
JUnit test cases are located in the src/test/java folder. To run the JUnit tests, use the following command:
mvn test

Accessing Swagger UI
-----------------------------------------------------------------
The project uses Springdoc OpenAPI for API documentation. After running the application, you can access the Swagger UI at:

http://localhost:8080/swagger-ui.html

This page provides a comprehensive view of all available REST API endpoints with their input and output formats.

Using Postman (postman.File)
----------------------------------------------------------------
To test the REST API endpoints, import the following sample requests into Postman.
Testing with Postman
Import the JSON requests into Postman or manually create them in Postman.
Run a POST request to create a country and then a holiday associated with that country.
Test other endpoints like GET, PUT, and DELETE as defined below.


Troubleshooting
Swagger UI not loading: Ensure you have the correct dependencies (springdoc-openapi-starter-webmvc-ui) in your pom.xml and that the application is running.
Database issues: If using H2, ensure you are using the correct settings in your application.properties or application.yml. If using another database, update the configuration accordingly.
Unit Tests Failing: Check if you have annotated your test classes correctly and if all dependencies are correctly injected using mocks.


Author: Preeti Rani