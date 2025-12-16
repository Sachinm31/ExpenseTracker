

```md
# 💰 ExpenseTracker (Backend)

A **Spring Boot backend application** for tracking expenses, managing budgets, and generating spending insights.  
Built with **clean REST APIs**, **layered architecture**, and **MySQL persistence**.

🔗 **Repository:** https://github.com/Sachinm31/ExpenseTracker

---

## 🚀 What this project does
- Manage users and their financial data
- Record income and expense transactions
- Maintain monthly budgets
- Generate weekly spending summaries by category
- Expose clean, production-style REST APIs

---

## 🛠 Tech Stack
**Backend**
- Java 17  
- Spring Boot 3.2  
- Spring Data JPA (Hibernate)  
- MySQL  
- Lombok  
- Maven  

---

## 🏗 Architecture & Design
- Layered architecture:  
  **Controller → Service → Repository → Database**
- DTO-based API contracts
- Business logic isolated in service layer
- Centralized exception handling using `@ControllerAdvice`
- CORS enabled for frontend integration

---

## 📡 API Overview

### Users
```

GET    /api/users
POST   /api/users
GET    /api/users/{id}
DELETE /api/users/{id}

```

### Transactions
```

GET    /api/transactions
POST   /api/transactions
GET    /api/transactions/{id}
DELETE /api/transactions/{id}

```

### Budgets
```

GET    /api/budgets
POST   /api/budgets
GET    /api/budgets/{id}
DELETE /api/budgets/{id}

```

### Reports
```

GET /api/reports/weekly

````

---

## 🗄 Database Model (High-level)
- **User** → one-to-many → **Transactions**
- **User** → one-to-many → **Budgets**
- **Transaction**: date, amount, category, type (INCOME / EXPENSE)
- **Budget**: monthly budget per user

---

## ▶️ Run Locally

### Prerequisites
- Java 17+
- Maven
- MySQL

### Setup
```bash
git clone https://github.com/Sachinm31/ExpenseTracker.git
cd ExpenseTracker
````

Create database:

```sql
CREATE DATABASE expense_db;
```

Set DB password:

```bash
DB_PASSWORD=your_mysql_password
```

Run application:

```bash
mvn clean spring-boot:run
```

App runs on:

```
http://localhost:8080
```

---

## 🧪 Example API Call

```http
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

## 💼 Resume Highlights

* Developed a **Spring Boot backend** for expense and budget management
* Designed **RESTful APIs** with proper HTTP semantics
* Implemented **weekly spending analytics** with category-wise aggregation
* Applied **clean architecture principles** and centralized error handling
* Integrated **MySQL persistence** using Spring Data JPA

---

## 👤 Author

**Sachin**
Backend / Java Developer

* GitHub: [https://github.com/Sachinm31](https://github.com/Sachinm31)
* LinkedIn: [https://www.linkedin.com/in/sachinm31/](https://www.linkedin.com/in/sachinm31/)

  

```

