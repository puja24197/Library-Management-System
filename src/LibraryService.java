import java.util.List;
import Exception.*;

    public class LibraryService {
        private final List<LibraryItems> catalog;

        public LibraryService() {
            this.catalog = FileManager.loadData();
        }

        public void registerItem(LibraryItems item) throws Exception {
            if (item == null) {
                throw new Exception("Item record cannot be null!");
            }

            String title = item.getTitle();
            if (title == null || title.trim().isEmpty()) {
                throw new Exception("Title mandatory for insertion!");
            }

            if (item.getTotalCopies() <= 0) {
                throw new Exception("Minimum 1 copy is required ! ");
            }

            if (searchByTitle(title) != null) {
                throw new Exception("An item with title '" + title + "' already exists!");
            }

            catalog.add(item);
            FileManager.saveData(catalog);
            System.out.println("Success: Record added and synchronized to file.");
        }

        public void showCatalog() {
            if (catalog.isEmpty()) {
                System.out.println("Notice: No records available in current catalog.");
                return;
            }

            System.out.println("\n Current Library Inventory ");
            for (LibraryItems item : catalog) {
                System.out.println(item.getDetails() );
            }
        }

        public void processIssue(String title, String memberId, String memberEmail, int issueDay, int currentBorrowDate)
                throws Exception {
            LibraryItems target = searchByTitle(title);

            if (target == null) {
                throw new Exception("Process Failed: Title '" + title + "' was not found.");
            }

            if (!target.issueItem(memberId, memberEmail, issueDay, currentBorrowDate)) {
                throw new Exception("Process Failed: Copies unavailable for '" + title + "'.");
            }

            FileManager.saveData(catalog);
            System.out.println("Success: Issued to member ID " + memberId);
        }

        public void removeItem(String title) throws Exception {
            LibraryItems target = searchByTitle(title);

            if (target == null) {
                throw new Exception("Deletion Error: Cannot find item titled '" + title + "'.");
            }

            catalog.remove(target);
            FileManager.saveData(catalog);
            System.out.println("Success: Item removed and file updated.");
        }

        public LibraryItems searchByTitle(String title) {
            if (title == null) return null;

            for (LibraryItems item : catalog) {
                if (item.getTitle().equalsIgnoreCase(title.trim() )) {
                    return item;
                }
            }
            return null;
        }

        public List<LibraryItems> getCatalog() {
            return catalog;
        }
    }

