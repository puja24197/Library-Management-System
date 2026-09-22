package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileManager {

        private static final String LibraryFile = "Mylibrary_data.txt";
        public static void saveData(List<LibraryItems> items) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LibraryFile))) {
                for (LibraryItems item : items) {
                    String safeTitle = item.getTitle().replace(",", " ");
                    if (item instanceof Book) {
                        Book b = (Book) item;
                        String safeAuthor = b.getAuthor().replace(",", " ");
                        writer.write("BOOK," + safeTitle +"," + b.getTotalCopies()+ "," + safeAuthor+ "," + b.isIssued());
                    } else if (item instanceof Magazine) {
                        Magazine m = (Magazine) item;
                        String safePublisher = m.getPublisher().replace(",", " ");
                        writer.write("MAGAZINE," + safeTitle+ "," + m.getTotalCopies() + "," + safePublisher+ "," + m.isIssued());
                    }
                    writer.newLine();
                }
                System.out.println("Data saved to " + LibraryFile);
            } catch (IOException e) {
                System.out.println("Could not save the library data:" + e.getMessage());
            }
        }

        public static List<LibraryItems> loadData() {
            List<LibraryItems> items = new ArrayList<>();
            File file = new File(LibraryFile);
            if (!file.exists()) return items;

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine())!= null) {
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split(",");
                    if (parts[0].equals("BOOK")) {
                        Book book = new Book(parts[1], Integer.parseInt(parts[2]), parts[3]);
                        boolean isIssued = Boolean.parseBoolean(parts[4]);
                        book.setIssued(isIssued);
                        items.add(book);
                    } else if (parts[0].equals("MAGAZINE")) {
                        Magazine magazine = new Magazine(parts[1], Integer.parseInt(parts[2]), parts[3]);
                        boolean isIssued = Boolean.parseBoolean(parts[4]);
                        magazine.setIssued(isIssued);
                        items.add(magazine);
                    }
                }
                System.out.println("Library Data loaded successfully.") ;
            } catch (IOException e) {
                System.out.println("File loading error: " + e.getMessage());
            }
            return items;
        }



    }
