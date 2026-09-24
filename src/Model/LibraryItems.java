package Model;

public abstract class LibraryItems {

    // =========================
    // ENCAPSULATION
    // =========================
    private String title;
    private boolean isIssued;
    private String memberId;
    private String memberEmail;
    private int totalCopies;
    private int availableCopies;
    private int dueDate;

    private final double finePerDay = 20.0;


    // =========================
    // CONSTRUCTOR
    // =========================
    public LibraryItems(String title, int totalCopies) {

        this.title = title;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;

        this.isIssued = false;

        this.memberId = "N/A";
        this.memberEmail = "N/A";

        this.dueDate = 0;
    }


    // =========================
    // ABSTRACTION
    // =========================
    public abstract String getDetails();


    // =========================
    // BUSINESS RULES
    // =========================

    // Maximum number of books a member can borrow
    public int getMaxAllowedBooks(String memberType) {

        if (memberType == null ||
                memberType.trim().isEmpty()) {

            return 0;
        }

        if (memberType.equalsIgnoreCase("Faculty")) {
            return 5;
        }

        if (memberType.equalsIgnoreCase("Student")) {
            return 3;
        }

        return 0;
    }


    // Maximum borrowing period
    public int getMaxBorrowDays(String memberType) {

        if (memberType == null ||
                memberType.trim().isEmpty()) {

            return 0;
        }

        if (memberType.equalsIgnoreCase("Faculty")) {
            return 30;
        }

        if (memberType.equalsIgnoreCase("Student")) {
            return 15;
        }

        return 0;
    }


    // =========================
    // ISSUE ITEM
    // =========================
    public boolean issueItem(
            String memberId,
            String memberType,
            String memberEmail,
            int currentDate,
            int currentBorrowedCount) {

        // Validate Member ID
        if (memberId == null ||
                memberId.trim().isEmpty()) {

            System.out.println(
                    "Issue failed: Member ID cannot be empty."
            );

            return false;
        }


        // Validate Member Type
        if (memberType == null ||
                memberType.trim().isEmpty()) {

            System.out.println(
                    "Issue failed: Member type is required."
            );

            return false;
        }


        // Validate Student / Faculty
        if (!memberType.equalsIgnoreCase("Student")
                && !memberType.equalsIgnoreCase("Faculty")) {

            System.out.println(
                    "Issue failed: Member type must be Student or Faculty."
            );

            return false;
        }


        // Validate Email
        if (!isValidEmail(memberEmail)) {

            System.out.println(
                    "Issue failed: Invalid email address."
            );

            return false;
        }


        // Validate Date
        if (currentDate <= 0) {

            System.out.println(
                    "Issue failed: Issue date must be greater than 0."
            );

            return false;
        }


        // Check available copies
        if (availableCopies <= 0) {

            System.out.println(
                    "Issue failed: No copy available."
            );

            return false;
        }


        int maxAllowed =
                getMaxAllowedBooks(memberType);

        int allowedDays =
                getMaxBorrowDays(memberType);


        // IMPORTANT:
        // < instead of <=
        if (currentBorrowedCount >= maxAllowed) {

            System.out.println(
                    "Issue failed: " + memberType
                            + " can borrow maximum "
                            + maxAllowed + " items."
            );

            return false;
        }


        // =========================
        // UPDATE OBJECT STATE
        // =========================

        availableCopies--;

        this.isIssued = true;

        this.memberId = memberId.trim();

        this.memberEmail = memberEmail.trim();

        this.dueDate =
                currentDate + allowedDays;


        System.out.println(
                "Successfully issued to "
                        + memberType
                        + "!"
        );

        System.out.println(
                "Member ID: "
                        + this.memberId
        );

        System.out.println(
                "Due Date: Day "
                        + this.dueDate
        );

        return true;
    }


    // =========================
    // RETURN ITEM
    // =========================
    public double returnFromMember(int returnDate) {

        // No issued copy
        if (availableCopies >= totalCopies) {

            System.out.println(
                    "Return failed: No copy of this item is currently issued."
            );

            return -1;
        }


        // Validate return date
        if (returnDate <= 0) {

            System.out.println(
                    "Return failed: Invalid return date."
            );

            return -1;
        }


        double fine = 0.0;


        // Calculate fine
        if (returnDate > dueDate) {

            int lateDays =
                    returnDate - dueDate;

            fine = lateDays * finePerDay;
        }


        // Return one copy
        availableCopies++;


        // If all copies are now available,
        // no copy is currently issued.
        if (availableCopies >= totalCopies) {

            availableCopies = totalCopies;

            this.isIssued = false;

            this.memberId = "N/A";

            this.memberEmail = "N/A";

            this.dueDate = 0;
        }


        System.out.println(
                "Item returned successfully."
        );


        if (fine > 0) {

            System.out.println(
                    "Late return fine: "
                            + fine
                            + " BDT"
            );

        } else {

            System.out.println(
                    "No late fine."
            );
        }


        return fine;
    }


    // =========================
    // EMAIL VALIDATION
    // =========================
    private boolean isValidEmail(String email) {

        if (email == null ||
                email.trim().isEmpty()) {

            return false;
        }

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }


    // =========================
    // WIKIPEDIA
    // =========================
    public void browseWikipedia(
            String memberEmail,
            String topic) {

        if (!isValidEmail(memberEmail)) {

            System.out.println(
                    "Wrong Email!"
            );

            return;
        }


        // Correct String comparison
        if (topic == null ||
                topic.trim().isEmpty()) {

            topic = title;
        }


        String formattedTopic =
                topic.trim().replace(" ", "_");


        System.out.println(
                "Email: " + memberEmail
        );

        System.out.println(
                "Link: https://en.wikipedia.org/wiki/"
                        + formattedTopic
        );
    }


    // =========================
    // GETTERS
    // =========================

    public String getTitle() {
        return title;
    }


    public boolean isIssued() {
        return isIssued;
    }


    public String getMemberId() {
        return memberId;
    }


    public String getMemberEmail() {
        return memberEmail;
    }


    public int getTotalCopies() {
        return totalCopies;
    }


    public int getAvailableCopies() {
        return availableCopies;
    }


    public int getDueDate() {
        return dueDate;
    }


    public double getFinePerDay() {
        return finePerDay;
    }


    // =========================
    // SETTERS
    // =========================

    public void setTitle(String title) {

        if (title != null &&
                !title.trim().isEmpty()) {

            this.title = title.trim();
        }
    }


    public void setTotalCopies(int totalCopies) {

        if (totalCopies <= 0) {
            return;
        }

        int issuedCopies =
                this.totalCopies - this.availableCopies;

        if (totalCopies < issuedCopies) {
            return;
        }

        this.totalCopies = totalCopies;

        this.availableCopies =
                totalCopies - issuedCopies;
    }


    public void setAvailableCopies(int availableCopies) {

        if (availableCopies < 0) {
            return;
        }

        if (availableCopies > totalCopies) {
            return;
        }

        this.availableCopies =
                availableCopies;

        if (availableCopies == totalCopies) {

            this.isIssued = false;

            this.memberId = "N/A";

            this.memberEmail = "N/A";

            this.dueDate = 0;

        } else {

            this.isIssued = true;
        }
    }


    public void setMemberId(String memberId) {

        if (memberId != null &&
                !memberId.trim().isEmpty()) {

            this.memberId =
                    memberId.trim();
        }
    }


    public void setMemberEmail(String memberEmail) {

        if (memberEmail != null &&
                isValidEmail(memberEmail)) {

            this.memberEmail =
                    memberEmail.trim();
        }
    }


    public void setIssued(boolean isIssued) {

        this.isIssued = isIssued;
    }


    public void setDueDate(int dueDate) {

        if (dueDate >= 0) {

            this.dueDate = dueDate;
        }
    }
}