package LibraryGUI;

import javax.swing.*;
import java.awt.*;
import Exception.InvalidInputException;
import Exception.ItemNotFoundException;
import Exception.MemberNotFoundException;
import Model.LibraryService;

public class IssueItemPanel extends JPanel {
    private LibraryService libraryService;
    private JTextField txtTitle, txtMemberId, txtEmail, txtBorrowCount;
    private JComboBox<String> comboType;

    public IssueItemPanel(LibraryService service) {
        this.libraryService = service;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtTitle = new JTextField(20);
        txtMemberId = new JTextField(20);
        txtEmail = new JTextField(20);
        txtBorrowCount = new JTextField(20);
        txtBorrowCount.setText("0");
        txtBorrowCount.setEditable(false);
        comboType = new JComboBox<>(new String[]{"Student", "Faculty"});
        txtBorrowCount.setEditable(false);
        JButton btnIssue = new JButton("Register & Issue Item");

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Item Title:"), gbc);
        gbc.gridx = 1; add(txtTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Member ID:"), gbc);
        gbc.gridx = 1; add(txtMemberId, gbc);

        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Member Email:"), gbc);
        gbc.gridx = 1; add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("Member Type:"), gbc);
        gbc.gridx = 1; add(comboType, gbc);

        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Currently Borrowed:"), gbc);
        gbc.gridx = 1; add(txtBorrowCount, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(btnIssue, gbc);

        txtMemberId.addActionListener(e -> {
            String memberId = txtMemberId.getText().trim();
            if (memberId.isEmpty()) return;
                int currentBorrowed = libraryService.getBorrowedCountForMember(memberId);
                txtBorrowCount.setText(String.valueOf(currentBorrowed));
            });

        btnIssue.addActionListener(e -> {
            try {
                String title = txtTitle.getText().trim();
                String memberId = txtMemberId.getText().trim();
                String email = txtEmail.getText().trim();
                String type = (String) comboType.getSelectedItem();
                int currentBorrowed = 0;
                String borrowedText = txtBorrowCount.getText().trim();
                if (!borrowedText.isEmpty()) {
                    currentBorrowed = Integer.parseInt(borrowedText);
                }
                if (title.isEmpty() || memberId.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Title and Member ID are required!", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                libraryService.processIssue(title, memberId, email, type, 1, currentBorrowed);
                JOptionPane.showMessageDialog(this,
                        "Successfully Issued!\nMember ID: " + memberId + "\nItem: " + title,
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } catch (ItemNotFoundException | MemberNotFoundException | InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Issue Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void clearFields() {
        txtTitle.setText("");
        txtMemberId.setText("");
        txtEmail.setText("");
        txtBorrowCount.setText("");
    }
}