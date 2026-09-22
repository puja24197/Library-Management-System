package LibraryGUI;

import javax.swing.*;

public class MainFrame extends JFrame {
    private LibraryService libraryService;

    public MainFrame() {
        libraryService = new LibraryService();

        setTitle("Library Management System");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // Adding Panels built by 3 members
        ViewCatalogPanel viewPanel = new ViewCatalogPanel(libraryService);
        AddItemPanel addPanel = new AddItemPanel(libraryService);
        IssueItemPanel issuePanel = new IssueItemPanel(libraryService);

        tabbedPane.addTab("View Catalog", viewPanel);
        tabbedPane.addTab("Add / Update Item", addPanel);
        tabbedPane.addTab("Issue Item", issuePanel);

        // Auto Refresh Catalog when tab is switched to View
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 0) {
                viewPanel.refreshCatalog();
            }
        });

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}