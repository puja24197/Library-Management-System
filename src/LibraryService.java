import java.util.List;
import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Exception.MemberNotFoundException;

public class LibraryService {
    private final List<LibraryItems> catalog;

    public LibraryService() {

        this.catalog = FileManager.loadData();
    }

    public void registerItem(LibraryItems item) throws InvalidInputException {
        if (item == null) {
            throw new InvalidInputException("Item record cannot be null!");
        }

        String title = item.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidInputException("Title mandatory for insertion !");
        }

        if (item.getTotalCopies() <= 0) {
            throw new InvalidInputException("Minimum 1 copy is required ! ");
        }

        if (searchByTitle(title) != null) {
            throw new InvalidInputException("An item with title '" + title + "' already exists!");
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

    public void processIssue(String title, String memberId, String memberEmail,String memberType, int issueDate, int currentBorrowDate)
    throws ItemNotFoundException, InvalidInputException, MemberNotFoundException {
        LibraryItems target = searchByTitle(title);

        if (target == null) {
            throw new ItemNotFoundException("Process Failed: Title '" + title + "' was not found.");
        }
        if (memberId == null || memberId.trim().isEmpty() || !memberId.startsWith("M")) {
            throw new MemberNotFoundException("Member with ID'" +memberId+"'not found in the databas!");
        }

        if (!target.issueItem(memberId, memberEmail, memberType,issueDate, currentBorrowDate)) {
            throw new InvalidInputException("Process Failed: Copies unavailable for '" + title + "'.");
        }

        FileManager.saveData(catalog);
        System.out.println("Success: Issued to member ID " + memberId);
    }

    public void removeItem(String title) throws ItemNotFoundException, InvalidInputException {
        LibraryItems target = searchByTitle(title);

        if (target == null) {
            throw new ItemNotFoundException("Deletion Error: Cannot find item titled '" + title + "'.");
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
