package Model;

import java.time.LocalDate;

public class Book {
        private int bookID;
        private String bookTitle;
        private String author;
        private LocalDate publicationYear;
        private String publisher;
        private double isbn;
        private boolean activeBook;

    public Book() {
    }

    public Book(int bookID, String bookTitle, String author, LocalDate publicationYear, String publisher, double ISBN, boolean activeBook) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = ISBN;
        this.activeBook = activeBook;
    }

    public Book(String bookTitle, String author, LocalDate publicationYear, String publisher, double ISBN, boolean activeBook) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = ISBN;
        this.activeBook = activeBook;
    }

    public Book(String bookTitle, String author, LocalDate publicationYear, String publisher, boolean activeBook) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(LocalDate publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getISBN() {
        return isbn;
    }

    public void setISBN(double ISBN) {
        this.isbn = ISBN;
    }

    public boolean isActiveBook() {
        return activeBook;
    }

    public void setActiveBook(boolean activeBook) {
        this.activeBook = activeBook;
    }

    @Override
    public String toString() {
        return "Book{" + "bookID=" + bookID +
                ", bookTitle=" + bookTitle + 
                ", author=" + author + 
                ", publicationYear=" + publicationYear +
                ", publisher=" + publisher + 
                ", ISBN=" + isbn + 
                ", activeBook=" + activeBook + '}';
    }

    
}
