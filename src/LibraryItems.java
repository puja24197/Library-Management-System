public abstract class LibraryItems {
    private String title;
    private boolean isIssued ;
    private String studentId;
    private String studentEmail ;
    private int totalCopies ;
    private int availableCopies;

      private int dueDate ;
       private double finePerDay = 20.0;

    public LibraryItems(String title, int totalCopies) {
        this.title = title;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.isIssued = false;
        this.studentId = "N/A";
        this.studentEmail = "N/A";
        this.dueDate = 0;
    }
    public abstract String getDetails();

    public boolean issueToStudent(String studentId, String studentEmail,int currentDate) {
        if (availableCopies > 0) {
            availableCopies--;
            this.isIssued = true;
            this.studentId = studentId;
            this.studentEmail = studentEmail;
            this.dueDate = currentDate + 15;
            return true;
        }
        return false;
    }

    public double returnFromStudent( int ReturnDate) {
        double fine = 0.0;
        if (isIssued && ReturnDate > dueDate) {
            int Late = ReturnDate - dueDate;
             fine =Late * finePerDay;
        }

        if (isIssued) {
            availableCopies++;
            if (availableCopies == totalCopies) {
                this.isIssued = false;
                this.studentId = "N/A";
                this.studentEmail = "N/A";
                this.dueDate = 0 ;
            }
        }
        return fine;
    }
    public void browseWikipedia(String studentEmail, String topic) {
        if (studentEmail.contains("@")) {
            if (topic == "") {
                 topic = title;
            }
              String formattedTopic = topic.replace(" ", "_");
            System.out.println("Email: " + studentEmail);
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
    public String getStudentId() {
        return studentId;
    }
    public String getStudentEmail() {
        return studentEmail;
    }
    public int getTotalCopies() {
        return totalCopies;
    }
    public int getAvailableCopies(){
        return availableCopies;
    }
    public int getDueDate() {
        return dueDate; }
}

