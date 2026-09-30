package Model;

public class Book extends LibraryItems {

    private String author;

    public Book(String title, int totalCopies, String author) {
        super(title, totalCopies);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String getDetails() {

        if (isIssued()) {
            return "Book Title: " + getTitle()
                    + ", Author: " + author
                    + ", Status: Issued to " + getMemberId()
                    + ", Available Copies: " + getAvailableCopies()
                    + ", Due Date: Day " + getDueDate();

        } else {
            return "Book Title: " + getTitle()
                    + ", Author: " + author
                    + ", Available Copies: " + getAvailableCopies()
                    + "/" + getTotalCopies();
        }
    }
}
