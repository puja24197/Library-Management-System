import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileManager {

        private static final String LibraryFile = "Mylibrary_data.txt";
        public static void saveData(List<LibraryItems> items) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LibraryFile))) {
                for (LibraryItems item : items) {
                    if (item instanceof Book) {
                        Book b = (Book) item;
                        writer.write("BOOK," + b.getTitle() +"," + b.getTotalCopies()+ "," + b.getAuthor());
                    } else if (item instanceof Magazine) {
                        Magazine m = (Magazine) item;
                        writer.write("MAGAZINE," + m.getTitle() + "," + m.getTotalCopies() + "," + m.getPublisher());
                    }
                    writer.newLine();
                }
                System.out.println("Data saved to " + LibraryFile);
            } catch (IOException e) {
                System.out.println("Could not load the library data:" + e.getMessage());
            }
        }

        public static List<LibraryItems> loadData() {
            List<LibraryItems> items = new ArrayList<>();
            File file = new File(LibraryFile);
            if (!file.exists()) return items;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals("BOOK")) {
                        items.add(new Book(parts[1], Integer.parseInt(parts[2]), parts[3]));
                    } else if (parts[0].equals("MAGAZINE")) {
                        items.add(new Magazine(parts[1], Integer.parseInt(parts[2]), parts[3]));
                    }
                }
                System.out.println("Library Data loaded successfully.") ;
            } catch (IOException e) {
                System.out.println("File loading error: " + e.getMessage());
            }
            return items;
        }
    }
