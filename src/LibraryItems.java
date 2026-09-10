public abstract class LibraryItems {
        private String id;
        private String title;
        private boolean isIssued;
        private String studentId;
        private int totalCopies;
        private int availableCopies;

        public LibraryItems(String id, String title, int totalCopies) {
            this.id = id;
            this.title = title;
            this.totalCopies = totalCopies;
            this.availableCopies = totalCopies;
            this.isIssued = false;
            this.studentId = "N/A";
        }
        public abstract String getDetails();

          public boolean issueToStudent(String studentId) {
            if (availableCopies > 0) {
                availableCopies--;
                this.isIssued = true;
                this.studentId = studentId;
                return true;
            }
            return false;
        }

        public void returnFromStudent() {
            if (availableCopies < totalCopies) {
                availableCopies++;
            }
            if (availableCopies == totalCopies) {
                this.isIssued = false;
                this.studentId = "N/A";
            }
        }
        public String getId() { return id; }
        public String getTitle() { return title; }
        public boolean isIssued() { return isIssued; }
        public String getStudentId() { return studentId; }
        public int getTotalCopies() { return totalCopies; }
        public int getAvailableCopies() { return availableCopies; }
    }

