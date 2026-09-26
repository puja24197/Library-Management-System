package LibraryGUI;

import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Model.LibraryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DeleteItemPanel extends JPanel {

    private LibraryService libraryService;

    private JTextField txtTitle;
    private JButton btnDelete;
    private JButton btnClear;

    private final Color BACKGROUND =
            new Color(245, 247, 252);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color DANGER =
            new Color(220, 38, 38);

    private final Color DANGER_HOVER =
            new Color(185, 28, 28);

    private final Color SECONDARY =
            new Color(226, 232, 240);

    private final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private final Color SUBTITLE_COLOR =
            new Color(100, 116, 139);

    private final Color WARNING_BG =
            new Color(254, 242, 242);

    public DeleteItemPanel(LibraryService service) {

        this.libraryService = service;

        setLayout(new BorderLayout());

        setBackground(BACKGROUND);

        setBorder(new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(
                        "Delete Library Item",
                        SwingConstants.CENTER
                );

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT
        );

        titleLabel.setFont(new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setForeground(DANGER
        );

        JLabel subtitleLabel = new JLabel(
                        "Remove an item from the library",
                        SwingConstants.CENTER
                );

        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT
        );

        subtitleLabel.setFont(new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(SUBTITLE_COLOR
        );

        headerPanel.add(titleLabel);

        headerPanel.add(Box.createVerticalStrut(5));

        headerPanel.add(subtitleLabel);

        headerPanel.add(Box.createVerticalStrut(15));

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel cardPanel = new JPanel(new GridBagLayout());

        cardPanel.setBackground(CARD_COLOR);

        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(
                                25,
                                35,
                                25,
                                35
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        JLabel lblTitle = createLabel("Item Title");

        txtTitle = new JTextField(25);

        styleTextField(txtTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        cardPanel.add(lblTitle, gbc);

        gbc.gridx = 1;

        cardPanel.add(txtTitle, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        btnDelete = new JButton("Delete Item");

        btnClear = new JButton("Clear");

        styleDeleteButton(btnDelete);

        styleClearButton(btnClear);

        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;

        cardPanel.add(buttonPanel, gbc);
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(cardPanel);

        add(centerWrapper, BorderLayout.CENTER);

        JPanel warningPanel = new JPanel(new BorderLayout());

        warningPanel.setBackground(WARNING_BG);

        warningPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(254, 202, 202)
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel warningLabel = new JLabel(
                        "<html><center>"
                                + "<b>Warning:</b> Deleting an item "
                                + "cannot be undone."
                                + "<br>"
                                + "An item cannot be deleted while "
                                + "one of its copies is issued."
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        warningLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        warningLabel.setForeground(new Color(153, 27, 27));

        warningPanel.add(warningLabel, BorderLayout.CENTER);

        add(
                warningPanel,
                BorderLayout.SOUTH
        );

        btnDelete.addActionListener(
                e -> deleteItem()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );
    }

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(new Font("SansSerif", Font.BOLD, 14));

        label.setForeground(TEXT_COLOR);

        return label;
    }

    private void styleTextField(JTextField field) {

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));

        field.setForeground(TEXT_COLOR);

        field.setBackground(new Color(248, 250, 252));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(203, 213, 225)
                        ),
                        new EmptyBorder(8, 10, 8, 10
                        )
                )
        );
    }

    private void styleDeleteButton(JButton button) {
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setOpaque(true);
        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(DANGER);

        button.setFocusPainted(false);

        button.setBorder(new EmptyBorder(10, 25, 10, 25));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            java.awt.event.MouseEvent e) {

                        button.setBackground(
                                DANGER_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {

                        button.setBackground(DANGER);
                    }
                }
        );
    }


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


    private void deleteItem() {

        try {

            String title =
                    txtTitle.getText().trim();


            if (title.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the item title.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                txtTitle.requestFocus();

                return;
            }

            if (libraryService.searchByTitle(title)
                    == null) {

                throw new ItemNotFoundException(
                        "No item found with title: "
                                + title
                );
            }

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete:\n\n"
                                    + "\"" + title + "\""
                                    + "\n\nThis action cannot be undone.",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (confirmation != JOptionPane.YES_OPTION) {

                return;
            }


            libraryService.removeItem(title);

            JOptionPane.showMessageDialog(
                    this,
                    "Item deleted successfully!\n\n"
                            + "Deleted Item: "
                            + title,
                    "Delete Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

        } catch (ItemNotFoundException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Item Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (InvalidInputException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Cannot Delete Item",
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

    private void clearFields() {

        txtTitle.setText("");

        txtTitle.requestFocus();
    }
}