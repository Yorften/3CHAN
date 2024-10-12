 # 3CHAN

A simple blog web application coded with Jakarta EE and Hibernate.

## Table of Contents

- [Project Overview](#project-overview)
- [Installation](#installation)
- [Structure](#structure)
- [Features](#features)
- [Technologies](#technologies)


## Project Overview

**Context**:  
3CHAN is an open forum designed for unfiltered conversations and creative expression. Whether you're discussing the latest trends, sharing your thoughts, or just looking for a space to post anonymously!

**Objectives**:
- Develop a Java web application using Servlets, JSP, JSTL and Hibernate for database query calls.
- Implement CRUD operations for authors, articles and comments.
- Adapt the existing database for web use.
- Build an intuitive user interface.
- Follow agile project management practices using Scrum.

## Installation

### Prerequisites

- Java 8 or higher
- Apache Maven
- MySQL Server
- Apache Tomcat v9.x

### Setup your database:

1. Ensure your MySQL server is running.
2. Navigate to the directory containing `Database.sql`.
3. Run the following command to create the database and tables:
   ```bash
   mysql -u your_username -p your_database < Database.sql

### Setup environment variable

1. **For windows:**
   ```cmd
   set DB_URL= **Your MySQL URL**
   set DB_USER= "dbadmin"
   set DB_PASSWORD = "azerty"

2. **For linux based:**
   ```bash
   export DB_URL= **Your MySQL URL**
   export DB_USER= "dbadmin"
   export DB_PASSWORD = "azerty"


### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Yorften/3CHAN.git
   cd 3CHAN

2. **Build the application:**
   ```sh
   mvn clean install

3. **Compile the application:**
   ```sh
   mvn package

4. **Deploy:**

- Deploy the WAR file in Apache Tomcat by copying the WAR from the /target folder to Tomcat's webapps directory.
- Start the Tomcat server and access the application in your browser at http://localhost:8080/3CHAN.

5. **Run with Eclipse/IntelliJ IDEA (optional)**

- Open the project in your ide.
- Build maven dependencies.
- Run the app or server.

## Structure

- **Model Layer**:  
  Defines entities such as `Author`, `Article`, `Comment` with JPA. These represent the application's core business domain.
  
- **Controller Layer**:  
  Handles user interactions, receives input from the UI, and communicates with the Service layer to process the requests.
  
- **Repository Layer**:  
  Provides an abstraction for data access. This layer interacts with the database using JDBC and Hibernate ORM to handles CRUD operations.
  
- **Service Layer**:  
  Contains business logic and orchestrates operations between the Controller and Repository layers.
  
- **Presentation Layer**:  
  Uses JSP and JSTL for rendering views and handling front-end user interactions.

## Features

1. **Articles Management**:
   - Create, update, delete, and search articles.

2. **Authors Management**:
   - Create, update and delete authors.
   - Assign roles to authors such as `Contributor` or `Editor`.

3. **Comments Management**:
   - List an article comments with pagination.
   - Add, update, and remove comments.

4. **Advanced Features**:
   - User-friendly and responsive UI built with Tailwind CSS.
   - View pending comments awaiting approval.
   - Interactive coment editing.

## Technologies

- **Java 8**: Core language used for development.
- **Servlets, JSP, JSTL**: For handling requests, business logic, and rendering views.
- **Apache Maven**: For dependency management and project build.
- **MySQL**: Database for storing application data.
- **JDBC**: Database connectivity.
- **Hibernate**: Database query builder AKA an ORM.
- **Apache Tomcat**: Web server for deploying the application.
- **JUnit**: For unit testing.
- **Mockito**: For mocking classes to unit test.
- **Tailwind CSS**: For responsive UI design.
- **JIRA**: For project management using Scrum methodology.
- **Git**: For version control with branches.