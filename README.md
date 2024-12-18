# 📋 Tasco: CLI Task Manager

**Tasco** is a powerful yet simple command-line application designed to manage your tasks right from the terminal!  
Organize your tasks, track their statuses, and keep your priorities straight—quickly and efficiently. 🚀

---

## 🌟 Features
- 🆕 **Add tasks** with custom titles, descriptions, priorities, and statuses.
- 🔍 **View tasks** in a beautifully formatted table, sorted and filtered as needed.
- ✅ **Update tasks** to mark them as complete or modify any attribute.
- 📊 **Sort and filter tasks** by priority, status, or other criteria.
- 💾 Powered by **Spring Boot** for backend management with **JPA** and **Hibernate**.
- 🖥️ Interactive CLI built with **PicoCLI** and enhanced with the **AsciiTable library**.

---

## 📦 Tech Stack
- **Java 21**: The foundation of Tasco.
- **Spring Boot**: For dependency injection, configuration, and Hibernate ORM.
- **PostgreSQL**: Used for persisting your tasks.
- **PicoCLI**: To handle the command-line interactions.
- **AsciiTable**: For rendering beautiful tables in the terminal.
- **Lombok**: For cleaner and more concise code.

---

## 🚀 Getting Started

### Prerequisites
1. Java 21 or higher installed.
2. PostgreSQL database set up.
3. Maven for managing dependencies.

---

### Installation

1. **Clone the Repository**
```bash
git clone https://github.com/yourusername/tasco.git
cd tasco
```

2. **Set Up Database**
- Create a PostgreSQL database (e.g., tasco_db).
- Update the application.yml with your database credentials:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tasco_db
    username: yourusername
    password: yourpassword
```

3. **Build the Project**
```bash
./gradlew build
```

4 **Run the Application**
```bash
./gradlew bootRun
```

## 🎮 Usage

### Adding a New Task

1. Adding a New Task
```bash
java -jar build/libs/tasco.jar add -t "Write Documentation" -d "Prepare README.md file" -p 2 -s START
```

2. Viewing Tasks
```bash
java -jar build/libs/tasco.jar show --sort desc --status START
```

3. Updating a Task
```bash
java -jar build/libs/tasco.jar update --id 1 --complete
```

4. Help
Get detailed help for any command:
```bash
java -jar build/libs/tasco.jar <command> --help
```

## 📖 Task Model

The **Task** entity represents the core structure of a task in the application:

| Field         | Type          | Description                                   |
|---------------|---------------|-----------------------------------------------|
| `id`          | `Long`        | Unique identifier for the task.               |
| `title`       | `String`      | Task title (required).                        |
| `description` | `String`      | Optional description for the task.            |
| `priority`    | `Integer`     | Task priority (e.g., 1 for high, 5 for low).  |
| `status`      | `Status` Enum | Current status of the task (`START`, `PEND`, `COMPLETE`, etc.). |
| `createdDate` | `LocalDate`   | Automatically set when the task is created.   |
| `updatedDate` | `LocalDate`   | Automatically updated when the task is modified. |

### 🎨 Status Enum Values
The `Status` enum defines the possible states of a task:

- `START`: Task is started.
- `PEND`: Task is pending.
- `SUSPEND`: Task is suspended.
- `DOWN`: Task is on hold or deprioritized.
- `FAILED`: Task failed or cannot be completed.
- `COMPLETE`: Task has been successfully completed.

## 🔧 Development Setup
To run the project locally:
1. Start your PostgreSQL database.
2. Configure your IDE with the correct JDK version (21+).
3. Run TascoApplication from your IDE.

## 🛠️ Commands Summary

This CLI application supports several commands to manage tasks efficiently. Below is a summary of the available commands:

| Command         | Description                                               | Example Usage                                            |
|------------------|-----------------------------------------------------------|---------------------------------------------------------|
| `add`           | Adds a new task to the task list.                         | `add -t "New Task" -d "Description" -p 1 -s START`      |
| `show`          | Displays tasks in a table format.                         | `show --status PEND --sort asc`                         |
| `update`        | Updates an existing task (e.g., change status).           | `update --id 1 --status COMPLETE`                       |
| `delete`        | Deletes a task by its ID.                                 | `delete --id 3`                                         |
| `clear`         | Deletes all tasks or tasks with specific criteria.        | `clear --status FAILED`                                 |

### 🔑 Command Details

#### `add`
Adds a new task with the specified options:
- `-t, --title`: Task title (required).
- `-d, --description`: Optional task description.
- `-p, --priority`: Task priority (integer).
- `-s, --status`: Task status (`START`, `PEND`, `COMPLETE`, etc.).

Example:
```bash
add -t "Write Documentation" -d "For CLI App" -p 2 -s START

## ❤️ Contributing
We welcome contributions!
Feel free to fork the repository and create a pull request with your enhancements.

## 📜 License
This project is licensed under the MIT License. See the LICENSE file for details.