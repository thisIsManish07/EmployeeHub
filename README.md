# EmployeeHub

A secure **Employee Directory Application** built as part of the JD Software coding assessment.  
This project demonstrates full-stack development with **Spring Boot (Java 17)** for backend and **Angular 16** for frontend, including authentication, authorization, and employee management.

---

## 🚀 Tech Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Security 6 (JWT + RBAC)
- JPA/Hibernate
- MySQL (or H2 for simplicity in local dev)
- Maven

### Frontend
- Angular 16
- Angular Material
- RxJS
- JWT handling via HTTP Interceptors
- Guards (AuthGuard, AdminGuard)

---

## ⚙️ Features
- **Authentication**
  - Email/Password → JWT issued by backend
- **Role-based Access**
  - `ADMIN`: Full CRUD on employees
  - `USER`: Read-only access
- **Employee Directory**
  - Paginated, searchable, and sortable employee list
  - View employee details
- **Security**
  - BCrypt password hashing
  - CORS setup
  - Auto logout on token expiry

---

## 📂 Project Structure
EmployeeHub/
├── BackendApp/
│ └── EmployeeHub/ # Spring Boot backend
└── Frontend/
└── EmployeeHub_Frontend/ # Angular frontend

### 1. Backend (Spring Boot)

cd BackendApp/EmployeeHub
./mvnw spring-boot:run
Runs at: http://localhost:8080

DB: H2 (in-memory) by default

2. Frontend (Angular)
cd Frontend/EmployeeHub_Frontend
npm install
ng serve --open


Runs at: http://localhost:4200

Environment Variables

JWT_SECRET → Secret key for signing JWTs

DB configs → Set in application.properties

###  📡 API Endpoints

🔐 Authentication
Method	   Endpoint	         Description	Roles
POST  	/api/auth/register	 Register a new user	Public
POST	  /api/auth/login    	 Login with email/password → JWT	Public

👥 Employees

Method	   Endpoint    	       Description	Roles
GET	     /api/employees	      Get paginated list of employees	USER, ADMIN
GET  	  /api/employees/{id}	  Get employee details by ID	USER, ADMIN
POST	  /api/employees	      Create a new employee	ADMIN only
PUT	   /api/employees/{id}	  Update employee details	ADMIN only
DELETE	/api/employees/{id}	   Delete employee	ADMIN only

📌 Submission Notes

Built with Java 17 + Spring Boot 3, Angular 16

Includes authentication (JWT), RBAC, and CRUD features

~10 sample employees preloaded for demo

 All code and implementation are my own.

👤 Author
Manish Nayak
GitHub: thisIsManish07
