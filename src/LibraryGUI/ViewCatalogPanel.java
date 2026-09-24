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

public class ViewCatalogPanel extends JPanel {

    private LibraryService libraryService;

    private JTable table;
    private DefaultTableModel tableModel;

    // ==========================================
    // COLORS
    // ==========================================

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

    public ViewCatalogPanel(
            LibraryService libraryService) {

        this.libraryService = libraryService;

        // ==========================================
        // MAIN PANEL
        // ==========================================

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

        // ==========================================
        // HEADER
        // ==========================================

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
                        "Library Catalog",
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
                        "View all books and magazines available in the library",
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

        // ==========================================
        // TABLE MODEL
        // ==========================================

        String[] columnNames = {
                "Type",
                "Title",
                "Author / Publisher",
                "Available Copies"
        };

        tableModel =
                new DefaultTableModel(
                        columnNames,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        // ==========================================
        // TABLE
        // ==========================================

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

        table.setAutoCreateRowSorter(true);

        table.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // ==========================================
        // TABLE HEADER
        // ==========================================

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

        // ==========================================
        // CENTER ALIGNMENT
        // ==========================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        table.getColumnModel()
                .getColumn(0)
                .setCellRenderer(
                        centerRenderer
                );

        table.getColumnModel()
                .getColumn(3)
                .setCellRenderer(
                        centerRenderer
                );

        // ==========================================
        // COLUMN WIDTH
        // ==========================================

        table.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(100);

        table.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(250);

        table.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(250);

        table.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(130);

        // ==========================================
        // SCROLL PANE
        // ==========================================

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

        // ==========================================
        // TABLE CARD
        // ==========================================

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

        add(
                tableCard,
                BorderLayout.CENTER
        );

        // ==========================================
        // BOTTOM CONTROL PANEL
        // ==========================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                12
                        )
                );

        bottomPanel.setOpaque(false);

        JButton refreshBtn =
                new JButton(
                        "Refresh Catalog"
                );

        styleRefreshButton(refreshBtn);

        bottomPanel.add(
                refreshBtn
        );

        add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // ==========================================
        // REFRESH ACTION
        // ==========================================

        refreshBtn.addActionListener(
                e -> refreshCatalog()
        );

        // ==========================================
        // INITIAL LOAD
        // ==========================================

        refreshCatalog();
    }

    // ==================================================
    // REFRESH CATALOG
    // ==================================================

    public void refreshCatalog() {

        tableModel.setRowCount(0);

        try {

            List<LibraryItems> items =
                    libraryService.getCatalog();

            if (items == null || items.isEmpty()) {

                return;
            }

            for (LibraryItems item : items) {

                String type = "";
                String detail = "";

                // ==========================================
                // BOOK
                // ==========================================

                if (item instanceof Book) {

                    type = "Book";

                    detail =
                            ((Book) item)
                                    .getAuthor();
                }

                // ==========================================
                // MAGAZINE
                // ==========================================

                else if (item instanceof Magazine) {

                    type = "Magazine";

                    detail =
                            ((Magazine) item)
                                    .getPublisher();
                }

                // ==========================================
                // ADD ROW
                // ==========================================

                Object[] rowData = {

                        type,

                        item.getTitle(),

                        detail,

                        item.getAvailableCopies()
                };

                tableModel.addRow(
                        rowData
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
                        10,
                        25,
                        10,
                        25
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