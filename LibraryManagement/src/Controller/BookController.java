package Controller;

import Model.Book;
import Model.BookList;
import View.Helper;
import View.Menu;
import java.time.LocalDate;

public class BookController extends Menu {

    private static BookList bookList;
    private static Book book;
    
    public BookController(BookList bookRepository) {
        super("Book Management", new String[]{
                "Add a new Book",
                "Update Book Information",
                "Delete Book",
                "Show All Books",
                "Others - Quit"});
        BookController.bookList = bookRepository;
    }

    BookController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                createNewBook();
                break;
            case 2:
                updateBookInformation();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
//                showAllBooks();
                break;
            case 5:
                return;
        }
    }

    private void createNewBook() {
        String bookTitle = Helper.getString("Enter Book Name");
        String author = Helper.getString("Enter Book Author");
        LocalDate publicationYear = Helper.getLocalDate("Enter Book Publication Year");
        String publisher = Helper.getString("Enter Book Publisher");
        boolean activeBook = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
        Book newBook = new Book(bookTitle, author, publicationYear, publisher, activeBook);

        boolean result = bookList.addBook(newBook);
        if (result) {
            System.out.println("Create success!");
        } else {
            System.out.println("Create failed!");
        }
    }

    private void updateBookInformation() {
    int bookId = Helper.getInt("Enter Book ID to update");
    Book bookToUpdate = bookList.getBookById(bookId);

    if (bookToUpdate != null) {
        // Allow user to update specific book attributes
        String newTitle = Helper.getString("Enter new title (or leave blank to keep existing)");
        String newAuthor = Helper.getString("Enter new author (or leave blank to keep existing)");
        LocalDate newPublicationYear = Helper.getOptionalLocalDate("Enter new publication year (or press Enter to keep existing)");
        String newPublisher = Helper.getString("Enter new publisher (or leave blank to keep existing)");
        boolean newActiveStatus = Helper.getStatusOptional("Enter new status (or press Enter to keep existing)"); // Handle optional boolean input

        // Update attributes only if new values are provided
        bookToUpdate.setBookTitle(newTitle.isEmpty() ? bookToUpdate.getBookTitle(): newTitle);
        bookToUpdate.setAuthor(newAuthor.isEmpty() ? bookToUpdate.getAuthor() : newAuthor);
        bookToUpdate.setPublicationYear(newPublicationYear != null ? newPublicationYear : bookToUpdate.getPublicationYear());
        bookToUpdate.setPublisher(newPublisher.isEmpty() ? bookToUpdate.getPublisher() : newPublisher);
        bookToUpdate.setActiveBook(newActiveStatus != false ? newActiveStatus : bookToUpdate.isActiveBook());

        boolean updateResult = bookList.updateBook(bookToUpdate);
        if (updateResult) {
            System.out.println("Book updated successfully!");
        } else {
            System.out.println("Failed to update book!");
        }
    } else {
        System.out.println("Book not found!");
    }
}


    private void deleteBook() {
        int bookId = Helper.getInt("Enter Book ID to delete");
        Book bookToDelete = bookList.getBookById(bookId);

        if (bookToDelete != null) {
            boolean confirmation = Helper.getConfirmation("Are you sure you want to delete this book?");
            if (confirmation) {
                boolean deleteResult = bookList.deleteBook(bookId);
                if (deleteResult) {
                    System.out.println("Book deleted successfully!");
                } else {
                    System.out.println("Failed to delete book!");
                }
            } else {
                System.out.println("Book deletion cancelled.");
            }
        } else {
            System.out.println("Book not found!");
        }
    }
/*private int bookID;
        private String bookTitle;
        private String author;
        private LocalDate publicationYear;
        private String publisher;
        private double isbn;
        private boolean activeBook;*/
//    public void displayEventList(BookList bookList) {
//    if (bookList.isEmpty()) {
//        System.out.println("List is empty!");
//    } else {
//        System.out.println("| BookID   | Title           | Author         | Publication Year  | Publisher      | ISBN            | Active Book      |");
//        System.out.println("|----------|-----------------|----------------|-------------------|----------------|-----------------|------------------|");
//
//        bookList.forEach((book) -> {
//            System.out.printf("| %-8d | %-14s | %-14s | %-16s | %-16s | %-17d | %-16s |\n",
//                    book.getBookID(), book.getBookTitle(), book.get(),
//                    event.getDateOfStart(), event.getDateOfEnd(), event.getEventAttendence(),
//                    event.getStatus());
//        });
//    }

//    private void showAllBooks() {
//                for (Book book : bookList.getAllBooks()) {
//              System.out.println(book); // Assuming Book class has a toString() method for displaying details
//            }
//            if (bookList.getAllBooks().isEmpty()) {
//              System.out.println("There are no books currently in the library.");
//            }
//          }
}