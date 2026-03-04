# MyNotesApp 📝

**MyNotesApp** is a simple and secure note-taking web application built with **Spring Boot**.  
It allows users to register, log in, and manage their own notes. Each user's notes are private, secured with **JWT-based authentication**.  

This project demonstrates a basic implementation of REST APIs, Spring Security, JWT, and database operations with Spring Data JPA.

---

## 🔹 Features

- **User Authentication**
  - Register new users with a username and password
  - Log in and receive a JWT token for authentication
- **Note Management**
  - Create, view, edit, and delete notes
  - Notes are linked to individual users — users cannot access others' notes
- **Security**
  - JWT-based stateless authentication
  - Passwords hashed with BCrypt
  - Secured endpoints with role-based access control (optional admin endpoint)

---

## 💻 Technologies Used

- **Backend:** Java, Spring Boot  
- **Database:** MySQL  
- **Security:** Spring Security, JWT  
- **ORM:** Spring Data JPA (Hibernate)  

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or later
- Maven or Gradle
- Git
- A database (H2 for in-memory, or MySQL/PostgreSQL for persistent storage)

---

### Installation

1. **Clone the repository**

```bash
git clone https://github.com/JosiahSia28/MyNotesApp.git
cd MyNotesApp
