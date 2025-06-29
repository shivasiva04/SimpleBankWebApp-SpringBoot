# 💸 Simple Bank Web App - Spring Boot

This is a simple banking web application built using **Spring Boot**, **Thymeleaf**, **Tailwind CSS**, and **PostgreSQL**.  
It allows users to:

- 🧑 Create bank accounts  
- 💰 Deposit money  
- 💸 Withdraw money  
- 📄 View transaction history  

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Thymeleaf** (server-side templating)
- **Tailwind CSS** (modern responsive styling)
- **PostgreSQL** (database)
- **JPA/Hibernate** (ORM)

---

## 🚀 Features

- Create new accounts with initial balance  
- Deposit & withdraw with modal forms  
- Transaction tracking (date, type, amount)  
- Stylish UI using Tailwind CSS  
- Error handling (e.g. insufficient balance)
- Dynamic balance update and animation effects

---

## 🖼️ UI Preview

> 📸 Add screenshots here after running the app.

---

## 🧑‍💻 Getting Started

### 1️⃣ Clone the repo
git clone https://github.com/shivasiva04/SimpleBankWebApp-SpringBoot.git

```bash
2️⃣ Setup Database (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/bank_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the App
mvn spring-boot:run
