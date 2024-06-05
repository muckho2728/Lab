package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class BookList {

    private static final String FILE_NAME = "books.dat";
    private final Map<Integer, Book> books;

    public BookList() {
        books = readBooksFromFile();
    }

    // Read books from file
    private Map<Integer, Book> readBooksFromFile() {
        Map<Integer, Book> bookMap = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return bookMap; 
        }

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    Book book = parseBookFromLine(line);
                    if (book != null) {
                        bookMap.put(book.getBookID(), book); // Use book ID as key
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error reading file: " + FILE_NAME);
        }

        return bookMap;
    }

    // Parse book data from a line
    private Book parseBookFromLine(String line) {
        String[] data = line.split(" : "); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            int bookID = Integer.parseInt(data[0]);
            String title = data[1];
            String author = data[2];
            LocalDate publicationYear = LocalDate.parse(data[3], formatter);
            String publisher = data[4];
            double isbn = Double.parseDouble(data[5]);
            boolean isActive = Boolean.parseBoolean(data[6]);
            return new Book(bookID, title, author, publicationYear, publisher, isbn, isActive);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + line);
            return null; // Handle invalid data gracefully
        }
    }

    // Add a book (consider ID generation strategy later)
    public boolean addBook(Book book) {
        books.put(book.getBookID(), book); // Use book ID as key
        return saveBooksToFile();
    }

    // Save books to file
    private boolean saveBooksToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books.values()) {
                bw.write(book.toString() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + FILE_NAME);
            return false;
        }
    }

    // Get a book by ID (assuming a unique book ID exists)
    public Book getBookById(int bookId) {
        return books.get(bookId);
    }

    // Update a book (assuming book ID remains the same)
    public boolean updateBook(Book book) {
        if (books.containsKey(book.getBookID())) {
            books.put(book.getBookID(), book); // Update the book with its existing ID as key
            return saveBooksToFile();
        } else {
            System.err.println("Book not found for update!");
            return false;
        }
    }

    // Delete a book by ID
    public boolean deleteBook(int bookId) {
        if (books.containsKey(bookId)) {
            books.remove(bookId);
            return saveBooksToFile();
        } else {
            System.err.println("Book not found for deletion!");
            return false;
        }
    }

   
}
