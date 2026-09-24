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
        setLayout(new GridLayout(6, 2, 10, 10));

        txtTitle = new JTextField();
        txtMemberId = new JTextField();
        txtEmail = new JTextField();
        txtBorrowCount = new JTextField();
        comboType = new JComboBox<>(new String[]{"Student", "Faculty"});

        JButton btnIssue = new JButton("Issue Item");

        add(new JLabel("Item Title:"));
        add(txtTitle);
        add(new JLabel("Member ID (starts with 'M'):"));
        add(txtMemberId);
        add(new JLabel("Member Email:"));
        add(txtEmail);
        add(new JLabel("Member Type:"));
        add(comboType);
        add(new JLabel("Current Borrowed Count:"));
        add(txtBorrowCount);
        add(new JLabel(""));
        add(btnIssue);

        btnIssue.addActionListener(e -> {
            try {
                String title = txtTitle.getText().trim();
                String memberId = txtMemberId.getText().trim();
                String email = txtEmail.getText().trim();
                String type = (String) comboType.getSelectedItem();
                int currentBorrowed = Integer.parseInt(txtBorrowCount.getText().trim());

                libraryService.processIssue(title, memberId, email, type, 1, currentBorrowed);

                JOptionPane.showMessageDialog(this, "Successfully issued to " + memberId);
                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Borrowed count must be a number!", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (ItemNotFoundException | MemberNotFoundException | InvalidInputException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Issue Error", JOptionPane.ERROR_MESSAGE);
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