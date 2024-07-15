# G7 - School Information Management System (SIMS)

**By: SLIIT/SE/OOP/2023/S2/MLB/WD/G7**

![G7 SIMS Logo](web/img/g7sims-logo.png)

## Table of Contents

- [Project Description](#project-description)
- [Features](#features)
- [Screenshots](#screenshots)
- [Setup Guide](#setup-guide)
- [Usage Instructions](#usage-instructions)
- [Contact](#contact)

## Project Description

G7 - School Information Management System (SIMS) is a simple yet functional school management application built using Java, JSP, Servlet, and MySQL. It allows university administrators to manage students, courses, instructors, and enrollments efficiently.

## Features

- **Student Management**: CRUD operations for student information.
- **Course Management**: CRUD operations for course details.
- **Instructor Management**: CRUD operations for instructor profiles.
- **Enrollment Management**: Manage student enrollments in courses.
- **Dashboard**: Summary of key metrics and visual charts.
- **Search Functionality**: Search for students and courses with ease.

## Screenshots

### Homepage

![Homepage](<screenshots/Screenshot%20(01).png>)

### Dashboard

![Dashboard](<screenshots/Screenshot%20(03).png>)

### Student Management

![Student Management](<screenshots/Screenshot%20(04).png>)

![Student Management](<screenshots/Screenshot%20(08).png>)

### Course Management

![Course Management](<screenshots/Screenshot%20(05).png>)

![Course Management](<screenshots/Screenshot%20(09).png>)

### Instructor Management

![Instructor Management](<screenshots/Screenshot%20(05).png>)

![Instructor Management](<screenshots/Screenshot%20(10).png>)

### Enrollment Management

![Enrollment Management](<screenshots/Screenshot%20(05).png>)

![Enrollment Management](<screenshots/Screenshot%20(11).png>)

### About

![Homepage](<screenshots/Screenshot%20(02).png>)

## Setup Guide

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Tomcat 8 or higher
- MySQL Server
- Maven (for dependency management)

### Installation

1. **Clone the Repository**:

   ```sh
   git clone https://github.com/Chinthana-Weligamage/G7-SIMS.git
   cd G7-SIMS
   ```

2. **Setup Database**:

   - Create a MySQL database:
     ```sql
     CREATE DATABASE school_management;
     ```
   - Import the database schema:
     ```sh
     mysql -u your-username -p school_management < database/school_management.sql
     ```

3. **Configure Database Connection**:

   - Update the `DBUtil.java` file with your MySQL credentials:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/school_management";
     private static final String USER = "your-username";
     private static final String PASSWORD = "your-password";
     ```

4. **Build and Deploy**:

   - Build the project using Maven:
     ```sh
     mvn clean install
     ```
   - Deploy the `war` file to your Tomcat server.

### Running the Application

1. Start your Tomcat server.
2. Access the application in your web browser at `http://localhost:8080/G7-SIMS`.

## Usage Instructions

1. **Home**: Access the home page and navigate to G7-SIMS Dashboard.
2. **Navigate**: Use the sidebar to navigate between Dashboard, Student Management, Course Management, Instructor Management, and Enrollment Management.
3. **Manage Records**: Perform CRUD operations as needed.
4. **View Dashboard**: View summary statistics and charts.

## Contact

For any inquiries or issues, please contact:

- Name: [Your Name]
- Email: [chinthanaweligamage1@gmail.com]
- GitHub: [https://github.com/Chinthana-Weligamage](https://github.com/Chinthana-Weligamage)
