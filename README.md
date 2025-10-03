# Expenses API

A **Spring Boot REST API** for managing users and expenses, built with **Hexagonal Architecture**, **MongoDB**, and **JWT-based authentication**.
This project is meant as a study/practice project, but follows production-grade patterns (clean separation of concerns, DTO validation, Dockerized database, semantic versioning).

---

## ✨ Features

* User registration (`POST /api/v1/users`)
* JWT authentication (stateless security layer)
* Password hashing with `BCryptPasswordEncoder`
* Request validation using `jakarta.validation`
* Semantic versioning
* Hexagonal architecture (domain, application, infrastructure layers)
* MongoDB persistence (via Spring Data Mongo)
* Docker Compose support for local DB

---

## 🏗️ Architecture

The project follows a **Hexagonal Architecture (Ports & Adapters)** style:

```
src/main/java/com/br/expenses
├── application         # Business logic & services (use cases)
│   └── service
├── domain              # Core domain (entities, value objects, ports, exceptions)
│   └── model
│   └── port.out        # Repository abstractions
│   └── exceptions
├── infrastructure      # Adapters & configs (MongoDB, security, web controllers)
│   └── config
│   └── ports.in
│   └── model
```

---

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot 3.x**
* **Spring Data MongoDB**
* **Spring Security (JWT)**
* **Docker + Docker Compose** (MongoDB)

---

## 🚀 Getting Started

### Prerequisites

* JDK 21+
* Maven 3+
* Docker & Docker Compose

### Clone repository

```bash
git clone https://github.com/your-username/expenses-api.git
cd expenses-api
```

### Start MongoDB with Docker

```bash
docker compose up -d
```

### Run the API

```bash
./mvnw spring-boot:run
```

The API will be available at:
👉 `http://localhost:8080/api/v1`

---

## 🔐 Security

* Passwords are hashed with `BCryptPasswordEncoder`.
* Authentication is JWT-based (stateless).
* Example flow:

    * `POST /api/v1/users` → register
    * `POST /api/v1/auth/login` → get JWT
    * Use `Authorization: Bearer <token>` header for protected endpoints

---

## 📦 Example Request

**Register User**

```http
POST /api/v1/users
Content-Type: application/json

{
  "name": "Richard",
  "email": "printsmande@gmail.com",
  "password": "MyPassword1!",
  "passwordConfirmation": "MyPassword1!"
}
```

**Example Response**

```json
{
  "status": 201,
  "version": "01.02.01",
  "data": {
    "id": "6584d589-a418-404a-8911-81348412eec7",
    "name": "Test User",
    "email": "test@email.com"
  },
  "timestamp": "2025-10-02T11:56:26.027544717Z",
  "_links": {
    "previous": { "method": "GET", "href": "http://localhost:8080/users", "rel": "all-users" },
    "current": { "method": "POST", "href": "http://localhost:8080/users", "rel": "create-user" },
    "next": { "method": "GET", "href": "http://localhost:8080/users/6584d589-a418-404a-8911-81348412eec7", "rel": "get-user" }
  }
}
```

---

## 📖 Versioning

This project follows **Semantic Versioning**:

```
MAJOR.MINOR.PATCH
```

* `MAJOR` → breaking changes (contract changes, endpoint renames)
* `MINOR` → new features (new endpoints, add capabilities)
* `PATCH` → bug fixes or small improvements

Current version: **01.02.01**

---

## 📌 Roadmap

* [x] User registration (`POST /users`)
* [ ] JWT authentication
* [ ] Expense management endpoints
* [ ] User roles (admin/user)
* [ ] Pagination & filtering

---

## 📜 License

MIT License © 2025 Richard Nascimento

---
