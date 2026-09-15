# Library Management System
This is a Java project for a Library Management System created using OOP concepts.
## Task 1: Core Implementation

I have implemented the main abstract class `LibraryItems.java`. This class acts as a base model for all library items.

### OOP Concepts Applied:
* Abstraction: Created the `LibraryItems` abstract class and defined an abstract method `getDetails()`.
* Encapsulation: Made variables like `id`, `title`, `totalCopies`, `availableCopies`, `isIssued`, and `studentId` private, and used public getter/setter methods.
* Logic: Added `issueToStudent()` and `returnFromStudent()` methods to handle copy counts and student issuance states.

## Tools Used
* Java
* IntelliJ IDEA
* Git bash & GitHub
* Docs

## Author
* Puja Paul - Task 1 (LibraryItems Abstract Class)
  
## Task 1: Book Class Implementation

The `Book.java` class was implemented by me by extending the `LibraryItems` class.

### OOP Concepts Applied:
* Inheritance: The `LibraryItems` class was extended so that its common properties and methods could be used in the `Book` class.
* Polymorphism: The `getDetails()` method was overridden to display book-specific information.
* Encapsulation: The `author` field was kept private, and getter and setter methods were used to access it.
* Constructor: The book title and total number of copies were initialized using the parent class constructor.

## Contributor
* Nazia Tazkia - Task 1 (Book Class Implementation)

  ## Task 1: Magazine Class Implementation
  The Magazine.java class was implemented by me by extending the LibraryItems class.
### OOP Concepts Applied:
* Inheritance: The LibraryItems class was extended so that its common properties and methods could be used in the Magazine class.
* Polymorphism: The getDetails() method was overridden to display magazine-specific information.
* Encapsulation: The publisher field was kept private, and getter method was used to access it.
* Constructor: The magazine title and total number of copies were initialized using the parent class constructor.

### Contributor
* Thamina Islam Tenni - Task 1 (Magazine Class Implementation)

## Task 2: File Handling 

I have implemented the `FileManager.java` class. This class is used to save and load library data from a text file.

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
* Puja Paul - Task 2 (File Handling)


##Task 2 CRUD Operations 

I have handle CRUD to manage a library catalog, track borrowed books, and save all data automatically to a local file.

I implemented complete CRUD operations inside the `LibraryService` class:

* **Create — `registerItem(LibraryItems item)`:** I wrote this method to add new books or magazines. I added validation logic to block empty titles, invalid copy counts, or duplicate records.
* **Read — `showCatalog()`:** I created this method to display the full inventory list on the console.
* **Read — `searchByTitle(String title)`:** I implemented case-insensitive searching to easily find specific items by name.
* **Update — `processIssue(...)`:** I built this to lower the available copy count whenever a student borrows a book.
* **Delete — `removeItem(String title)`:** I added this functionality to permanently delete an item from the catalog using its title.


## Data Management Strategy

* **Purpose:** I designed the system around CRUD logic because a functional library requires basic tools to add new stock, update borrowed items, and clean up old records.
* **Execution:** I stored active records in an `ArrayList` inside `LibraryService`. After every add, update, or delete action, I trigger `FileManager.saveData(catalog)` to keep my text file updated.

## Tools and Technologies Used

* **Java & ArrayList:** I used Java for the application logic and `ArrayList` for in-memory data management.
* **`CustomException`:** I built custom error handling to print clear error messages for bad inputs.
* **FileManager Integration:** I connected my service class with `FileManager` to load data on startup and auto-save changes.
* **IntelliJ IDEA & Git/GitHub:** I used IntelliJ IDEA as my IDE and Git/GitHub for branch management and version control.

## Project Setup and Execution

1. Clone or download my repository.
2. Open the project in IntelliJ IDEA.
3. Run `Main.java` to test adding items, issuing books, viewing the catalog, and saving data.
