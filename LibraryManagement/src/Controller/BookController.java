package Controller;

import Model.Book;
import Model.BookList;
import View.Helper;
import View.Menu;
import java.util.Map;

public class BookController extends Menu {
    
    static String[] mainMenu = {
                "Add a new Book",
                "Update Book Information",
                "Delete Book",
                "Show All Books",
                "Others - Quit"};
    
    public BookController(BookList bookList) {
        super("Book Management", mainMenu);
        BookController.bookList = bookList;
    }

//    public BookController(BookList bookList) {
//        super("Book Management", mainMenu);
//        BookController.bookList = bookList;
//    }
    private static BookList bookList;
    private static Book book;
 

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
                doDeleteBook();
                break;
            case 4:
//                showAllBooks();
                break;
            case 5:
                break;
        }
    }
    @Override
    public void run() {
        while (true) {
            int choice = getSelected();

            if (choice == 5) { // Exit condition for Book Management menu
                break;
            }
            execute(choice);
        }
    }
//------------------------------------------------------------------------------
    
    private void createNewBook() {
        
            String bookTitle = Helper.getString("Enter Book Name");
            String author = Helper.getString("Enter Book Author");
            int publicationYear = Helper.getInt("Enter Book Publication Year");
            String publisher = Helper.getString("Enter Book Publisher");
            boolean activeBook = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
            book = new Book(bookTitle, author, publicationYear, publisher, activeBook);
            boolean result = bookList.addBook(book);
            if(result){
                System.out.println("Create success!");
            }else{
                System.out.println("Create fail!");
            }
        }
    
    private void checkActiveBook(){
        int ID = Helper.getInt("Enter ID event");
        if (bookList.isActiveBook(ID)){
            System.out.println("Exist Book");
        }else{
            System.out.println("No Book Found!");
        }
    }
    
    private void searchByBookTitle(){
        String bookTitle = Helper.getString("Enter Book Title");
        Map<Integer, Book> kq = bookList.searchBook(bookList.getBookList(),
                s -> s.getBookTitle().equalsIgnoreCase(bookTitle));
        if (kq.isEmpty())
            System.out.println("Not Found !!!");
        else{
            bookList.displayBookList(kq);
        }
    }
//------------------------------------------------------------------------------
    
//    private void updateBookInformation() {
//            displayBookList(bookList.getBookToDisplay());
//            int id = Helper.getInt("Enter Book ID you want to update");
//            Book existBook = bookList.getBook(id);
//            if(existBook == null ){
//                System.out.println("Book Not found!");
//                return;
//            }
//            String bookTitle = Helper.getString("Enter Book Name");
//            String author = Helper.getString("Enter Book Author");
//            int publicationYear = Helper.getInt("Enter Book Publication Year");
//            String publisher = Helper.getString("Enter Book Publisher");
//            boolean activeBook = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
//            Book book = new Book(id, bookTitle, author, publicationYear, publisher, id, activeBook);
//            boolean result = bookList.updateBook(book);
//            if(result) {
//                System.out.println("Update success!");
//            }else{
//                System.out.println("Update fail!");
//            }
//        
//}

    private void updateBookInformation() {
        displayBookList(bookList.getBookToDisplay()); // Assuming it doesn't return a collection
        int id = Helper.getInt("Enter Book ID you want to update");
        Book existBook = bookList.getBook(id);
        if (existBook == null) {
            System.out.println("Book Not found!");
            return;
        }

        String bookTitle = Helper.getString("Enter Book Name");
        String author = Helper.getString("Enter Book Author");
        int publicationYear = Helper.getInt("Enter Book Publication Year");
        String publisher = Helper.getString("Enter Book Publisher");
        boolean activeBook = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");

        // Update the existing book object
        existBook.setBookTitle(bookTitle);
        existBook.setAuthor(author);
        existBook.setPublicationYear(publicationYear);
        existBook.setPublisher(publisher);
        existBook.setActiveBook(activeBook);

        boolean result = bookList.updateBook(existBook); // Pass the updated book

        if (result) {
            System.out.println("| BookID | Title   | Author  | Publication Year | Publisher | ISBN  | Active Book |");
            System.out.printf("| %-8d | %-14s | %-14s | %-16d | %-16s | %-17d | %-16s |\n",
                existBook.getBookID(), existBook.getBookTitle(), existBook.getAuthor(), existBook.getPublicationYear(),
                existBook.getPublisher(), existBook.getIsbn(), existBook.isActiveBook() ? "Yes" : "No");
        } else {
            System.out.println("Update fail!");
    }
    }
    
//------------------------------------------------------------------------------

    private void doDeleteBook() {
            displayBookList(bookList.getBookToDisplay());
            int bookID = Helper.getInt("Enter Book ID to delete");
            boolean result = bookList.deleteBook(bookID);

            if (result) {
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete fail!");
            }
    }
    
    public void displayBookList(Map<Integer, Book> bookList){
    if(bookList.isEmpty()) {
        System.out.println("List is empty!");
    }else{
        System.out.println("| BookID | Title      | Author   | Publication Year | Publisher  | ISBN   | Active Book  |");
        System.out.println("|---------|------------|------------|------------------|-------------|-----------|---------------|");
        bookList.forEach((Integer bookId, Book book) -> {
            System.out.printf("| %-8d | %-14s | %-14s | %-16d | %-16s | %-17d | %-16s |\n",
                bookId, book.getBookTitle(), book.getAuthor(), book.getPublicationYear(), 
                book.getPublisher(), book.getIsbn(), book.isActiveBook());
        });
    }
}
//    public void saveBookToFile(Map<Integer, Book> bookList) {
//        try (FileWriter fileWriter = new FileWriter("books.dat")) {
//            for (Book book : bookList) {
//                fileWriter.write(book.toString
//                fileWriter.write(System.lineSeparator());
//            }
//            System.out.println("Book saved to file");
//        } catch (Exception e) {
//            System.out.println("Error writing to file: " + e.getMessage());
//        }
//    }
   
}