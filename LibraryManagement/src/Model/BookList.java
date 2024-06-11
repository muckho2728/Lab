package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BookList {

//    private static final String FILE_NAME = "books.dat";
    private final Map<Integer, Book> books;
    private String fileName;

    Map<Integer, Book> bookMap = new HashMap<>();
    
    public BookList() {
        books = readBooksFromFile();
    }
    
//------------------------------------------------------------------------------
    // Read books from file
    private Map<Integer, Book> readBooksFromFile() {
        Map<Integer, Book> books = new HashMap<>();
        File file = new File("books.dat");
        String fileName = file.getAbsolutePath();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line ;
                while ((line = br.readLine()) != null){
                    Book book =  parseBookFromLine(line);
                    if (book != null) { // Check for valid book data before adding
                        books.put(book.getBookID(), book); // Use book ID as key
                    } else {
                        System.err.println("Error parsing book data: " + line); // Log invalid data
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
        }

        return books;
    }
    // Parse book data from a line
    private Book parseBookFromLine(String line) {
        String[] data = line.split(" : "); 
        try {
            int bookID = Integer.parseInt(data[0]);
            String title = data[1];
            String author = data[2];
            int publicationYear = Integer.parseInt(data[3]);
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
        int maxID = 0;
        byte delFlag = 0;
        for (Book b : books.values()) {
            int currentID = b.getBookID();
            if (currentID > maxID) {
                maxID = currentID;
            }
        }
        book.setBookID(maxID + 1);
        book.setDeleteFlag(delFlag);
        books.put(book.getBookID() + 1, book); // Assuming bookID is the key
        saveBookToFile();
        return true;
    }

    // Update a book (assuming book ID remains the same)
    public boolean updateBook(Book book) {
        for (Book b : books.values()) {
            if(b.getBookID() == book.getBookID() && b.getDeleteFlag()== 0) {
                b.setBookTitle(book.getBookTitle());
                b.setAuthor(book.getAuthor());
                b.setPublicationYear(book.getPublicationYear());
                b.setPublisher(book.getPublisher());
                b.setIsbn(book.getIsbn());
                b.setActiveBook(book.isActiveBook());
                saveBookToFile();
                return true;
            }
        } return false;
    }
    // Get a book by ID (assuming a unique book ID exists)
//    public Map<Integer, Book> getBookToDisplay(){
//        Map<Integer, Book> result = new HashMap<>();
//        books.values().stream().filter((b) -> (b.getDeleteFlag()==0)).forEachOrdered((Book b) -> {
//            result.put(Integer.SIZE, b);
//        });
//        return result;
//    }
    public Map<Integer, Book> getBookToDisplay() {
        Map<Integer, Book> result = new HashMap<>();
        books.values().stream().filter(b -> b.getDeleteFlag() == 0) // Filter based on deleteFlag
            .forEachOrdered(b -> result.put(b.getBookID(), b));
        return result;
}
    
    public Book getBookByID(int bookID) {
        for (Book b : books.values()) {
            if (bookID == b.getBookID() 
                    && b.getDeleteFlag() == 0) {
                return b;
            }
        }
        return null;
    }
    
    // Delete a book by ID
    public boolean deleteBook(int bookID) {
        Book gBook = getBookByID(bookID);
        if(gBook == null) return false;
        byte dl = 1;
        // check availible getByID
        gBook.setDeleteFlag(dl);
        saveBookToFile();
        return true;
        }

    public Map<Integer, Book> getBookList(){
        return new HashMap<>(books);
    }
    
    public void displayBookList(Map<Integer, Book> booksList){
        if(booksList.isEmpty()) {
            System.out.println("List is empty!");
        }else{
            booksList.values().forEach((b) -> {
                System.out.println(b);
            });
        }
    }
    
    public Map<Integer, Book> searchBook(Map<Integer, Book> booksList, Predicate<Book> s) {
        Map<Integer, Book> result = new HashMap<>();
        books.values().stream().filter((book) -> (s.test(book) && book.getDeleteFlag() == 0)).forEachOrdered((book) -> {
            result.put(Integer.SIZE, book);
        });
        return result;
    }

//    public Book getBook(int bookID){
//        Book book = new Book();
//        int size = books.size();
//        for(int i = 0; i<size; i++) {
//            if(bookID == books.get(i).getBookID()){
//                book = books.get(i);
//            }
//        }return book;
//    }
    public Book getBook(int bookID) {
        // Check for empty collection (optional)
        if (books.isEmpty()) {
          return null;
        }

        for (int i = 0; i < books.size(); i++) {
          Book book = books.get(i);
          if (book != null && bookID == book.getBookID()) {
            return book;
          }
        }
        return null; // Book not found
}
    
    public boolean isActiveBook (int ID){
        return books.values().stream().anyMatch((book) -> (book.getBookID()==ID));
    }
            
    public void saveBookToFile() {
        try(FileWriter fileWriter = new FileWriter("books.dat")) {
            for (Book book : books.values()) {
                fileWriter.write(book.convertToLine());
                fileWriter.write(System.lineSeparator());
            }
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}