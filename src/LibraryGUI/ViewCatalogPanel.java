package LibraryGUI;
import Model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import Exception.ItemNotFoundException;
    public class ViewCatalogPanel extends JPanel{
        private LibraryService libraryService;
        private JTextArea txtAreaCatalog;
        private JTextField txtDeleteTitle;
        public ViewCatalogPanel(LibraryService service){
              this.libraryService = service;
               setLayout(new BorderLayout(10, 10));

            txtAreaCatalog =new JTextArea();
            txtAreaCatalog.setEditable (false);
            add(new JScrollPane(txtAreaCatalog), BorderLayout.CENTER);

             JPanel bottomPanel = new JPanel(new FlowLayout());
              JButton btnRefresh = new JButton("Refresh Catalog");
            txtDeleteTitle = new JTextField(12);
            JButton btnDelete = new JButton("Delete Item");

            bottomPanel.add(btnRefresh);
            bottomPanel.add(new JLabel("Title to Delete:"));
            bottomPanel.add(txtDeleteTitle);
            bottomPanel.add(btnDelete);
            add(bottomPanel, BorderLayout.SOUTH);

            btnRefresh.addActionListener(e ->refreshCatalog()) ;
            btnDelete.addActionListener(e ->{
                String title = txtDeleteTitle.getText().trim();
                try {
                    libraryService.removeItem(title);
                    JOptionPane.showMessageDialog(this,"The catalog is empty right now....Check back soon!");
                    refreshCatalog();
                    txtDeleteTitle.setText("");
                } catch (ItemNotFoundException ex) {
                    JOptionPane.showMessageDialog(this,ex.getMessage(),"Deletion Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Unexpected Error:"+ ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            refreshCatalog();
        }
        public void refreshCatalog(){
            txtAreaCatalog.setText("");
            List<LibraryItems> catalog=libraryService.getCatalog();
            if (catalog.isEmpty()){
                txtAreaCatalog.setText("Sorry!All copies of this item are currently checked out.") ;
            } else {
                for (LibraryItems item : catalog){
                    txtAreaCatalog.append(item.getDetails()+"\n---------------------------\n");
                }
            }
        }

    }