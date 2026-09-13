public class Magazine extends LibraryItems {
    private String publisher;
    private String experience;

    public Magazine(String title, int totalCopies, String publisher) {
        super(title, totalCopies);
        this.publisher = publisher;

    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getDetails() {
        if (isIssued()) {
            return "Magazine Title: " + getTitle() + ", Publisher: " + publisher + ", Status: Issued to " + getStudentId();
        } else {
            return "Magazine Title: " + getTitle() + ", Publisher: " + publisher + ", Available: " + getAvailableCopies();
        }
    }
}