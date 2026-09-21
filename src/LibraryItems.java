public abstract class LibraryItems {
    private String title;
    private boolean isIssued ;
    private String memberId;
    private String memberEmail ;
    private int totalCopies ;
    private int availableCopies;

      private int dueDate ;
       private double finePerDay = 20.0;

    public LibraryItems(String title, int totalCopies) {
        this.title = title;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.isIssued = false;
        this.memberId = "N/A";
        this.memberEmail = "N/A";
        this.dueDate = 0;
    }
    public abstract String getDetails() ;
    
    public int getMaxAllowedBooks(String memberType) {
        if (memberType.equalsIgnoreCase("Faculty")) {
            return 5;
        }
        return 3;
    }
    public int getMaxBorrowDays(String memberType) {
        if (memberType.equalsIgnoreCase("Faculty")) {
            return 30; 
        }
        return 15; 
}

    public boolean issueItem(String memberId, String memberType,String memberEmail, int currentDate, int currentBorrowedCount) {
        int maxAllowed = getMaxAllowedBooks(memberType);
        int allowedDays = getMaxBorrowDays(memberType);
        if (availableCopies > 0 && currentBorrowedCount <= maxAllowed) {
            availableCopies--;
            this.isIssued = true;
            this.memberId = memberId;
            this.memberEmail = memberEmail;
            this.dueDate = currentDate +  allowedDays;
            System.out.println("Successfully issued to " + memberType + "! Due Date: Day " + this.dueDate);
            return true;
        }
        
        System.out.println("Issue failed!! no copy available!!");
        return false;
    }
         
    public double returnFromMember( int returnDate) {
        double fine = 0.0;
        if (isIssued && returnDate > dueDate) {
            int Late = returnDate - dueDate;
             fine =Late * finePerDay;
        }

        if (isIssued) {
            availableCopies++;
            if (availableCopies == totalCopies) {
                this.isIssued = false;
                this.memberId = "N/A";
                this.memberEmail = "N/A";
                this.dueDate = 0 ;
            }
        }
        return fine;
    }
    public void browseWikipedia(String memberEmail, String topic) {
        if (memberEmail.contains("@")) {
            if (topic == "") {
                 topic = title;
            }
              String formattedTopic = topic.replace(" ", "_");
            System.out.println("Email: " + memberEmail);
            System.out.println("Link: https://en.wikipedia.org/wiki/" + formattedTopic);
        } else {
            System.out.println("Wrong Email!");
        }
    }

    public String getTitle() {
        return title;
    }
    public boolean isIssued() {
        return isIssued ;
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
    public int getAvailableCopies(){

        return availableCopies;
    }
    public int getDueDate() {
        return dueDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }
}

