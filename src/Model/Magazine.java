package Model;

public class Magazine extends LibraryItems {

    private String publisher;


    // =========================
    // CONSTRUCTOR
    // =========================
    public Magazine(
            String title,
            int totalCopies,
            String publisher) {

        super(title, totalCopies);

        this.publisher = publisher;
    }


    // =========================
    // GETTER
    // =========================
    public String getPublisher() {

        return publisher;
    }


    // =========================
    // SETTER
    // =========================
    public void setPublisher(String publisher) {

        if (publisher != null &&
                !publisher.trim().isEmpty()) {

            this.publisher =
                    publisher.trim();
        }
    }


    // =========================
    // POLYMORPHISM
    // =========================
    @Override
    public String getDetails() {

        if (isIssued()) {

            return "Magazine Title: "
                    + getTitle()
                    + ", Publisher: "
                    + publisher
                    + ", Status: Issued to "
                    + getMemberId()
                    + ", Available Copies: "
                    + getAvailableCopies()
                    + ", Due Date: Day "
                    + getDueDate();

        } else {

            return "Magazine Title: "
                    + getTitle()
                    + ", Publisher: "
                    + publisher
                    + ", Available Copies: "
                    + getAvailableCopies()
                    + "/"
                    + getTotalCopies();
        }
    }
}