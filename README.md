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

