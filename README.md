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



## Task 2: Book Class Implementation

The `Book.java` class was implemented by me by extending the `LibraryItems` class.

### OOP Concepts Applied:
* Inheritance: The `LibraryItems` class was extended so that its common properties and methods could be used in the `Book` class.
* Polymorphism: The `getDetails()` method was overridden to display book-specific information.
* Encapsulation: The `author` field was kept private, and getter and setter methods were used to access it.
* Constructor: The book title and total number of copies were initialized using the parent class constructor.

## Contributor

* Nazia Tazkia - Task 2 (Book Class Implementation)

  ## Task 3: Magazine Class Implementation

  The Magazine.java class was implemented by me by extending the LibraryItems class.
### OOP Concepts Applied:
* Inheritance: The LibraryItems class was extended so that its common properties and methods could be used in the Magazine class.
* Polymorphism: The getDetails() method was overridden to display magazine-specific information.
* Encapsulation: The publisher field was kept private, and getter method was used to access it.
* Constructor: The magazine title and total number of copies were initialized using the parent class constructor.

### Contributor

* Thamina Islam Tenni - Task 3 (Magazine Class Implementation)  
