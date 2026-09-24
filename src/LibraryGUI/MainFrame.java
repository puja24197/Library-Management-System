package LibraryGUI;
import Model.Book;
import Model.Magazine;
import Model.LibraryService;
import javax.swing.*;

public class MainFrame extends JFrame{
    private LibraryService libraryService;

    public MainFrame() {
        libraryService = new LibraryService();
        try {
            libraryService.registerItem(new Book("Java Programming", 15, "P. Rizwan Ahmed"));
            libraryService.registerItem(new Book("Numerical Methods", 8, "Richard L. burden"));
            libraryService.registerItem(new Book("Data Structures & Algorithms", 31, "Mark Allen Weiss"));
            libraryService.registerItem(new Book("Data Communications and networking ", 19, "Behrouz a forouzan"));

            libraryService.registerItem(new Magazine("Kishore Alo", 12, "Prothom Alo"));
            libraryService.registerItem(new Magazine("Time Magazine", 7, "Time USA"));
            libraryService.registerItem(new Magazine("Canvas", 21, "Canvas Publications"));
        } catch (Exception e) {
            System.out.println("Sample Data Error:" + e.getMessage());
        }

        setTitle("Library Management System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        ViewCatalogPanel viewPanel = new ViewCatalogPanel(libraryService);
        IssueItemPanel issuePanel = new IssueItemPanel(libraryService);
        ReturnItemPanel returnPanel = new ReturnItemPanel(libraryService);
        tabbedPane.addTab("View Catalog", viewPanel);

        tabbedPane.addTab("Issue Item", issuePanel);
        tabbedPane.addTab("Return Item", returnPanel);
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) {
                viewPanel.refreshCatalog();
            }
        });

        add(tabbedPane);
        viewPanel.refreshCatalog();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}