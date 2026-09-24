import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Exception.MemberNotFoundException;
import Model.Book;
import Model.LibraryItems;
import Model.LibraryService;

public class Main {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();

        System.out.println("================================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM - TEST");
        System.out.println("================================================");

        // =================================================
        // 1. CREATE
        // =================================================
        System.out.println("\n========== 1. ADDING BOOKS ==========");

        try {

            Book book1 = new Book(
                    "Java Basics",
                    3,
                    "James Gosling"
            );

            Book book2 = new Book(
                    "Python Programming",
                    5,
                    "Guido van Rossum"
            );

            Book book3 = new Book(
                    "C++ Programming",
                    4,
                    "Bjarne Stroustrup"
            );

            Book book4 = new Book(
                    "Data Structures",
                    6,
                    "Mark Allen Weiss"
            );

            Book book5 = new Book(
                    "Database Systems",
                    2,
                    "Raghu Ramakrishnan"
            );

            libraryService.registerItem(book1);
            libraryService.registerItem(book2);
            libraryService.registerItem(book3);
            libraryService.registerItem(book4);
            libraryService.registerItem(book5);

            System.out.println("5 books added successfully!");

        } catch (InvalidInputException e) {

            System.out.println(
                    "Invalid Input: " + e.getMessage()
            );
        }

        // =================================================
        // 2. READ
        // =================================================
        System.out.println("\n========== 2. SHOW CATALOG ==========");

        libraryService.showCatalog();

        // =================================================
        // 3. SEARCH
        // =================================================
        System.out.println("\n========== 3. SEARCH TEST ==========");

        LibraryItems found =
                libraryService.searchByTitle("Java Basics");

        if (found != null) {

            System.out.println("Book found!");

            System.out.println(
                    "Title: " + found.getTitle()
            );

            System.out.println(
                    "Total Copies: "
                            + found.getTotalCopies()
            );

            System.out.println(
                    "Available Copies: "
                            + found.getAvailableCopies()
            );

        } else {

            System.out.println("Book not found!");
        }

        // =================================================
        // 4. UPDATE
        // =================================================
        System.out.println(
                "\n========== 4. UPDATE BOOK TEST =========="
        );

        try {

            libraryService.updateBook(
                    "Java Basics",
                    "Advanced Java",
                    5,
                    "Herbert Schildt"
            );

            System.out.println(
                    "Update completed successfully!"
            );

        } catch (ItemNotFoundException e) {

            System.out.println(
                    "Item Not Found: " + e.getMessage()
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Invalid Input: " + e.getMessage()
            );
        }

        // =================================================
        // 5. ISSUE
        // =================================================
        System.out.println(
                "\n========== 5. ISSUE BOOK TEST =========="
        );

        try {

            libraryService.processIssue(
                    "Advanced Java",
                    "M001",
                    "student@gmail.com",
                    "Student",
                    10,
                    0
            );

            System.out.println(
                    "Issue operation completed successfully!"
            );

        } catch (ItemNotFoundException e) {

            System.out.println(
                    "Item Not Found: " + e.getMessage()
            );

        } catch (MemberNotFoundException e) {

            System.out.println(
                    "Member Not Found: " + e.getMessage()
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Invalid Input: " + e.getMessage()
            );
        }

        // =================================================
        // 6. RETURN
        // =================================================
        System.out.println(
                "\n========== 6. RETURN BOOK TEST =========="
        );

        try {

            libraryService.returnItem(
                    "Advanced Java",
                    20
            );

            System.out.println(
                    "Return operation completed successfully!"
            );

        } catch (ItemNotFoundException e) {

            System.out.println(
                    "Item Not Found: " + e.getMessage()
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Invalid Input: " + e.getMessage()
            );
        }

        // =================================================
        // 7. DELETE
        // =================================================
        System.out.println(
                "\n========== 7. DELETE BOOK TEST =========="
        );

        try {

            libraryService.removeItem(
                    "Database Systems"
            );

            System.out.println(
                    "Database Systems deleted successfully!"
            );

        } catch (ItemNotFoundException e) {

            System.out.println(
                    "Item Not Found: " + e.getMessage()
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Invalid Input: " + e.getMessage()
            );
        }

        // =================================================
        // 8. DUPLICATE TEST
        // =================================================
        System.out.println(
                "\n========== 8. DUPLICATE BOOK TEST =========="
        );

        try {

            Book duplicateBook = new Book(
                    "Advanced Java",
                    10,
                    "Another Author"
            );

            libraryService.registerItem(
                    duplicateBook
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }

        // =================================================
        // 9. INVALID INPUT TEST
        // =================================================
        System.out.println(
                "\n========== 9. INVALID BOOK TEST =========="
        );

        try {

            Book invalidBook = new Book(
                    "Invalid Book",
                    0,
                    "Unknown Author"
            );

            libraryService.registerItem(
                    invalidBook
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }

        // =================================================
        // 10. NULL ITEM TEST
        // =================================================
        System.out.println(
                "\n========== 10. NULL ITEM TEST =========="
        );

        try {

            libraryService.registerItem(null);

        } catch (InvalidInputException e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }

        // =================================================
        // 11. SEARCH NON-EXISTING ITEM
        // =================================================
        System.out.println(
                "\n========== 11. SEARCH NON-EXISTING BOOK =========="
        );

        LibraryItems notFound =
                libraryService.searchByTitle(
                        "Harry Potter"
                );

        if (notFound == null) {

            System.out.println(
                    "Correct: Book was not found."
            );

        } else {

            System.out.println(
                    "Error: Book should not exist."
            );
        }

        // =================================================
        // 12. FINAL CATALOG
        // =================================================
        System.out.println(
                "\n========== 12. FINAL CATALOG =========="
        );

        libraryService.showCatalog();

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "             ALL TESTS COMPLETED"
        );

        System.out.println(
                "================================================"
        );
    }
}