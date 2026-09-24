package LibraryGUI;

import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Model.LibraryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ReturnItemPanel extends JPanel {

    private LibraryService libraryService;

    private JTextField txtTitle;
    private JTextField txtReturnDay;

    private JLabel lblFineResult;

    // ==========================================
    // COLORS
    // ==========================================

    private final Color BACKGROUND =
            new Color(245, 247, 252);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color PRIMARY =
            new Color(16, 185, 129);

    private final Color PRIMARY_HOVER =
            new Color(5, 150, 105);

    private final Color SECONDARY =
            new Color(226, 232, 240);

    private final Color TEXT_COLOR =
            new Color(31, 41, 55);

    private final Color SUBTITLE_COLOR =
            new Color(100, 116, 139);

    private final Color INFO_BG =
            new Color(236, 253, 245);

    private final Color INFO_TEXT =
            new Color(6, 95, 70);

    public ReturnItemPanel(LibraryService service) {

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
                        "Return Library Item",
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
                        "Return an issued item and calculate late fine",
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
                                25,
                                35,
                                25,
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
        // ITEM TITLE
        // ==========================================

        txtTitle =
                new JTextField(25);

        styleTextField(txtTitle);

        addField(
                cardPanel,
                gbc,
                0,
                "Item Title",
                txtTitle
        );

        // ==========================================
        // RETURN DAY
        // ==========================================

        txtReturnDay =
                new JTextField(25);

        styleTextField(txtReturnDay);

        addField(
                cardPanel,
                gbc,
                1,
                "Return Day",
                txtReturnDay
        );

        // ==========================================
        // BUTTONS
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

        JButton btnReturn =
                new JButton(
                        "Return Item"
                );

        JButton btnClear =
                new JButton(
                        "Clear"
                );

        stylePrimaryButton(btnReturn);

        styleClearButton(btnClear);

        buttonPanel.add(btnReturn);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 2;
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
        // FINE RESULT CARD
        // ==========================================

        JPanel resultPanel =
                new JPanel(
                        new BorderLayout()
                );

        resultPanel.setBackground(
                INFO_BG
        );

        resultPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(167, 243, 208)
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        lblFineResult =
                new JLabel(
                        "Late Fine: 0.00 BDT",
                        SwingConstants.CENTER
                );

        lblFineResult.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        lblFineResult.setForeground(
                INFO_TEXT
        );

        resultPanel.add(
                lblFineResult,
                BorderLayout.CENTER
        );

        add(
                resultPanel,
                BorderLayout.SOUTH
        );

        // ==========================================
        // BUTTON ACTION
        // ==========================================

        btnReturn.addActionListener(
                e -> returnItem()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );
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

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;

        JLabel fieldLabel =
                new JLabel(label);

        fieldLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        fieldLabel.setForeground(
                TEXT_COLOR
        );

        panel.add(
                fieldLabel,
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
    // RETURN ITEM
    // ==================================================

    private void returnItem() {

        try {

            String title =
                    txtTitle.getText().trim();

            String returnDayText =
                    txtReturnDay.getText().trim();

            // ==========================================
            // VALIDATION
            // ==========================================

            if (title.isEmpty()
                    || returnDayText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter Item Title and Return Day.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // ==========================================
            // RETURN DAY VALIDATION
            // ==========================================

            int returnDay;

            try {

                returnDay =
                        Integer.parseInt(
                                returnDayText
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Return Day must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (returnDay <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Return Day must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            // ==========================================
            // RETURN THROUGH SERVICE
            // ==========================================

            double fine =
                    libraryService.returnItem(
                            title,
                            returnDay
                    );

            // ==========================================
            // SHOW FINE
            // ==========================================

            lblFineResult.setText(
                    String.format(
                            "Late Fine: %.2f BDT",
                            fine
                    )
            );

            if (fine > 0) {

                lblFineResult.setForeground(
                        new Color(185, 28, 28)
                );

            } else {

                lblFineResult.setForeground(
                        INFO_TEXT
                );
            }

            // ==========================================
            // SUCCESS MESSAGE
            // ==========================================

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "Item returned successfully!\n\n"
                                    + "Item: %s\n"
                                    + "Late Fine: %.2f BDT",
                            title,
                            fine
                    ),
                    "Return Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

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
                    "Return Error",
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

        txtTitle.setText("");
        txtReturnDay.setText("");

        lblFineResult.setText(
                "Late Fine: 0.00 BDT"
        );

        lblFineResult.setForeground(
                INFO_TEXT
        );

        txtTitle.requestFocus();
    }
}