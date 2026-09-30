# 📚 Library Management System

A Java Swing-based desktop application designed to streamline library inventory management, member borrowing workflows, and automated late fine calculations. Built using modular Object-Oriented Programming (OOP) principles.

## 🔗 Repository & Source Code
- **GitHub Repository:** [Library Management System](https://github.com/puja24197/library-management-system)

## 🛠️ Architecture & Core Features

### 🏛️ System Architecture
The application follows a modular architecture separating the GUI views, service layers, and core data models:
- **Base Abstract Model (`LibraryItems.java`):** Enforces data contracts and encapsulates common asset properties (`id`, `title`, `availableCopies`, etc.).
- **Subclasses (`Book.java`, `Magazine.java`):** Extend the base class to represent specific media types with dedicated parameters like Author or Publisher.
- **Service Layer (`LibraryService.java`):** Handles core business logic, including validations, title searches, issue limits, and return calculations.
- **Persistence Layer (`FileManager.java`):** Manages read/write synchronization with local file storage (`Mylibrary_data.txt`).

## ✨ Key Features

- 📖 **Catalog Management:** Add, update, view, and delete Books and Magazines.
- 🎯 **Category Filtering:** Filter items seamlessly across Books, Magazines, or the entire inventory.
- 👥 **Role-Based Borrow Limits:**
  - **Students:** Max 3 items, 15-day borrow limit.
  - **Faculty:** Max 5 items, 30-day borrow limit.
- 💰 **Automated Late Fine System:** Calculates daily fines (20 BDT/day) upon item return based on due dates.
- 🌐 **External Integration:** Integrated Wikipedia browsing for quick topic searches via system browser.
- ⚠️ **Custom Exception Handling:** Dedicated runtime exception package (`InvalidInputException`, `ItemNotFoundException`, `MemberNotFoundException`) to handle edge cases smoothly.

## 🚀 Recent Improvements & Bug Fixes

- **Flexible Member ID Validation:** Removed strict `M`-prefix constraints in `LibraryService.java` to support standard numeric Member IDs.
- **Swing UI Layout Fixes:** Resolved component shrinkage using proper `GridBagLayout` weight constraints (`weightx`) and alignment properties.
- **Button & Table Styling:** Applied custom `BasicButtonUI` and customized table header renderers for clean visual contrast.
- **Case-Insensitive Search:** Improved item title lookup logic to prevent false `ItemNotFoundException` errors.

## 🛠️ Tech Stack & Prerequisites

- **Language:** Java (JDK 8 or higher)
- **GUI Framework:** Java Swing (`javax.swing`)
- **IDE:** IntelliJ IDEA
- **Version Control:** Git & GitHub
## 👥 Contributors & Team
Puja Rani Paul — Leader

Nazia Tazkia — Team Member

Thamina Islam Tenni — Team Member
