package LibraryGUI;

import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Exception.MemberNotFoundException;
import Model.LibraryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class IssueItemPanel extends JPanel {

    private LibraryService libraryService;

    private JTextField txtTitle;
    private JTextField txtMemberId;
    private JTextField txtEmail;
    private JTextField txtIssueDay;
    private JTextField txtQuantity;

    private JComboBox<String> comboType;


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

    public IssueItemPanel(LibraryService service) {

        this.libraryService = service;

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

        JPanel headerPanel = new JPanel();

        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );

        headerPanel.setOpaque(false);

        JLabel titleLabel =
                new JLabel("Issue Library Item", SwingConstants.CENTER);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titleLabel.setFont(
                new Font("SansSerif", Font.BOLD, 26)
        );

        titleLabel.setForeground(PRIMARY);

        JLabel subtitleLabel =
                new JLabel(
                        "Issue a book or magazine to a library member",
                        SwingConstants.CENTER
                );

        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
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

        JPanel cardPanel = new JPanel(new GridBagLayout());

        cardPanel.setBackground(CARD_COLOR);

        cardPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(226, 232, 240)
                        ),
                        new EmptyBorder(20, 30, 20, 30
                        )
                )
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(
                        7,
                        10,
                        7,
                        10
                );

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        txtTitle = new JTextField(25);

        styleTextField(txtTitle);

        addField(
                cardPanel,
                gbc,
                0,
                "Item Title",
                txtTitle
        );

        txtMemberId = new JTextField(25);

        styleTextField(txtMemberId);

        addField(
                cardPanel,
                gbc,
                1,
                "Member ID",
                txtMemberId
        );

        txtEmail = new JTextField(25);
        styleTextField(txtEmail);

        addField(cardPanel, gbc, 2, "Member Email", txtEmail);

        comboType = new JComboBox<>(new String[]{"Student", "Faculty"});

        styleComboBox(comboType);

        addField(
                cardPanel,
                gbc,
                3,
                "Member Type",
                comboType
        );

        txtIssueDay = new JTextField(25);

        styleTextField(txtIssueDay);

        addField(cardPanel,
                gbc,
                4,
                "Issue Day",
                txtIssueDay
        );
        txtQuantity = new JTextField(25);
        txtQuantity.setText("1");
        styleTextField(txtQuantity);
        addField(cardPanel, gbc, 5, "Quantity", txtQuantity);

        JPanel buttonPanel = new JPanel(new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttonPanel.setOpaque(false);

        JButton btnIssue = new JButton("Issue Item");

        JButton btnClear = new JButton("Clear");

        stylePrimaryButton(btnIssue);

        styleClearButton(btnClear);

        buttonPanel.add(btnIssue);
        buttonPanel.add(btnClear);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;

        cardPanel.add(
                buttonPanel,
                gbc
        );
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(cardPanel);

        add(
                centerWrapper,
                BorderLayout.CENTER
        );

        JPanel infoPanel = new JPanel(new BorderLayout());

        infoPanel.setBackground(INFO_BG);

        infoPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(199, 210, 254)
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel infoLabel = new JLabel(
                        "<html><center>"
                                + "<b>Borrowing Rules</b>"
                                + "<br>"
                                + "Student: Maximum 3 items / 15 days"
                                + "<br>"
                                + "Faculty: Maximum 5 items / 30 days"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        infoLabel.setFont(new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        infoLabel.setForeground(INFO_TEXT);

        infoPanel.add(infoLabel, BorderLayout.CENTER);

        add(
                infoPanel,
                BorderLayout.SOUTH
        );


        btnIssue.addActionListener(
                e -> issueItem()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );
    }

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
    private void stylePrimaryButton(JButton button) {
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setOpaque(true);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));

        button.setForeground(Color.WHITE);

        button.setBackground(PRIMARY);

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

    private void styleClearButton(JButton button) {
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        button.setOpaque(true);

        button.setFont(new Font("SansSerif", Font.BOLD, 14));

        button.setForeground(TEXT_COLOR);

        button.setBackground(SECONDARY);

        button.setFocusPainted(false);

        button.setBorder(new EmptyBorder(10, 25, 10, 25));

        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void issueItem() {

        try {

            String title = txtTitle.getText().trim();

            String memberId = txtMemberId.getText().trim();

            String email = txtEmail.getText().trim();

            String memberType = (String) comboType.getSelectedItem();

            String issueDayText = txtIssueDay.getText().trim();

            if (title.isEmpty()
                    || memberId.isEmpty()
                    || email.isEmpty()
                    || issueDayText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int issueDay;

            try {

                issueDay =
                        Integer.parseInt(
                                issueDayText
                        );

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Issue Day must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            if (issueDay <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Issue Day must be greater than 0.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            int currentBorrowed =
                    libraryService
                            .getBorrowedCountForMember(
                                    memberId
                            );

            libraryService.processIssue(
                    title,
                    memberId,
                    email,
                    memberType,
                    issueDay,
                    currentBorrowed
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Item issued successfully!\n\n"
                            + "Item: " + title
                            + "\nMember ID: " + memberId
                            + "\nMember Type: " + memberType,
                    "Issue Successful",
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

        } catch (MemberNotFoundException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Member Error",
                    JOptionPane.ERROR_MESSAGE
            );

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

    private void clearFields() {

        txtTitle.setText("");
        txtMemberId.setText("");
        txtEmail.setText("");
        txtIssueDay.setText("");

        comboType.setSelectedIndex(0);

        txtTitle.requestFocus();
    }
}