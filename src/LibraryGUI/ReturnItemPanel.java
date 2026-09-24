package LibraryGUI;

import javax.swing.*;
import java.awt.*;
import Model.LibraryItems;
import Model.LibraryService;
import Model.FileManager;

public class ReturnItemPanel extends JPanel {
    private LibraryService libraryService;
    private JTextField txtTitle, txtReturnDay;
    private JLabel lblFineResult;

    public ReturnItemPanel(LibraryService service) {
        this.libraryService = service;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtTitle = new JTextField(20);
        txtReturnDay = new JTextField(20);
        JButton btnReturn = new JButton("Calculate Fine & Return Item");

        lblFineResult = new JLabel("Late Fine: 0.00 BDT", SwingConstants.CENTER);
        lblFineResult.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblFineResult.setForeground(Color.BLUE);

        gbc.gridx = 0; gbc.gridy = 0; add(new JLabel("Item Title:"), gbc);
        gbc.gridx = 1; add(txtTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Return Day (e.g., 10, 15):"), gbc);
        gbc.gridx = 1; add(txtReturnDay, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(btnReturn, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(lblFineResult, gbc);

        btnReturn.addActionListener(e -> {
            String title = txtTitle.getText().trim();
            String dayStr = txtReturnDay.getText().trim();

            if (title.isEmpty() || dayStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both Title and Return Day!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int returnDay = Integer.parseInt(dayStr);
                LibraryItems item = libraryService.searchByTitle(title);

                if (item != null) {
                    if (!item.isIssued()) {
                        JOptionPane.showMessageDialog(this, "This item is not currently issued to anyone!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    double fine = item.returnFromMember(returnDay);
                    FileManager.saveData(libraryService.getCatalog());

                    lblFineResult.setText("Item Returned! Total Late Fine: " + fine + " BDT");
                    lblFineResult.setForeground(fine > 0 ? Color.RED : new Color(0, 128, 0));

                    JOptionPane.showMessageDialog(this, "Item successfully returned.\nLate Fine: " + fine + " BDT");
                    txtTitle.setText("");
                    txtReturnDay.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Item not found in catalog!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Return Day must be a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}