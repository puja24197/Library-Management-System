public class Book extends LibraryItems {
    private String author;

    public Book(String title, int totalCopies, String author) {
        super(title, totalCopies);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String getDetails() {
        if (isIssued()) {
            return "Book Title: " + getTitle() + ", Author: " + author + ", Status: Issued to " + getStudentId();
        } else {
            return "Book Title: " + getTitle() + ", Author: " + author + ", Available: " + getAvailableCopies();
        }
    }
}