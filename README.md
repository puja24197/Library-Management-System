# Library Management System

This is a Java project for a Library Management System created using OOP concepts.

## Task 1: Core Implementation

I have implemented the main abstract class `LibraryItems.java`. This class acts as a base model for all library items.

### OOP Concepts Applied:
* **Abstraction:** Created the `LibraryItems` abstract class and defined an abstract method `getDetails()`.
* **Encapsulation:** Made variables like `id`, `title`, `totalCopies`, `availableCopies`, `isIssued`, and `studentId` private, and used public getter/setter methods.
* **Logic:** Added `issueToStudent()` and `returnFromStudent()` methods to handle copy counts and student issuance states.

## Tools Used
* Java
* IntelliJ IDEA
* Git Bash & GitHub
* Docs

## Author
* Puja Rani Paul - Task 1 (LibraryItems Abstract Class)

---

## Task 1: Book Class Implementation

The `Book.java` class was implemented by me by extending the `LibraryItems` class.

### OOP Concepts Applied:
* **Inheritance:** The `LibraryItems` class was extended so that its common properties and methods could be used in the `Book` class.
* **Polymorphism:** The `getDetails()` method was overridden to display book-specific information.
* **Encapsulation:** The `author` field was kept private, and getter and setter methods were used to access it.
* **Constructor:** The book title and total number of copies were initialized using the parent class constructor.

## Contributor
* Nazia Tazkia - Task 1 (Book Class Implementation)

---

## Task 1: Magazine Class Implementation

The `Magazine.java` class was implemented by me by extending the `LibraryItems` class.

### OOP Concepts Applied:
* **Inheritance:** The `LibraryItems` class was extended so that its common properties and methods could be used in the `Magazine` class.
* **Polymorphism:** The `getDetails()` method was overridden to display magazine-specific information.
* **Encapsulation:** The `publisher` field was kept private, and a getter method was used to access it.
* **Constructor:** The magazine title and total number of copies were initialized using the parent class constructor.

### Contributor
* Thamina Islam Tenni - Task 1 (Magazine Class Implementation)

---

# Task 2: File Handling

I have implemented the `Model.FileManager.java` class. This class is used to save and load library data from a text file.

### Features Implemented:

* `saveData()` method saves all Books and Magazines into a file named `Mylibrary_data.txt`.
* `loadData()` method reads the saved data from the file when the program starts.
* Book and Magazine information is stored in comma-separated format.
* If the data file does not exist, the program starts with an empty list.
* Used exception handling to show error messages if there is a problem reading or writing the file.

### File Format:
* Book: `BOOK,Title,TotalCopies,Author`
* Magazine: `MAGAZINE,Title,TotalCopies,Publisher`

## Author
* Puja Rani Paul - Task 2 (File Handling)



# Task 2: CRUD Operations

I have handled CRUD operations to manage a library catalog, track borrowed books, and automatically save all data to a local file.

I implemented CRUD operations inside the `LibraryService` class:

* **Create — `registerItem(LibraryItems item)`:** Adds new books or magazines to the library catalog. Validation is included to prevent empty titles, invalid copy counts, and duplicate records.

* **Read — `showCatalog()`:** Displays the complete library inventory on the console.

* **Read — `searchByTitle(String title)`:** Searches for a specific library item by title using case-insensitive comparison.

* **Update — `updateBook(...)`:** Updates the information of a specific book, including its title, total number of copies, and author. The updated information is automatically saved to the data file.

* **Delete — `removeItem(String title)`:** Deletes a specific library item from the catalog using its title and automatically updates the data file.

* **Issue — `processIssue(...)`:** Processes the borrowing of a library item and updates its available copy information.

## Data Management Strategy

* **Purpose:** The system uses CRUD operations to provide the basic functionality required for managing library items, including adding, viewing, updating, issuing, and deleting records.

* **Execution:** Active library records are stored in an `ArrayList` inside `LibraryService`. After every create, update, issue, or delete operation, `Model.FileManager.saveData(catalog)` is called to synchronize the in-memory data with the local text file.

## Tools and Technologies Used

* **Java & ArrayList:** Java is used for application logic and `ArrayList` is used for in-memory data management.
* **Custom Exceptions:** Custom exceptions are used to provide clear error messages for invalid inputs and missing records.
* **Model.FileManager Integration:** `LibraryService` is connected with `Model.FileManager` to load data when the application starts and automatically save changes.
* **IntelliJ IDEA & Git/GitHub:** IntelliJ IDEA is used as the development environment, while Git/GitHub is used for version control and branch management.

## Project Setup and Execution

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Run `Main.java`.
4. Test adding, searching, updating, deleting, and issuing library items.
5. Verify that changes are automatically saved to `Mylibrary_data.txt`.


   ## 🛠️Task 3: Project Refactoring & Architecture 

I refactored the codebase into a clean, modular architecture separating the data layer,business logic, and presentation layer (GUI).
### 📁 Package Structure
* **`Model/`**: Contains core data models and file handling logic.
  * `LibraryItems.java`: Abstract base class for all library items.
  * `Book.java` & `Magazine.java`: Concrete implementations of items.
  * `LibraryService.java`: Business logic layer for operations (add, issue, return).
  * `FileManager.java`: File persistence layer for saving and loading data.
* **`LibraryGUI/`**: Contains Swing-based User Interface components.
  * `ViewCatalogPanel.java`: GUI component for searching, displaying, and filtering all library items.
### 🎨 GUI Highlights
* **Catalog View (`ViewCatalogPanel`)**:
  * Displays books and magazines dynamically in a tabular format.
  * Interactive UI components for seamless navigation and item lookup.

## Contributors

* Puja Rani Paul - Task 1 (LibraryItems Abstract Class)
* Nazia Tazkia - Task 1 (Book Class Implementation)
* Thamina Islam Tenni - Task 1 (Magazine Class Implementation)
* Puja Rani Paul - Task 2 (File Handling+Exception)
* Nazia Tazkia - Task 2 (CRUD Operations)
* **Thamina Islam Tenni - Task 2 (Update and Delete Operations)**
* Puja Rani Paul - (ViewCatalogPanel)


## 🛠️ Features & Technical Implementation

Here is a breakdown of the core components and features implemented in MainFrame.java and IssueItemPanel.java:

### 1. Main Infrastructure & Navigation (MainFrame.java)
* *Swing GUI Framework:* Built using Java Swing (JFrame, JTabbedPane) for a clean desktop graphical user interface.
* *Tabbed Interface:* Integrated multiple panel modules (ViewCatalogPanel, AddItemPanel, IssueItemPanel) into a single window for intuitive navigation.
* *Dynamic Event Listening (ChangeListener):* Implemented tab change listeners to automatically refresh the library catalog whenever the "View Catalog" tab is selected.
* *Thread-Safe Launching:* Used SwingUtilities.invokeLater() to safely initialize and render the GUI on the Event Dispatch Thread (EDT).

### 2. Issue Item Module (IssueItemPanel.java)
* *Interactive Form Elements:* Utilized JTextField, JComboBox, JLabel, and JButton structured inside a clean GridLayout(6, 2) format.
* *Data Input Processing:* Captures essential borrowing details including Item Title, Member ID, Email, Member Type (Student/Faculty), and Current Borrowed Count.
* *Robust Exception Handling:*
  * *Number Format Validation:* Prevents crashes by catching NumberFormatException if non-numeric values are entered for borrow count.
  * *Custom Domain Exceptions:* Safely catches and displays alerts for ItemNotFoundException, MemberNotFoundException, and InvalidInputException.
* *User Feedback & State Reset:* Displays interactive pop-up alerts (JOptionPane) for success and error states, and automatically resets form input fields via a helper method clearFields().

---

### 💻 Technologies & Libraries Used
* *Language:* Java
* *UI Framework:* Java Swing & AWT (javax.swing.*, java.awt.*)
* *Architecture:* Modular Component-based Design (Service & Panel Separation)
*Nazia tazkia (MainFrame.java,IssueItemPanel.java)
