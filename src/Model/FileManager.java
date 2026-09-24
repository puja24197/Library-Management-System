package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String LIBRARY_FILE = "Mylibrary_data.txt";

    // =========================
    // SAVE DATA
    // =========================
    public static void saveData(List<LibraryItems> items) {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(LIBRARY_FILE))) {

            for (LibraryItems item : items) {

                if (item instanceof Book) {

                    Book book = (Book) item;

                    writer.write(
                            "BOOK,"
                                    + clean(book.getTitle()) + ","
                                    + book.getTotalCopies() + ","
                                    + book.getAvailableCopies() + ","
                                    + clean(book.getAuthor()) + ","
                                    + book.isIssued() + ","
                                    + clean(book.getMemberId()) + ","
                                    + clean(book.getMemberEmail()) + ","
                                    + book.getDueDate()
                    );

                } else if (item instanceof Magazine) {

                    Magazine magazine = (Magazine) item;

                    writer.write(
                            "MAGAZINE,"
                                    + clean(magazine.getTitle()) + ","
                                    + magazine.getTotalCopies() + ","
                                    + magazine.getAvailableCopies() + ","
                                    + clean(magazine.getPublisher()) + ","
                                    + magazine.isIssued() + ","
                                    + clean(magazine.getMemberId()) + ","
                                    + clean(magazine.getMemberEmail()) + ","
                                    + magazine.getDueDate()
                    );
                }

                writer.newLine();
            }

            System.out.println("Data saved successfully to "
                    + LIBRARY_FILE);

        } catch (IOException e) {

            System.out.println(
                    "Could not save library data: "
                            + e.getMessage()
            );
        }
    }


    // =========================
    // LOAD DATA
    // =========================
    public static List<LibraryItems> loadData() {

        List<LibraryItems> items = new ArrayList<>();

        File file = new File(LIBRARY_FILE);

        if (!file.exists()) {
            System.out.println("No existing library data found.");
            return items;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",", -1);

                try {

                    // =========================
                    // LOAD BOOK
                    // =========================
                    if (parts[0].equalsIgnoreCase("BOOK")) {

                        if (parts.length < 9) {
                            System.out.println(
                                    "Invalid BOOK record skipped."
                            );
                            continue;
                        }

                        String title = parts[1];

                        int totalCopies =
                                Integer.parseInt(parts[2]);

                        int availableCopies =
                                Integer.parseInt(parts[3]);

                        String author = parts[4];

                        boolean issued =
                                Boolean.parseBoolean(parts[5]);

                        String memberId = parts[6];

                        String memberEmail = parts[7];

                        int dueDate =
                                Integer.parseInt(parts[8]);


                        Book book =
                                new Book(
                                        title,
                                        totalCopies,
                                        author
                                );

                        // Restore previous state
                        book.setAvailableCopies(
                                availableCopies
                        );

                        book.setIssued(issued);

                        book.setMemberId(memberId);

                        book.setMemberEmail(memberEmail);

                        book.setDueDate(dueDate);

                        items.add(book);
                    }


                    // =========================
                    // LOAD MAGAZINE
                    // =========================
                    else if (parts[0].equalsIgnoreCase("MAGAZINE")) {

                        if (parts.length < 9) {
                            System.out.println(
                                    "Invalid MAGAZINE record skipped."
                            );
                            continue;
                        }

                        String title = parts[1];

                        int totalCopies =
                                Integer.parseInt(parts[2]);

                        int availableCopies =
                                Integer.parseInt(parts[3]);

                        String publisher = parts[4];

                        boolean issued =
                                Boolean.parseBoolean(parts[5]);

                        String memberId = parts[6];

                        String memberEmail = parts[7];

                        int dueDate =
                                Integer.parseInt(parts[8]);


                        Magazine magazine =
                                new Magazine(
                                        title,
                                        totalCopies,
                                        publisher
                                );

                        // Restore previous state
                        magazine.setAvailableCopies(
                                availableCopies
                        );

                        magazine.setIssued(issued);

                        magazine.setMemberId(memberId);

                        magazine.setMemberEmail(memberEmail);

                        magazine.setDueDate(dueDate);

                        items.add(magazine);
                    }

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Invalid number found in file. "
                                    + "Record skipped: " + line
                    );
                }
            }

            System.out.println(
                    "Library data loaded successfully."
            );

        } catch (IOException e) {

            System.out.println(
                    "File loading error: "
                            + e.getMessage()
            );
        }

        return items;
    }


    // =========================
    // CLEAN DATA BEFORE SAVING
    // =========================
    private static String clean(String value) {

        if (value == null) {
            return "N/A";
        }

        // Comma is our file separator,
        // so replace it with a space.
        return value
                .replace(",", " ")
                .replace("\n", " ")
                .replace("\r", " ")
                .trim();
    }
}