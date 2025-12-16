
# ExpenseTracker – Backend (Spring Boot)

A backend-focused expense tracking system that enables users to record transactions, manage budgets, and generate spending insights. Built using Spring Boot with a clean, layered architecture and MySQL persistence.

Repository: [https://github.com/Sachinm31/ExpenseTracker](https://github.com/Sachinm31/ExpenseTracker)

---

## Key Features

- User management (CRUD)
- Income and expense tracking with categories
- Budget management
- Weekly spending summary with category-wise breakdown
- RESTful APIs with proper HTTP status codes
- Centralized exception handling
- CORS enabled for frontend integration

---

## Tech Stack

- Java 17
- Spring Boot 3.2
- Spring Data JPA (Hibernate)
- MySQL
- Lombok
- Maven

---

## Architecture

Layered architecture following:

**Controller → Service → Repository → Database**

- DTOs used to decouple API contracts from entities  
- Business logic isolated in service layer  
- Global exception handling using `@ControllerAdvice`

---

## API Endpoints

### Users

- `GET    /api/users`  
- `POST   /api/users`  
- `GET    /api/users/{id}`  
- `DELETE /api/users/{id}`  

### Transactions

- `GET    /api/transactions`  
- `POST   /api/transactions`  
- `GET    /api/transactions/{id}`  
- `DELETE /api/transactions/{id}`  

### Budgets

- `GET    /api/budgets`  
- `POST   /api/budgets`  
- `GET    /api/budgets/{id}`  
- `DELETE /api/budgets/{id}`  

### Reports

- `GET    /api/reports/weekly`  

---

## Database Design

- User has one-to-many relationship with Transactions and Budgets  
- Transaction stores date, amount, category, and type (INCOME / EXPENSE)  
- Budget tracks monthly budget per user  

Relationships are enforced using foreign keys.

---

## Run Locally

### Prerequisites

- Java 17+
- Maven
- MySQL

### Steps

```
git clone https://github.com/Sachinm31/ExpenseTracker.git
cd ExpenseTracker
```

Create database:

```
CREATE DATABASE expense_db;
```

Set environment variable:

```
DB_PASSWORD=your_mysql_password
```

Run the application:

```
mvn clean spring-boot:run
```

Application runs at:  
`http://localhost:8080`

---

## Sample API Request

```
POST /api/transactions
Content-Type: application/json

{
  "date": "2025-12-16",
  "amount": 500,
  "category": "Food",
  "type": "EXPENSE",
  "user": { "id": 1 }
}
```

---

## Resume Highlights

- Built a Spring Boot backend for expense tracking using REST APIs and MySQL  
- Designed clean layered architecture with DTOs and service abstraction  
- Implemented weekly spending analytics with category-wise aggregation  
- Centralized error handling using global exception handler  
- Followed production-ready backend practices  

---

## Author

Sachin  
GitHub: [https://github.com/Sachinm31](https://github.com/Sachinm31)  
LinkedIn: [https://www.linkedin.com/in/sachinm31/](https://www.linkedin.com/in/sachinm31/)
```
