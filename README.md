# School‑System

A **Student & Course Management System** built with Spring Boot (Java).  
Provides CRUD for students and courses, JWT-based authentication, student-course assignments, and statistics tracking.

---

##  Key Features

- **User registration & login** with JWT authentication.  
- **Full CRUD** for Students using DTO + Mapper + Validation.  
- **Full CRUD** for Courses using DTO + Mapper.  
- Many-to-Many relationship: Students can enroll in multiple courses.  
- View students in each course and manage enrollments.  
- **Statistics**: student average grades, highest grade per course, student rank in a course.  
- Uses **Spring Data JPA** with PostgreSQL, includes **pagination** for searches.  
- Clean separation between entities and API response objects (DTOs).

---
**Tech Stack**
- Language : Java 21
- Framework : Spring Boot
- Database : PostgreSQL
- Authentication : JWT (JSON Web Token)
- DTO Mapping : Manual Mapper (StudentMapper / CourseMapper /EnrollmentMapper)
- ORM : Hibernate & JPA
- Build : Maven
---
**Clone the repository**:  
   ```bash
   git clone https://github.com/KareemEmadElfrargi/School-System.git

   cd School-System
```
---
**Set up PostgreSQL database**:
 ```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/studentdb1
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
```
---
**Run the application**
then Test the API using **Postman** or any similar tool
---
**Notes** 
1. All endpoints except ```/auth/**``` require a valid JWT token in the ```Authorization```
2. Replace {BaseURL} with your local server URL (e.g., http://localhost:8090).

## API Endpoints ExamplesAPI Endpoints Examples
- Endpoints
```bash
POST {BaseURL}/api/v1/auth/register
```
- Request body
```bash 
{
  "email": "yourEmail",
  "password": "yourPassword",
  "role": "USER"
}
```
→ Returns JWT token . 
---
- to get all students use :
```bash
GET {BaseURL}/api/v1/students
```
- to add a new student use :
```bash
POST {BaseURL}/api/v1/students
```
- Request body
```bash
{
  "name": "John Doe",
  "email": "john@example.com",
  "age": 22
}
```
- Get student by ID
```bash
GET {BaseURL}/api/v1/students/{id}
```
- Update student :
```bash
PUT {BaseURL}/api/v1/students/{id}
```
- Request body
```bash
{
  "name": "John Updated",
  "email": "johnupdated@example.com",
  "age": 23
}
```
- Delete student :
```bash
DELETE {BaseURL}/api/v1/students/{id}
```
- Assign courses to a student :
```bash
POST {BaseURL}/api/v1/students/{id}/courses
```
- Request body
```bash
[1, 2, 3]
```
- Search students with pagination :
```bash
GET {BaseURL}/api/v1/students/search?name=John&page=0&size=10
```
---
- Get all courses :
```bash
GET {BaseURL}/api/v1/courses

```
- Add a new course :
```bash
POST {BaseURL}/api/v1/courses
```
- Request body
```bash
{
  "name": "Java Basics",
  "description": "Learn fundamentals of Java"
}
```
- Update course :
```bash
PUT {BaseURL}/api/v1/courses/id?id={id}
```
- Request body
```bash
{
  "name": "Advanced Java",
  "description": "Deep dive into Java concepts"
}
```
- Delete course :
```bash
DELETE {BaseURL}/api/v1/courses/id?id={id}
```
- Get all students enrolled in a course :
```bash
GET {BaseURL}/api/v1/courses/{id}/students
```
---
- Get student’s average grade :
```bash
GET {BaseURL}/api/v1/enrollments/student/{studentId}/average
```
- Get highest grade in a course :
```bash
GET {BaseURL}/api/v1/enrollments/course/{courseId}/highest
```
- Get student rank in a course:
```bash
GET {BaseURL}/api/v1/enrollments/course/{courseId}/student/{studentId}/rank
```
---

**MIT License © 2025 ```Kareem Emad```**

