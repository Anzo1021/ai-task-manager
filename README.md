# 🤖 AI Task Manager

A full-stack web application that uses **Google Gemini AI** to automatically analyze tasks and generate a summary, priority level, and category — so you don't have to think about it!

---

## ✨ Features

- 📝 Add tasks in one click
- 🤖 AI auto-generates summary, priority and category using Gemini
- 🔴 Priority detection — HIGH / MEDIUM / LOW
- 🏷️ Auto category — Academic, Work, Personal, Health, Finance
- 🗑️ Delete tasks
- 💾 All data saved to MySQL database
- 🌐 Clean responsive frontend

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Backend | Java, Spring Boot |
| AI | Google Gemini API |
| Database | MySQL + Spring Data JPA + Hibernate |
| Frontend | HTML, CSS, JavaScript |

---

## 📁 Project Structure
src/
├── main/
│   ├── java/com/eanjali/ai_task_manager/
│   │   ├── controller/
│   │   │   └── TaskController.java
│   │   ├── model/
│   │   │   ├── Task.java
│   │   │   ├── TaskRequest.java
│   │   │   └── TaskResponse.java
│   │   ├── repository/
│   │   │   └── TaskRepository.java
│   │   └── services/
│   │       ├── TaskService.java
│   │       └── AIService.java
│   └── resources/
│       └── static/
│           └── index.html
---

## ⚙️ Setup Instructions

### 1. Clone the repo
```bash
git clone https://github.com/Anzo1021/ai-task-manager.git
cd ai-task-manager
```

### 2. Create MySQL database
```sql
CREATE DATABASE ai_task_manager;
```

### 3. Configure application.properties
Create `src/main/resources/application.properties` and add:
```properties
spring.application.name=ai-task-manager

spring.datasource.url=jdbc:mysql://localhost:3306/ai_task_manager
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

gemini.api.key=your_gemini_api_key
```

### 4. Get Gemini API Key
- Go to [https://aistudio.google.com](https://aistudio.google.com)
- Click **Get API Key**
- Copy and paste into `application.properties`

### 5. Run the app
Open project in IntelliJ and click **Run**

### 6. Open frontend
Open browser and go to:




http://localhost:8080/
---

## 📸 How It Works

1. User types a task → e.g. `"Submit college project"`
2. Backend sends task to **Gemini AI**
3. Gemini returns summary, priority and category as JSON
4. Data is saved to **MySQL**
5. Frontend displays all tasks with AI analysis

---

## 🔑 Important

Never commit your `application.properties` file — it contains your API key and database password.
This file is already added to `.gitignore` for safety.

---

## 👩‍💻 Author

**Anjali Jaiswal**  
[GitHub](https://github.com/Anzo1021)
