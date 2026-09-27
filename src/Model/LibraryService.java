package Model;

import java.util.ArrayList;
import java.util.List;

import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Exception.MemberNotFoundException;

public class LibraryService {

    private final List<LibraryItems> catalog;

    public LibraryService() {
        this.catalog = FileManager.loadData();
    }

    public void registerItem(LibraryItems item)
            throws InvalidInputException {

        if (item == null) {
            throw new InvalidInputException(
                    "Item record cannot be null."
            );
        }


        String title = item.getTitle();

        if (title == null || title.trim().isEmpty()) {

            throw new InvalidInputException("Title is mandatory.");
        }


        if (item.getTotalCopies() <= 0) {

            throw new InvalidInputException(
                    "Minimum 1 copy is required."
            );
        }

        if (item instanceof Book) {

            Book book = (Book) item;

            if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {

                throw new InvalidInputException(
                        "Author is mandatory for a Book."
                );
            }
        }

        if (item instanceof Magazine) {

            Magazine magazine = (Magazine) item;

            if (magazine.getPublisher() == null || magazine.getPublisher().trim().isEmpty()) {

                throw new InvalidInputException(
                        "Publisher is mandatory for a Magazine."
                );
            }
        }

        if (searchByTitle(title) != null) {

            throw new InvalidInputException("An item with title '"
                            + title
                            + "' already exists."
            );
        }


        catalog.add(item);

        FileManager.saveData(catalog);

        System.out.println("Success: Record added and saved to file.");
    }


    public void showCatalog() {

        if (catalog.isEmpty()) {

            System.out.println("Notice: No records available in the library.");

            return;
        }


        System.out.println(
                "\n========== LIBRARY INVENTORY =========="
        );


        for (LibraryItems item : catalog) {

            System.out.println(
                    item.getDetails()
            );

            System.out.println(
                    "---------------------------------------"
            );
        }
    }

    public LibraryItems searchByTitle(String title) {

        if (title == null ||
                title.trim().isEmpty()) {

            return null;
        }


        for (LibraryItems item : catalog) {

            if (item.getTitle() != null &&
                    item.getTitle()
                            .equalsIgnoreCase(title.trim())) {

                return item;
            }
        }


        return null;
    }

    public List<LibraryItems> getCatalog() {

        return new ArrayList<>(catalog);
    }


    public int getBorrowedCountForMember(String memberId) {

        if (memberId == null || memberId.trim().isEmpty()) {
            return 0;
        }


        int count = 0;


        for (LibraryItems item : catalog) {

            if (item.isIssued()
                    && item.getMemberId() != null
                    && item.getMemberId()
                    .equalsIgnoreCase(memberId.trim())) {

                count++;
            }
        }


        return count;
    }


    public void processIssue(
            String title,
            String memberId,
            String memberEmail,
            String memberType,
            int issueDate,
            int currentBorrowedCount)

            throws ItemNotFoundException,
            InvalidInputException,
            MemberNotFoundException {

        LibraryItems target = searchByTitle(title);


        if (target == null) {

            throw new ItemNotFoundException(
                    "Process failed: Item titled '"
                            + title
                            + "' was not found."
            );
        }

        if (memberId == null || memberId.trim().isEmpty()) {
            throw new MemberNotFoundException(
                    "Member ID cannot be empty."
            );
        }


        if (memberType == null ||
                memberType.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Member type is required."
            );
        }


        if (!memberType.equalsIgnoreCase("Student")
                && !memberType.equalsIgnoreCase("Faculty")) {

            throw new InvalidInputException(
                    "Member type must be Student or Faculty."
            );
        }


        // Validate email
        if (memberEmail == null ||
                memberEmail.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Member email is required."
            );
        }


        if (!memberEmail.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new InvalidInputException(
                    "Invalid member email address."
            );
        }


        // Validate date
        if (issueDate <= 0) {

            throw new InvalidInputException(
                    "Issue date must be greater than 0."
            );
        }

        int actualBorrowedCount =
                getBorrowedCountForMember(memberId);

        if (actualBorrowedCount != currentBorrowedCount) {

            currentBorrowedCount =
                    actualBorrowedCount;
        }


        boolean success =
                target.issueItem(
                        memberId,
                        memberType,
                        memberEmail,
                        issueDate,
                        currentBorrowedCount
                );


        if (!success) {

            throw new InvalidInputException(
                    "Process failed: Item cannot be issued. "
                            + "Check availability or borrowing limit."
            );
        }


        // Save updated state
        FileManager.saveData(catalog);


        System.out.println(
                "Success: Item issued to member ID "
                        + memberId
        );
    }



    public void updateBook(
            String oldTitle,
            String newTitle,
            int newTotalCopies,
            String newAuthor)

            throws ItemNotFoundException,
            InvalidInputException {


        LibraryItems target =
                searchByTitle(oldTitle);


        if (target == null) {

            throw new ItemNotFoundException(
                    "Update failed: Item '"
                            + oldTitle
                            + "' was not found."
            );
        }


        if (!(target instanceof Book)) {

            throw new InvalidInputException(
                    "Update failed: Selected item is not a Book."
            );
        }


        if (newTitle == null ||
                newTitle.trim().isEmpty()) {

            throw new InvalidInputException(
                    "New title cannot be empty."
            );
        }


        if (newTotalCopies <= 0) {

            throw new InvalidInputException(
                    "Total copies must be greater than 0."
            );
        }


    
        if (newAuthor == null ||
                newAuthor.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Author cannot be empty."
            );
        }



        LibraryItems existing =
                searchByTitle(newTitle);


        if (existing != null &&
                existing != target) {

            throw new InvalidInputException(
                    "An item with title '"
                            + newTitle
                            + "' already exists."
            );
        }


        Book book =
                (Book) target;


        int oldTotalCopies =
                book.getTotalCopies();


        int oldAvailableCopies =
                book.getAvailableCopies();


        int issuedCopies =
                oldTotalCopies - oldAvailableCopies;

        if (newTotalCopies < issuedCopies) {

            throw new InvalidInputException(
                    "New total copies cannot be less than "
                            + "currently issued copies."
            );
        }


        int newAvailableCopies =
                newTotalCopies - issuedCopies;


    
        book.setTitle(
                newTitle.trim()
        );

        book.setTotalCopies(
                newTotalCopies
        );

        book.setAvailableCopies(
                newAvailableCopies
        );

        book.setAuthor(
                newAuthor.trim()
        );


    
        FileManager.saveData(catalog);


        System.out.println(
                "Success: Book updated and saved."
        );
    }


    public void updateMagazine(
            String oldTitle,
            String newTitle,
            int newTotalCopies,
            String newPublisher)

            throws ItemNotFoundException,
            InvalidInputException {


        LibraryItems target =
                searchByTitle(oldTitle);


        if (target == null) {

            throw new ItemNotFoundException(
                    "Update failed: Item '"
                            + oldTitle
                            + "' was not found."
            );
        }


        if (!(target instanceof Magazine)) {

            throw new InvalidInputException(
                    "Update failed: Selected item is not a Magazine."
            );
        }


        
        if (newTitle == null ||
                newTitle.trim().isEmpty()) {

            throw new InvalidInputException(
                    "New title cannot be empty."
            );
        }


        if (newTotalCopies <= 0) {

            throw new InvalidInputException(
                    "Total copies must be greater than 0."
            );
        }


        if (newPublisher == null ||
                newPublisher.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Publisher cannot be empty."
            );
        }

        LibraryItems existing =
                searchByTitle(newTitle);


        if (existing != null &&
                existing != target) {

            throw new InvalidInputException(
                    "An item with title '"
                            + newTitle
                            + "' already exists."
            );
        }


        Magazine magazine =
                (Magazine) target;


        int oldTotalCopies =
                magazine.getTotalCopies();


        int oldAvailableCopies =
                magazine.getAvailableCopies();


        int issuedCopies =
                oldTotalCopies - oldAvailableCopies;


        if (newTotalCopies < issuedCopies) {

            throw new InvalidInputException(
                    "New total copies cannot be less than "
                            + "currently issued copies."
            );
        }


        int newAvailableCopies =
                newTotalCopies - issuedCopies;


    
        magazine.setTitle(
                newTitle.trim()
        );

        magazine.setTotalCopies(
                newTotalCopies
        );

        magazine.setAvailableCopies(
                newAvailableCopies
        );

        magazine.setPublisher(
                newPublisher.trim()
        );

        FileManager.saveData(catalog);


        System.out.println(
                "Success: Magazine updated and saved."
        );
    }



    public void removeItem(String title)
            throws ItemNotFoundException,
            InvalidInputException {


        LibraryItems target =
                searchByTitle(title);


        if (target == null) {

            throw new ItemNotFoundException(
                    "Deletion failed: Item titled '"
                            + title
                            + "' was not found."
            );
        }


        if (target.getAvailableCopies()
                < target.getTotalCopies()) {

            throw new InvalidInputException(
                    "Cannot delete '"
                            + title
                            + "' because one or more copies "
                            + "are currently issued."
            );
        }


        catalog.remove(target);

        FileManager.saveData(catalog);


        System.out.println("Success: Item deleted and file updated.");

     public double returnItem(
            String title,
            int returnDate)

            throws ItemNotFoundException,
            InvalidInputException {


        LibraryItems target =
                searchByTitle(title);


        if (target == null) {

            throw new ItemNotFoundException(
                    "Return failed: Item titled '"
                            + title
                            + "' was not found."
            );
        }


        if (target.getAvailableCopies()
                >= target.getTotalCopies()) {

            throw new InvalidInputException(
                    "All copies of '"
                            + title
                            + "' are already in the library."
            );
        }


        if (returnDate <= 0) {

            throw new InvalidInputException(
                    "Return date must be greater than 0."
            );
        }
        double fine =
                target.returnFromMember(returnDate);


        if (fine < 0) {

            throw new InvalidInputException(
                    "Return operation failed."
            );
        }

        FileManager.saveData(catalog);


        System.out.println(
                "Success: Item returned and file updated."
        );


        if (fine > 0) {

            System.out.println(
                    "Fine: " + fine + " BDT"
            );
        }


        return fine;
    }
}
