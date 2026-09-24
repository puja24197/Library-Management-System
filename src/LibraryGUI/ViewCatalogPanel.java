package LibraryGUI;
import Model.Book;
import Model.LibraryItems;
import Model.LibraryService;
import Model.Magazine;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCatalogPanel extends JPanel{
    private LibraryService libraryService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField deleteTitleField;

    public ViewCatalogPanel(LibraryService libraryService){
        this.libraryService = libraryService;
        setLayout(new BorderLayout());

        String[] columnNames = {"Type", "Title", "Author / Publisher", "Available Copies"};
        tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        JButton refreshBtn = new JButton("Refresh Catalog");
        JLabel deleteLabel = new JLabel("Title to Delete:");
        deleteTitleField = new JTextField(15);
        JButton deleteBtn = new JButton("Delete Item");

        bottomPanel.add(refreshBtn);
        bottomPanel.add(deleteLabel);
        bottomPanel.add(deleteTitleField);
        bottomPanel.add(deleteBtn);

        add(bottomPanel, BorderLayout.SOUTH);
        refreshBtn.addActionListener(e -> refreshCatalog());

        deleteBtn.addActionListener(e ->{
            String title = deleteTitleField.getText().trim();
            if (!title.isEmpty()){
                boolean deleted = false;
                List<LibraryItems> items = libraryService.getCatalog();
                for (LibraryItems item : items){
                    if (item.getTitle().equalsIgnoreCase(title)){
                        items.remove(item);
                        deleted = true;
                        break;
                    }
                }
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Item deleted successfully!");
                    deleteTitleField.setText("");
                    refreshCatalog();
                } else {
                    JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a title to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        refreshCatalog();
    }
    public void refreshCatalog() {
        tableModel.setRowCount(0);
        List<LibraryItems> items = libraryService.getCatalog();
        for (LibraryItems item : items) {
            String type = "";
            String detail = "";

            if (item instanceof Book) {
                type = "Book";
                detail = ((Book) item).getAuthor();
            } else if (item instanceof Magazine) {
                type = "Magazine";
                detail = ((Magazine) item).getPublisher();
            }
            Object[] rowData = {
                    type,
                    item.getTitle(),
                    detail,
                    item.getAvailableCopies()
            };
            tableModel.addRow(rowData);
        }
    }
}