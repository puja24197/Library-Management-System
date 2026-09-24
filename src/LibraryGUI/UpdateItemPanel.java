package LibraryGUI;

import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Model.LibraryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UpdateItemPanel extends JPanel {

    private LibraryService libraryService;

    private JComboBox<String> comboItemType;

    private JTextField txtOldTitle;
    private JTextField txtNewTitle;
    private JTextField txtTotalCopies;
    private JTextField txtAuthor;
    private JTextField txtPublisher;

    private JLabel lblAuthor;
    private JLabel lblPublisher;

    private JButton btnUpdate;
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
            new Color(31, 41, 55);

    private final Color SUBTITLE_COLOR =
            new Color(100, 116, 139);

    private final Color INFO_BG =
            new Color(238, 242, 255);

    private final Color INFO_TEXT =
            new Color(55, 48, 163);

    public UpdateItemPanel(LibraryService service) {

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
                        "Update Library Item",
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
                        "Edit the information of an existing library item",
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
                                15,
                                30,
                                15,
                                30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        10,
                        5,
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
        // CURRENT TITLE
        // ==========================================

        txtOldTitle =
                new JTextField(25);

        styleTextField(txtOldTitle);

        addField(
                cardPanel,
                gbc,
                1,
                "Current Title",
                txtOldTitle
        );

        // ==========================================
        // NEW TITLE
        // ==========================================

        txtNewTitle =
                new JTextField(25);

        styleTextField(txtNewTitle);

        addField(
                cardPanel,
                gbc,
                2,
                "New Title",
                txtNewTitle
        );

        // ==========================================
        // TOTAL COPIES
        // ==========================================

        txtTotalCopies =
                new JTextField(25);

        styleTextField(txtTotalCopies);

        addField(
                cardPanel,
                gbc,
                3,
                "New Total Copies",
                txtTotalCopies
        );

        // ==========================================
        // AUTHOR
        // ==========================================

        lblAuthor =
                createLabel("New Author");

        txtAuthor =
                new JTextField(25);

        styleTextField(txtAuthor);

        addField(
                cardPanel,
                gbc,
                4,
                lblAuthor,
                txtAuthor
        );

        // ==========================================
        // PUBLISHER
        // ==========================================

        lblPublisher =
                createLabel("New Publisher");

        txtPublisher =
                new JTextField(25);

        styleTextField(txtPublisher);

        addField(
                cardPanel,
                gbc,
                5,
                lblPublisher,
                txtPublisher
        );

        // Initially Book selected
        lblPublisher.setVisible(false);
        txtPublisher.setVisible(false);

        // ==========================================
        // BUTTONS
        // ==========================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                8
                        )
                );

        buttonPanel.setOpaque(false);

        btnUpdate =
                new JButton(
                        "Update Item"
                );

        btnClear =
                new JButton(
                        "Clear"
                );

        stylePrimaryButton(btnUpdate);

        styleClearButton(btnClear);

        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 6;
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
        // INFORMATION CARD
        // ==========================================

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
                                + "<b>Update Information</b>"
                                + "<br>"
                                + "Enter the current title and the new information."
                                + "<br>"
                                + "The updated record will be saved automatically."
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

        // ==========================================
        // ITEM TYPE CHANGE
        // ==========================================

        comboItemType.addActionListener(
                e -> updateItemTypeFields()
        );

        // ==========================================
        // UPDATE BUTTON
        // ==========================================

        btnUpdate.addActionListener(
                e -> updateItem()
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

    private JLabel createLabel(
            String text) {

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
                new Color(248, 250, 252)
        );

        comboBox.setBorder(
                BorderFactory.createLineBorder(
                        new Color(203, 213, 225)
                )
        );
    }

    // ==================================================
    // PRIMARY BUTTON STYLE
    // ==================================================

    private void stylePrimaryButton(
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

    // ==================================================
    // CLEAR BUTTON STYLE
    // ==================================================

    private void styleClearButton(
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
    // SHOW/HIDE BOOK OR MAGAZINE FIELDS
    // ==================================================

    private void updateItemTypeFields() {

        String selectedType =
                (String) comboItemType.getSelectedItem();

        boolean isBook =
                "Book".equals(selectedType);

        lblAuthor.setVisible(isBook);
        txtAuthor.setVisible(isBook);

        lblPublisher.setVisible(!isBook);
        txtPublisher.setVisible(!isBook);

        revalidate();
        repaint();
    }

    // ==================================================
    // UPDATE ITEM
    // ==================================================

    private void updateItem() {

        try {

            // ==========================================
            // GET INPUT
            // ==========================================

            String type =
                    (String) comboItemType.getSelectedItem();

            String oldTitle =
                    txtOldTitle.getText().trim();

            String newTitle =
                    txtNewTitle.getText().trim();

            String copiesText =
                    txtTotalCopies.getText().trim();

            // ==========================================
            // VALIDATE CURRENT TITLE
            // ==========================================

            if (oldTitle.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the current item title.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtOldTitle.requestFocus();

                return;
            }

            // ==========================================
            // VALIDATE NEW TITLE
            // ==========================================

            if (newTitle.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "New title cannot be empty.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtNewTitle.requestFocus();

                return;
            }

            // ==========================================
            // VALIDATE COPIES
            // ==========================================

            if (copiesText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the new total number of copies.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtTotalCopies.requestFocus();

                return;
            }

            int newTotalCopies;

            try {

                newTotalCopies =
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

                txtTotalCopies.requestFocus();

                return;
            }

            if (newTotalCopies <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Total Copies must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                txtTotalCopies.requestFocus();

                return;
            }

            // ==========================================
            // CHECK WHETHER ITEM EXISTS
            // ==========================================

            if (libraryService.searchByTitle(oldTitle)
                    == null) {

                throw new ItemNotFoundException(
                        "No item found with title: "
                                + oldTitle
                );
            }

            // ==========================================
            // UPDATE BOOK
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

                libraryService.updateBook(
                        oldTitle,
                        newTitle,
                        newTotalCopies,
                        author
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Book updated successfully!\n\n"
                                + "Old Title: "
                                + oldTitle
                                + "\nNew Title: "
                                + newTitle
                                + "\nTotal Copies: "
                                + newTotalCopies
                                + "\nAuthor: "
                                + author,
                        "Update Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                // ==========================================
                // UPDATE MAGAZINE
                // ==========================================

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

                libraryService.updateMagazine(
                        oldTitle,
                        newTitle,
                        newTotalCopies,
                        publisher
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Magazine updated successfully!\n\n"
                                + "Old Title: "
                                + oldTitle
                                + "\nNew Title: "
                                + newTitle
                                + "\nTotal Copies: "
                                + newTotalCopies
                                + "\nPublisher: "
                                + publisher,
                        "Update Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

            // ==========================================
            // CLEAR AFTER SUCCESS
            // ==========================================

            clearFields();

        } catch (ItemNotFoundException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Item Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (InvalidInputException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Update Error",
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

        txtOldTitle.setText("");
        txtNewTitle.setText("");
        txtTotalCopies.setText("");
        txtAuthor.setText("");
        txtPublisher.setText("");

        updateItemTypeFields();

        txtOldTitle.requestFocus();
    }
}