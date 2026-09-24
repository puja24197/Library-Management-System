package LibraryGUI;

import Model.LibraryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class MainFrame extends JFrame {

    private LibraryService libraryService;
    private JTabbedPane tabbedPane;

    private ViewCatalogPanel catalogPanel;
    private ViewCategoryPanel categoryPanel;

    // ==================================================
    // COLORS
    // ==================================================

    private final Color BACKGROUND =
            new Color(245, 247, 252);

    private final Color PRIMARY =
            new Color(79, 70, 229);

    private final Color PRIMARY_DARK =
            new Color(67, 56, 202);

    private final Color TAB_BACKGROUND =
            new Color(226, 232, 240);

    private final Color TEXT_COLOR =
            new Color(31, 41, 55);

    public MainFrame() {

        // ==========================================
        // LIBRARY SERVICE
        // ==========================================

        libraryService =
                new LibraryService();

        // ==========================================
        // FRAME SETTINGS
        // ==========================================

        setTitle(
                "AYON MIYA PUBLIC LIBRARY"
        );

        setSize(
                1100,
                700
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setMinimumSize(
                new Dimension(
                        900,
                        600
                )
        );

        // ==========================================
        // MAIN BACKGROUND
        // ==========================================

        getContentPane().setBackground(
                BACKGROUND
        );

        // ==========================================
        // CREATE GUI
        // ==========================================

        createGUI();
    }

    private void createGUI() {

        // ==========================================
        // MAIN TITLE PANEL
        // ==========================================

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setBackground(
                BACKGROUND
        );

        titlePanel.setBorder(
                new EmptyBorder(
                        18,
                        10,
                        12,
                        10
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "AYON MIYA PUBLIC LIBRARY",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setForeground(
                PRIMARY
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Manage Books, Magazines, Members and Library Records",
                        SwingConstants.CENTER
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                new Color(
                        100,
                        116,
                        139
                )
        );

        JPanel titleContent =
                new JPanel();

        titleContent.setLayout(
                new BoxLayout(
                        titleContent,
                        BoxLayout.Y_AXIS
                )
        );

        titleContent.setOpaque(
                false
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titleContent.add(
                titleLabel
        );

        titleContent.add(
                Box.createVerticalStrut(4)
        );

        titleContent.add(
                subtitleLabel
        );

        titlePanel.add(
                titleContent,
                BorderLayout.CENTER
        );

        // ==========================================
        // TABBED PANE
        // ==========================================

        tabbedPane =
                new JTabbedPane();

        tabbedPane.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        tabbedPane.setForeground(
                TEXT_COLOR
        );

        tabbedPane.setBackground(
                BACKGROUND
        );

        tabbedPane.setBorder(
                new EmptyBorder(
                        0,
                        10,
                        10,
                        10
                )
        );

        // ==========================================
        // CUSTOM TAB UI
        // ==========================================

        tabbedPane.setUI(
                new BasicTabbedPaneUI() {

                    @Override
                    protected void installDefaults() {

                        super.installDefaults();

                        tabAreaInsets =
                                new Insets(
                                        5,
                                        5,
                                        0,
                                        5
                                );

                        contentBorderInsets =
                                new Insets(
                                        0,
                                        0,
                                        0,
                                        0
                                );
                    }

                    @Override
                    protected void paintTabBackground(
                            Graphics g,
                            int tabPlacement,
                            int tabIndex,
                            int x,
                            int y,
                            int w,
                            int h,
                            boolean isSelected) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        if (isSelected) {

                            g2.setColor(
                                    PRIMARY
                            );

                        } else {

                            g2.setColor(
                                    TAB_BACKGROUND
                            );
                        }

                        g2.fillRoundRect(
                                x,
                                y,
                                w,
                                h,
                                10,
                                10
                        );

                        g2.dispose();
                    }

                    @Override
                    protected void paintTabBorder(
                            Graphics g,
                            int tabPlacement,
                            int tabIndex,
                            int x,
                            int y,
                            int w,
                            int h,
                            boolean isSelected) {

                        // No border
                    }
                }
        );

        // ==========================================
        // CREATE ALL PANELS
        // ==========================================

        AddItemPanel addPanel =
                new AddItemPanel(
                        libraryService
                );

        catalogPanel =
                new ViewCatalogPanel(
                        libraryService
                );

        categoryPanel =
                new ViewCategoryPanel(
                        libraryService
                );

        UpdateItemPanel updatePanel =
                new UpdateItemPanel(
                        libraryService
                );

        DeleteItemPanel deletePanel =
                new DeleteItemPanel(
                        libraryService
                );

        IssueItemPanel issuePanel =
                new IssueItemPanel(
                        libraryService
                );

        ReturnItemPanel returnPanel =
                new ReturnItemPanel(
                        libraryService
                );

        // ==========================================
        // ADD TABS
        // ==========================================

        tabbedPane.addTab(
                "Add Item",
                addPanel
        );

        tabbedPane.addTab(
                "View Catalog",
                catalogPanel
        );

        tabbedPane.addTab(
                "View Category",
                categoryPanel
        );

        tabbedPane.addTab(
                "Update Item",
                updatePanel
        );

        tabbedPane.addTab(
                "Delete Item",
                deletePanel
        );

        tabbedPane.addTab(
                "Issue Item",
                issuePanel
        );

        tabbedPane.addTab(
                "Return Item",
                returnPanel
        );

        // ==========================================
        // TAB COLOR / TEXT UPDATE
        // ==========================================

        updateTabColors();

        // ==========================================
        // REFRESH VIEW PANELS
        // ==========================================

        tabbedPane.addChangeListener(
                e -> {

                    updateTabColors();

                    int selectedIndex =
                            tabbedPane.getSelectedIndex();

                    // View Catalog
                    if (selectedIndex == 1) {

                        catalogPanel.refreshCatalog();
                    }

                    // View Category
                    if (selectedIndex == 2) {

                        categoryPanel.refreshCatalog();
                    }
                }
        );

        // ==========================================
        // MAIN FRAME LAYOUT
        // ==========================================

        setLayout(
                new BorderLayout()
        );

        add(
                titlePanel,
                BorderLayout.NORTH
        );

        add(
                tabbedPane,
                BorderLayout.CENTER
        );

        // ==========================================
        // INITIAL DATA LOAD
        // ==========================================

        catalogPanel.refreshCatalog();

        categoryPanel.refreshCatalog();
    }

    // ==================================================
    // UPDATE TAB COLORS
    // ==================================================

    private void updateTabColors() {

        for (int i = 0;
             i < tabbedPane.getTabCount();
             i++) {

            if (i ==
                    tabbedPane.getSelectedIndex()) {

                tabbedPane.setForegroundAt(
                        i,
                        Color.WHITE
                );

            } else {

                tabbedPane.setForegroundAt(
                        i,
                        TEXT_COLOR
                );
            }
        }

        tabbedPane.repaint();
    }

    // ==============================================
    // MAIN METHOD
    // ==============================================

    public static void main(
            String[] args) {

        try {

            UIManager.setLookAndFeel(
                    UIManager
                            .getSystemLookAndFeelClassName()
            );

        } catch (Exception ignored) {

        }

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();

                    frame.setVisible(true);
                }
        );
    }
}