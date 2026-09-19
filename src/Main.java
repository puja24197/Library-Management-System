import Exception.*;

public class Main {
    public static void main(String[] args) {
        LibraryService libraryService = new LibraryService();

        System.out.println("============== 1. VALID ITEM REGISTRATION =============");
        try {
            // ১. সঠিকভাবে একটি বই যোগ করার চেষ্টা
            Book book1 = new Book("Java Basics", 7, "Pagol"); // আপনার Book কনস্ট্রাক্টর অনুযায়ী প্যারামিটার অ্যাডজাস্ট করবেন
            libraryService.registerItem(book1);

            Book book2 = new Book("Python Programming", 4, "Goru");
            libraryService.registerItem(book2);
        } catch (Exception e) {
            System.out.println("Registration Failed: " + e.getMessage());
        }

        System.out.println("\n============== 2. SHOW CATALOG =============");
        libraryService.showCatalog();

        System.out.println("\n============== 3. TESTING INVALID INPUTS (EXCEPTIONS) =============");

        // টেস্ট ১: Null আইটেম রেজিস্টার করার চেষ্টা
        try {
            System.out.println("\n--- Test 1: Registering Null Item ---");
            libraryService.registerItem(null);
        } catch (Exception e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }


        try {
            System.out.println("\n--- Test 2: Registering Item with 0 copies ---");
            Book invalidBook = new Book("C++ Basics", 6, "more");
            libraryService.registerItem(invalidBook);
        } catch (Exception e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }


        try {
            System.out.println("\n--- Test 3: Registering Duplicate Item ---");
            Book duplicateBook = new Book("Java Basics", 4, "kutta");
            libraryService.registerItem(duplicateBook);
        } catch (Exception e) {
            System.out.println("Caught Expected Exception: " + e.getMessage());
        }

        System.out.println("\n============== 4. FINAL CATALOG STATUS =============");
        libraryService.showCatalog();
    }
}