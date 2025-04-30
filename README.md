# Blogging_application
A full-featured Blogging Platform built using *Spring Boot, **Spring Security, **JPA/Hibernate, . This application allows users to register, create and manage blog posts, and perform CRUD operations with authentication and role-based access.

##Features
User Registration and Login
Role-based Access (Admin/User)
Create, Read, Update, Delete (CRUD) blog posts
Category management
REST API support
Secure authentication using Spring Security

##Tech Stack
Backend: Java, Spring Boot, Spring Security, Spring Data JPA
Frontend: reactjs
Database: MySQL
Build Tool: Maven

##Prerequisites
Java 17+
Maven
MySQL Server
basics od spring Framework
springboot
STS IDE

##Project Setup Guide: Blogging Project 1.Set Up MySQL Database

Open your MySQL client (e.g., MySQL Workbench, CLI).
Create a new database: CREATE DATABASE blog_app;
2.Configure application.properties

Navigate to src/main/resources/application.properties :

spring.datasource.url=jdbc:mysql://localhost:3306/blog_app 
spring.datasource.username=your_mysql_username 
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true

3.Build and Run the Application

From the root of the project directory, run: mvn spring-boot:run This will start the Spring Boot server on:

http://localhost:8080

4.Access the Application Open a browser and visit:http://localhost:8080/ or you can use PostMan To access Apis

###Project Structure
com.blog
├── config               # Security and application configuration
├── controller           # REST controllers
├── dto                  # Data Transfer Objects
├── entity               # JPA entities (User, Post, Role)
├── exception            # Custom exceptions and global handler
├── payload              # API response models
├── repository           # Spring Data JPA repositories
├── service              # Service interfaces and implementations
│   └── impl             # Business logic implementations
├── security             # JWT and authentication logic
resources/
├── templates            
├── static              
└── application.properties

###Dependencies
All the required Dependencies are mentioned in pom.xml
