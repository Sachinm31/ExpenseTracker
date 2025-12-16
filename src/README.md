# Personalized Expense Analyzer Dashboard

A full-stack web application for tracking expenses, managing budgets, and generating spending reports. Built with **Spring Boot**, **React**, and **MySQL**.

---

## 📋 Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Database Schema](#database-schema)
- [API Endpoints](#api-endpoints)
- [Running the Application](#running-the-application)
- [Testing with Postman](#testing-with-postman)
- [Frontend Setup](#frontend-setup)
- [Resume Highlights](#resume-highlights)

---

## ✨ Features

- ✅ **User Management** - Create and manage user accounts
- ✅ **Transaction Tracking** - Record income and expenses with categories
- ✅ **Budget Management** - Set and track monthly budgets
- ✅ **Weekly/Monthly Reports** - Analyze spending patterns by category
- ✅ **RESTful API** - Clean, scalable backend architecture
- ✅ **Modular Design** - Separated transaction and reporting logic
- ✅ **CORS Enabled** - Support for cross-origin requests

---

## 🛠 Tech Stack

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Framework
- **Spring Data JPA** - ORM for database operations
- **MySQL 8.0** - Relational database
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management

### Frontend (Optional)
- **React.js** - UI library
- **Axios** - HTTP client
- **Recharts** - Data visualization
- **Tailwind CSS** - Styling

### Tools
- **IntelliJ IDEA** - IDE
- **Postman** - API testing
- **Git** - Version control

---

## 📁 Project Structure

```
expense-tracker/
├── src/main/java/com/expense_tracker/
│   ├── entity/                    # JPA entities
│   │   ├── User.java
│   │   ├── Transaction.java
│   │   ├── TransactionType.java
│   │   └── Budget.java
│   ├── repository/                # Data access layer
│   │   ├── UserRepository.java
│   │   ├── TransactionRepository.java
│   │   └── BudgetRepository.java
│   ├── service/                   # Business logic
│   │   ├── TransactionService.java
│   │   ├── BudgetService.java
│   │   └── ReportingService.java
│   ├── controller/                # REST endpoints
│   │   ├── UserController.java
│   │   ├── TransactionController.java
│   │   ├── BudgetController.java
│   │   └── ReportingController.java
│   ├── dto/                       # Data transfer objects
│   │   ├── TransactionDto.java
│   │   ├── BudgetDto.java
│   │   ├── SpendingSummaryDto.java
│   │   └── UserDto.java
│   └── ExpenseTrackerApplication.java
├── src/main/resources/
│   └── application.properties      # Configuration
├── pom.xml                         # Maven dependencies
└── README.md
```

---

## 📦 Prerequisites

- **Java 17 or higher**
- **Maven 3.8+**
- **MySQL 8.0+**
- **Git**
- **Postman** (for API testing)

---

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```bash
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker
```

### Step 2: Create MySQL Database

Open MySQL Command Line and run:

```sql
CREATE DATABASE expense_db;
```

### Step 3: Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/expense_db
spring.datasource.username=root
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false

logging.level.com.expense_tracker=DEBUG
```

### Step 4: Build the Project

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
mvn spring-boot:run
```

Or run from IntelliJ:
- Right-click `ExpenseTrackerApplication.java` → Run

The application will start on **http://localhost:8080**

---

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  full_name VARCHAR(255),
  active BOOLEAN DEFAULT TRUE
);
```

### Transactions Table
```sql
CREATE TABLE transactions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  date DATE NOT NULL,
  amount DOUBLE NOT NULL,
  category VARCHAR(100),
  description VARCHAR(255),
  type VARCHAR(50) NOT NULL,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

### Budgets Table
```sql
CREATE TABLE budgets (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  month INT,
  year INT,
  amount DOUBLE,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

## 📡 API Endpoints

### Users API
```
GET    /api/users              - Get all users
POST   /api/users              - Create new user
GET    /api/users/{id}         - Get user by ID
DELETE /api/users/{id}         - Delete user
```

### Transactions API
```
GET    /api/transactions       - Get all transactions
POST   /api/transactions       - Create new transaction
GET    /api/transactions/{id}  - Get transaction by ID
DELETE /api/transactions/{id}  - Delete transaction
```

### Budgets API
```
GET    /api/budgets            - Get all budgets
POST   /api/budgets            - Create/Update budget
GET    /api/budgets/{id}       - Get budget by ID
DELETE /api/budgets/{id}       - Delete budget
```

### Reports API
```
GET    /api/reports/weekly     - Get weekly spending summary
GET    /api/reports/monthly    - Get monthly spending summary
```

---

## 🧪 Testing with Postman

### 1. Create a User

```http
POST http://localhost:8080/api/users
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123",
  "fullName": "John Doe",
  "active": true
}
```

### 2. Create a Transaction

```http
POST http://localhost:8080/api/transactions
Content-Type: application/json

{
  "date": "2025-12-16",
  "amount": 500,
  "category": "Food",
  "description": "Lunch",
  "type": "EXPENSE",
  "user": {
    "id": 1
  }
}
```

### 3. Get Weekly Report

```http
GET http://localhost:8080/api/reports/weekly
```

**Response:**
```json
{
  "totalExpense": 500.0,
  "totalIncome": 0.0,
  "categoryTotals": {
    "Food": 500.0
  }
}
```

### 4. Create Budget

```http
POST http://localhost:8080/api/budgets
Content-Type: application/json

{
  "month": 12,
  "year": 2025,
  "amount": 5000,
  "user": {
    "id": 1
  }
}
```

---

## 🎨 Frontend Setup (Optional)

### Create React App

```bash
npx create-react-app expense-dashboard
cd expense-dashboard
npm install axios recharts
```

### Update `.env`
```
REACT_APP_API_BASE_URL=http://localhost:8080/api
```

### Example API Call

```javascript
import axios from 'axios';

const api = axios.create({
  baseURL: process.env.REACT_APP_API_BASE_URL
});

// Get transactions
api.get('/transactions').then(res => {
  console.log(res.data);
});
```

---

## 💼 Resume Highlights

Add these bullet points to your resume:

- **Personalized Expense Analyzer Dashboard** – Full-stack application built with Spring Boot, React, and MySQL
- Implemented **RESTful APIs** for transaction management, budget tracking, and spending analysis
- Designed **relational database schema** (User, Transaction, Budget) with proper foreign keys and indexes
- Created **modular backend architecture** separating transaction and reporting logic for maintainability
- Built **React dashboard** with Recharts for visualizing spending patterns (pie charts, bar charts, line graphs)
- Implemented **weekly/monthly spending summaries** with category-wise breakdown and budget vs actual comparison

---

## 🔄 Git Workflow

### Initial Commit

```bash
git init
git add .
git commit -m "Initial commit: Expense Tracker backend setup"
git branch -M main
git remote add origin https://github.com/yourusername/expense-tracker.git
git push -u origin main
```

### Regular Commits

```bash
# Feature branch
git checkout -b feature/add-jwt-auth
git add .
git commit -m "feat: Add JWT authentication"
git push origin feature/add-jwt-auth

# Create pull request on GitHub
```

---

## 🐛 Troubleshooting

### Port 8080 already in use
```bash
# Find process using port 8080
lsof -i :8080

# Kill the process
kill -9 <PID>
```

### MySQL connection refused
- Ensure MySQL is running
- Check credentials in `application.properties`
- Verify database exists: `CREATE DATABASE expense_db;`

### No tables in database
- Set `spring.jpa.hibernate.ddl-auto=update` in properties
- Restart the application to auto-create tables

---

## 📚 Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Recharts Documentation](https://recharts.org/)

---

## 📄 License

This project is open source and available under the MIT License.

---

## 👤 Author

**Your Name** - Aspiring Full-Stack Java Developer
- GitHub: [@yourusername](https://github.com/yourusername)
- LinkedIn: [Your Profile](https://linkedin.com/in/yourprofile)

---

## 🙏 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

**Happy Coding! 🚀**
