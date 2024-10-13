Here's a sample `README.md` file for your **Job Application Service** built with **Spring Boot** and **MySQL** in a **monolithic architecture**.

---

# Job Application Service

## Overview

This is a **Job Application Service** built using **Spring Boot** in a monolithic architecture, with **MySQL** as the database. The service allows users to:

- Create and manage companies.
- Add and manage jobs under those companies.
- Write and manage reviews for the jobs.

You must first create a company, then add jobs under that company. Once jobs are created, users can add, view, and delete reviews for those jobs.

## Features

- **Company Management**:
  - Create a company.
  - Delete a company by its `id`.
  - Retrieve a company by its `id`.

- **Job Management**:
  - Create a job under a company.
  - Delete a job by its `id`.
  - Retrieve a job by its `id`.

- **Review Management**:
  - Add a review for a job.
  - Delete a review by its `id`.
  - Retrieve all reviews for a job by its `id`.

## Technologies Used

- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **Architecture**: Monolithic
- **ORM**: Hibernate (JPA)

## Setup and Installation

### Prerequisites

1. **Java**: Make sure Java 17 or later is installed.
2. **Maven**: Ensure Maven is installed for building and managing dependencies.
3. **MySQL**: You will need a running MySQL database instance.

### Installation Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo/job-application-service.git
   cd job-application-service
   ```

2. **Create MySQL Database**:
   Create a MySQL database called `job_application_db` (or any name of your choice).
   ```sql
   CREATE DATABASE job_application_db;
   ```

3. **Configure Database Connection**:
   Update the `src/main/resources/application.properties` file with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/job_application_db
   spring.datasource.username=your-username
   spring.datasource.password=your-password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. **Build the Project**:
   Build the project using Maven:
   ```bash
   mvn clean install
   ```

5. **Run the Application**:
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

6. **Access the API**:
   The application will be available at `http://localhost:8080`.

## API Endpoints

### Company Endpoints

- **Create a Company**:
  - **POST** `/companies`
  - Request Body (JSON):
    ```json
    {
      "name": "Company Name",
      "description": "Company Description"
    }
    ```

- **Get a Company by ID**:
  - **GET** `/companies/{id}`

- **Delete a Company by ID**:
  - **DELETE** `/companies/{id}`

### Job Endpoints

- **Create a Job**:
  - **POST** `/companies/{companyId}/jobs`
  - Request Body (JSON):
    ```json
    {
      "title": "Job Title",
      "description": "Job Description",
      "salary": 50000
    }
    ```

- **Get a Job by ID**:
  - **GET** `/jobs/{id}`

- **Delete a Job by ID**:
  - **DELETE** `/jobs/{id}`

### Review Endpoints

- **Create a Review for a Job**:
  - **POST** `/jobs/{jobId}/reviews`
  - Request Body (JSON):
    ```json
    {
      "comment": "Great job opportunity!",
      "rating": 5
    }
    ```

- **Get All Reviews for a Job**:
  - **GET** `/jobs/{jobId}/reviews`

- **Delete a Review by ID**:
  - **DELETE** `/reviews/{id}`

## Database Schema

The application uses a relational database structure in MySQL with the following key tables:

1. **Company Table**:
   - `id`: Primary key
   - `name`: Company name
   - `description`: Company description

2. **Job Table**:
   - `id`: Primary key
   - `title`: Job title
   - `description`: Job description
   - `salary`: Job salary
   - `company_id`: Foreign key referencing the Company table

3. **Review Table**:
   - `id`: Primary key
   - `comment`: Review comment
   - `rating`: Rating out of 5
   - `job_id`: Foreign key referencing the Job table

## Testing

To test the endpoints, you can use tools like **Postman** or **cURL** to send requests to the API.

### Example cURL Commands:

- Create a Company:
  ```bash
  curl -X POST http://localhost:8080/companies \
    -H "Content-Type: application/json" \
    -d '{"name": "TechCorp", "description": "A leading tech company."}'
  ```

- Add a Job:
  ```bash
  curl -X POST http://localhost:8080/companies/1/jobs \
    -H "Content-Type: application/json" \
    -d '{"title": "Software Engineer", "description": "Develop web applications.", "salary": 70000}'
  ```

- Add a Review:
  ```bash
  curl -X POST http://localhost:8080/jobs/1/reviews \
    -H "Content-Type: application/json" \
    -d '{"comment": "Amazing work environment!", "rating": 5}'
  ```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

Feel free to modify this README file as per your project details or requirements!
