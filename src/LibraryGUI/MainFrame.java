package LibraryGUI;

import Model.LibraryService;

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

        ViewCatalogPanel viewPanel = new ViewCatalogPanel(libraryService);
        IssueItemPanel issuePanel = new IssueItemPanel(libraryService);

        tabbedPane.addTab("View Catalog", viewPanel);

        tabbedPane.addTab("Issue Item", issuePanel);

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