package LibraryGUI;

import Model.Book;
import Model.LibraryItems;
import Model.LibraryService;
import Model.Magazine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCategoryPanel extends JPanel {

    private LibraryService libraryService;

    private JComboBox<String> comboCategory;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnRefresh;

    // ==================================================
    // COLORS
    // ==================================================

    private final Color BACKGROUND =
            new Color(245, 247, 252);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color PRIMARY =
            new Color(79, 70, 229);

    private final Color PRIMARY_HOVER =
            new Color(67, 56, 202);

    private final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private final Color SUBTITLE_COLOR =
            new Color(100, 116, 139);

    private final Color HEADER_COLOR =
            new Color(67, 56, 202);

    private final Color INFO_BG =
            new Color(238, 242, 255);

    private final Color INFO_TEXT =
            new Color(55, 48, 163);

    public ViewCategoryPanel(LibraryService service) {

        this.libraryService = service;

        // ==================================================
        // MAIN PANEL
        // ==================================================

        setLayout(new BorderLayout());

        setBackground(BACKGROUND);

        setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // ==================================================
        // HEADER
        // ==================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setOpaque(false);

        JLabel titleLabel =
                new JLabel(
                        "View Library by Category",
                        SwingConstants.CENTER
                );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
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
                        "Filter library items by Books or Magazines",
                        SwingConstants.CENTER
                );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                SUBTITLE_COLOR
        );

        headerPanel.add(titleLabel);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitleLabel);

        headerPanel.add(
                Box.createVerticalStrut(15)
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ==================================================
        // CONTROL CARD
        // ==================================================

        JPanel controlCard =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                10
                        )
                );

        controlCard.setBackground(
                CARD_COLOR
        );

        controlCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                5,
                                15,
                                5,
                                15
                        )
                )
        );

        JLabel categoryLabel =
                new JLabel("View Category:");

        categoryLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        categoryLabel.setForeground(
                TEXT_COLOR
        );

        comboCategory =
                new JComboBox<>(
                        new String[]{
                                "All Items",
                                "Books",
                                "Magazines"
                        }
                );

        styleComboBox(comboCategory);

        btnRefresh =
                new JButton("Refresh");

        styleRefreshButton(btnRefresh);

        controlCard.add(
                categoryLabel
        );

        controlCard.add(
                comboCategory
        );

        controlCard.add(
                btnRefresh
        );

        // ==================================================
        // TABLE MODEL
        // ==================================================

        tableModel =
                new DefaultTableModel(
                        new String[]{
                                "Type",
                                "Title",
                                "Author / Publisher",
                                "Total Copies",
                                "Available Copies",
                                "Status",
                                "Member ID"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        // ==================================================
        // TABLE
        // ==================================================

        table =
                new JTable(tableModel);

        table.setRowHeight(32);

        table.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        table.setForeground(
                TEXT_COLOR
        );

        table.setBackground(
                Color.WHITE
        );

        table.setGridColor(
                new Color(226, 232, 240)
        );

        table.setSelectionBackground(
                new Color(224, 231, 255)
        );

        table.setSelectionForeground(
                TEXT_COLOR
        );

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        table.setAutoCreateRowSorter(true);

        // ==================================================
        // TABLE HEADER
        // ==================================================

        table.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                13
                        )
                );

        table.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        table.getTableHeader()
                .setBackground(
                        HEADER_COLOR
                );

        table.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                38
                        )
                );

        // ==================================================
        // CENTER ALIGNMENT
        // ==================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        // Type
        table.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        // Total Copies
        table.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        // Available Copies
        table.getColumnModel()
                .getColumn(4)
                .setCellRenderer(
                        centerRenderer
                );

        // Status
        table.getColumnModel()
                .getColumn(5)
                .setCellRenderer(
                        centerRenderer
                );

        // Member ID
        table.getColumnModel()
                .getColumn(6)
                .setCellRenderer(
                        centerRenderer
                );

        // ==================================================
        // COLUMN WIDTH
        // ==================================================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(90);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(220);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(220);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(110);

        table.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(130);

        table.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(100);

        table.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(110);

        // ==================================================
        // SCROLL PANE
        // ==================================================

        JScrollPane scrollPane =
                new JScrollPane(table);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(226, 232, 240)
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        // ==================================================
        // TABLE CARD
        // ==================================================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout()
                );

        tableCard.setBackground(
                CARD_COLOR
        );

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                5,
                                5,
                                5,
                                5
                        )
                )
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // ==================================================
        // CENTER AREA
        // ==================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                controlCard,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==================================================
        // INFORMATION PANEL
        // ==================================================

        JPanel infoPanel =
                new JPanel(
                        new BorderLayout()
                );

        infoPanel.setBackground(
                INFO_BG
        );

        infoPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(199, 210, 254)
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        JLabel infoLabel =
                new JLabel(
                        "<html><center>"
                                + "<b>Category Filter</b>"
                                + "<br>"
                                + "Select a category to view specific library items."
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        infoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        infoLabel.setForeground(
                INFO_TEXT
        );

        infoPanel.add(
                infoLabel,
                BorderLayout.CENTER
        );

        add(
                infoPanel,
                BorderLayout.SOUTH
        );

        // ==================================================
        // CATEGORY CHANGE
        // ==================================================

        comboCategory.addActionListener(
                e -> refreshCatalog()
        );

        // ==================================================
        // REFRESH BUTTON
        // ==================================================

        btnRefresh.addActionListener(
                e -> refreshCatalog()
        );

        // ==================================================
        // INITIAL LOAD
        // ==================================================

        refreshCatalog();
    }

    // ==================================================
    // REFRESH CATALOG
    // ==================================================

    public void refreshCatalog() {

        tableModel.setRowCount(0);

        try {

            List<LibraryItems> catalog =
                    libraryService.getCatalog();

            String selectedCategory =
                    (String) comboCategory.getSelectedItem();

            if (catalog == null ||
                    catalog.isEmpty()) {

                return;
            }

            for (LibraryItems item : catalog) {

                // ==========================================
                // CHECK CATEGORY
                // ==========================================

                if ("Books".equals(selectedCategory)
                        && !(item instanceof Book)) {

                    continue;
                }

                if ("Magazines".equals(selectedCategory)
                        && !(item instanceof Magazine)) {

                    continue;
                }

                // ==========================================
                // ITEM TYPE
                // ==========================================

                String type;

                String authorOrPublisher;

                if (item instanceof Book) {

                    Book book =
                            (Book) item;

                    type = "Book";

                    authorOrPublisher =
                            book.getAuthor();

                } else if (item instanceof Magazine) {

                    Magazine magazine =
                            (Magazine) item;

                    type = "Magazine";

                    authorOrPublisher =
                            magazine.getPublisher();

                } else {

                    type = "Other";

                    authorOrPublisher = "N/A";
                }

                // ==========================================
                // STATUS
                // ==========================================

                String status;

                if (item.isIssued()) {

                    status = "Issued";

                } else {

                    status = "Available";
                }

                // ==========================================
                // MEMBER ID
                // ==========================================

                String memberId =
                        item.isIssued()
                                ? item.getMemberId()
                                : "N/A";

                // ==========================================
                // ADD ROW
                // ==========================================

                tableModel.addRow(
                        new Object[]{
                                type,
                                item.getTitle(),
                                authorOrPublisher,
                                item.getTotalCopies(),
                                item.getAvailableCopies(),
                                status,
                                memberId
                        }
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not load catalog.\n\n"
                            + ex.getMessage(),
                    "Catalog Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==================================================
    // COMBO BOX STYLE
    // ==================================================

    private void styleComboBox(
            JComboBox<String> comboBox) {

        comboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        comboBox.setForeground(
                TEXT_COLOR
        );

        comboBox.setBackground(
                new Color(248, 250, 252)
        );

        comboBox.setPreferredSize(
                new Dimension(
                        150,
                        35
                )
        );

        comboBox.setBorder(
                BorderFactory.createLineBorder(
                        new Color(203, 213, 225)
                )
        );
    }

    // ==================================================
    // REFRESH BUTTON STYLE
    // ==================================================

    private void styleRefreshButton(
            JButton button) {

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                PRIMARY
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        9,
                        22,
                        9,
                        22
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                PRIMARY_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                PRIMARY
                        );
                    }
                }
        );
    }
}