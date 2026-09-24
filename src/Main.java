import Exception.*;
import Model.*;
import Exception.*;

public class Main {

    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();

        System.out.println("================================================");
        System.out.println("       LIBRARY MANAGEMENT SYSTEM - TEST");
        System.out.println("================================================");


        // =====================================================
        // 1. ADD 5 BOOKS
        // =====================================================

        System.out.println("\n========== 1. ADDING 5 BOOKS ==========");

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

        } catch (Exception e) {
            System.out.println("Error while adding books: "
                    + e.getMessage());
        }


        // =====================================================
        // 2. SHOW CATALOG
        // =====================================================

        System.out.println("\n========== 2. SHOW CATALOG ==========");

        libraryService.showCatalog();


        // =====================================================
        // 3. SEARCH BOOK
        // =====================================================

        System.out.println("\n========== 3. SEARCH TEST ==========");

        LibraryItems found =
                libraryService.searchByTitle("Java Basics");

        if (found != null) {

            System.out.println("Book found!");

            System.out.println(
                    "Title: " + found.getTitle()
            );

            System.out.println(
                    "Total Copies: " + found.getTotalCopies()
            );

            System.out.println(
                    "Available Copies: "
                            + found.getAvailableCopies()
            );

        } else {

            System.out.println("Book not found!");
        }


        // =====================================================
        // 4. UPDATE BOOK
        // =====================================================

        System.out.println("\n========== 4. UPDATE BOOK TEST ==========");

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

        } catch (Exception e) {

            System.out.println(
                    "Update failed: " + e.getMessage()
            );
        }


        // =====================================================
        // 5. SHOW CATALOG AFTER UPDATE
        // =====================================================

        System.out.println(
                "\n========== 5. CATALOG AFTER UPDATE =========="
        );

        libraryService.showCatalog();


        // =====================================================
        // 6. DELETE ONE BOOK
        // =====================================================

        System.out.println("\n========== 6. DELETE BOOK TEST ==========");

        try {

            libraryService.removeItem(
                    "Database Systems"
            );

            System.out.println(
                    "Database Systems deleted successfully!"
            );

        } catch (Exception e) {

            System.out.println(
                    "Delete failed: " + e.getMessage()
            );
        }


        // =====================================================
        // 7. SHOW CATALOG AFTER DELETE
        // =====================================================

        System.out.println(
                "\n========== 7. CATALOG AFTER DELETE =========="
        );

        libraryService.showCatalog();


        // =====================================================
        // 8. TEST DUPLICATE BOOK
        // =====================================================

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

        } catch (Exception e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }


        // =====================================================
        // 9. TEST INVALID BOOK - 0 COPIES
        // =====================================================

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

        } catch (Exception e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }


        // =====================================================
        // 10. TEST NULL ITEM
        // =====================================================

        System.out.println(
                "\n========== 10. NULL ITEM TEST =========="
        );

        try {

            libraryService.registerItem(null);

        } catch (Exception e) {

            System.out.println(
                    "Expected Exception Caught: "
                            + e.getMessage()
            );
        }


        // =====================================================
        // 11. TEST SEARCH NON-EXISTING BOOK
        // =====================================================

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


        // =====================================================
        // 12. FINAL CATALOG
        // =====================================================

        System.out.println(
                "\n========== 12. FINAL CATALOG =========="
        );

        libraryService.showCatalog();


        // =====================================================
        // END
        // =====================================================

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