package LibraryGUI;

import Exception.InvalidInputException;
import Model.Book;
import Model.LibraryService;
import Model.Magazine;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddItemPanel extends JPanel {

    private LibraryService libraryService;

    private JComboBox<String> comboItemType;

    private JTextField txtTitle;
    private JTextField txtCopies;
    private JTextField txtAuthor;
    private JTextField txtPublisher;

    private JLabel lblAuthor;
    private JLabel lblPublisher;

    private JButton btnAdd;
    private JButton btnClear;

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

    private final Color SECONDARY =
            new Color(226, 232, 240);

    private final Color TEXT_COLOR =
            new Color(17, 86, 145 );

    private final Color SUBTITLE_COLOR =
            new Color(100, 116, 139);

    public AddItemPanel(LibraryService service) {

        this.libraryService = service;

        // ==========================================
        // MAIN PANEL
        // ==========================================

        setLayout(new BorderLayout());

        setBackground(BACKGROUND);

        setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
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
                        "Add Library Item",
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
                        "Add a new book or magazine to the library",
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
        // FORM CARD
        // ==========================================

        JPanel cardPanel =
                new JPanel(
                        new GridBagLayout()
                );

        cardPanel.setBackground(
                CARD_COLOR
        );

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                20,
                                35,
                                20,
                                35
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        10,
                        8,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // ==========================================
        // ITEM TYPE
        // ==========================================

        comboItemType =
                new JComboBox<>(
                        new String[]{
                                "Book",
                                "Magazine"
                        }
                );

        styleComboBox(comboItemType);

        addField(
                cardPanel,
                gbc,
                0,
                "Item Type",
                comboItemType
        );

        // ==========================================
        // TITLE
        // ==========================================

        txtTitle =
                new JTextField(25);

        styleTextField(txtTitle);

        addField(
                cardPanel,
                gbc,
                1,
                "Title",
                txtTitle
        );

        // ==========================================
        // COPIES
        // ==========================================

        txtCopies =
                new JTextField(25);

        styleTextField(txtCopies);

        addField(
                cardPanel,
                gbc,
                2,
                "Total Copies",
                txtCopies
        );

        // ==========================================
        // AUTHOR
        // ==========================================

        lblAuthor =
                createLabel("Author");

        txtAuthor =
                new JTextField(25);

        styleTextField(txtAuthor);

        addField(
                cardPanel,
                gbc,
                3,
                lblAuthor,
                txtAuthor
        );

        // ==========================================
        // PUBLISHER
        // ==========================================

        lblPublisher =
                createLabel("Publisher");

        txtPublisher =
                new JTextField(25);

        styleTextField(txtPublisher);

        addField(
                cardPanel,
                gbc,
                4,
                lblPublisher,
                txtPublisher
        );

        // Initially Book selected
        lblPublisher.setVisible(false);
        txtPublisher.setVisible(false);

        // ==========================================
        // BUTTON PANEL
        // ==========================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        btnAdd =
                new JButton("Add Item");

        btnClear =
                new JButton("Clear");

        stylePrimaryButton(btnAdd);

        styleSecondaryButton(btnClear);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;

        cardPanel.add(
                buttonPanel,
                gbc
        );

        add(
                cardPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // INFORMATION
        // ==========================================

        JLabel infoLabel =
                new JLabel(
                        "<html><center>"
                                + "<b>Tip:</b> Enter all required information "
                                + "before adding the item."
                                + "<br>"
                                + "The record will be saved automatically."
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
                SUBTITLE_COLOR
        );

        infoLabel.setBorder(
                new EmptyBorder(
                        15,
                        10,
                        5,
                        10
                )
        );

        add(
                infoLabel,
                BorderLayout.SOUTH
        );

        // ==========================================
        // ITEM TYPE CHANGE
        // ==========================================

        comboItemType.addActionListener(
                e -> updateItemTypeFields()
        );

        // ==========================================
        // ADD BUTTON
        // ==========================================

        btnAdd.addActionListener(
                e -> addItem()
        );

        // ==========================================
        // CLEAR BUTTON
        // ==========================================

        btnClear.addActionListener(
                e -> clearFields()
        );
    }

    // ==================================================
    // CREATE LABEL
    // ==================================================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }

    // ==================================================
    // ADD FIELD
    // ==================================================

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String label,
            JComponent component) {

        addField(
                panel,
                gbc,
                row,
                createLabel(label),
                component
        );
    }

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;

        panel.add(
                component,
                gbc
        );
    }

    // ==================================================
    // TEXT FIELD STYLE
    // ==================================================

    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        field.setForeground(
                TEXT_COLOR
        );

        field.setBackground(
                new Color(248, 250, 252)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        new EmptyBorder(
                                8,
                                10,
                                8,
                                10
                        )
                )
        );
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
                Color.WHITE
        );
    }

    // ==================================================
    // PRIMARY BUTTON
    // ==================================================

    private void stylePrimaryButton(JButton button) {

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(PRIMARY);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
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

    // ==================================================
    // SECONDARY BUTTON
    // ==================================================

    private void styleSecondaryButton(
            JButton button) {

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(
                TEXT_COLOR
        );

        button.setBackground(
                SECONDARY
        );
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
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
    }

    // ==================================================
    // SHOW/HIDE AUTHOR & PUBLISHER
    // ==================================================

    private void updateItemTypeFields() {

        String selectedType =
                (String) comboItemType.getSelectedItem();

        boolean isBook =
                "Book".equals(selectedType);

        lblAuthor.setVisible(
                isBook
        );

        txtAuthor.setVisible(
                isBook
        );

        lblPublisher.setVisible(
                !isBook
        );

        txtPublisher.setVisible(
                !isBook
        );

        revalidate();
        repaint();
    }

    // ==================================================
    // ADD ITEM
    // ==================================================

    private void addItem() {

        try {

            String type =
                    (String) comboItemType.getSelectedItem();

            String title =
                    txtTitle.getText().trim();

            String copiesText =
                    txtCopies.getText().trim();

            // ------------------------------------------
            // TITLE VALIDATION
            // ------------------------------------------

            if (title.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Title cannot be empty.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtTitle.requestFocus();

                return;
            }

            // ------------------------------------------
            // COPIES VALIDATION
            // ------------------------------------------

            if (copiesText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the number of copies.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtCopies.requestFocus();

                return;
            }

            int totalCopies;

            try {

                totalCopies =
                        Integer.parseInt(
                                copiesText
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total Copies must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                txtCopies.requestFocus();

                return;
            }

            if (totalCopies <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total Copies must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                txtCopies.requestFocus();

                return;
            }

            // ==========================================
            // CREATE BOOK
            // ==========================================

            if ("Book".equals(type)) {

                String author =
                        txtAuthor.getText().trim();

                if (author.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Author cannot be empty.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    txtAuthor.requestFocus();

                    return;
                }

                Book book =
                        new Book(
                                title,
                                totalCopies,
                                author
                        );

                libraryService.registerItem(
                        book
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Book added successfully!\n\n"
                                + "Title: "
                                + title
                                + "\nAuthor: "
                                + author
                                + "\nTotal Copies: "
                                + totalCopies,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // ==========================================
            // CREATE MAGAZINE
            // ==========================================

            else {

                String publisher =
                        txtPublisher.getText().trim();

                if (publisher.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Publisher cannot be empty.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    txtPublisher.requestFocus();

                    return;
                }

                Magazine magazine =
                        new Magazine(
                                title,
                                totalCopies,
                                publisher
                        );

                libraryService.registerItem(
                        magazine
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Magazine added successfully!\n\n"
                                + "Title: "
                                + title
                                + "\nPublisher: "
                                + publisher
                                + "\nTotal Copies: "
                                + totalCopies,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // ==========================================
            // CLEAR AFTER SUCCESS
            // ==========================================

            clearFields();

        } catch (InvalidInputException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unexpected error: "
                            + ex.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==================================================
    // CLEAR
    // ==================================================

    private void clearFields() {

        comboItemType.setSelectedIndex(0);

        txtTitle.setText("");
        txtCopies.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");

        updateItemTypeFields();

        txtTitle.requestFocus();
    }
}