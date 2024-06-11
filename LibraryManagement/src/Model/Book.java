package Model;

public class Book {
        private int bookID;
        private String bookTitle;
        private String author;
        private int publicationYear;
        private String publisher;
        private String isbn;
        private boolean activeBook;
        private byte deleteFlag;

    public Book() {
    }

    public Book(int bookID, String bookTitle, String author, int publicationYear, String publisher, String isbn, boolean activeBook) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = isbn;
        this.activeBook = activeBook;
    }

    public Book(int bookID, String bookTitle, String author, int publicationYear, String publisher, String isbn, boolean activeBook, byte deleteFlag) {
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = isbn;
        this.activeBook = activeBook;
        this.deleteFlag = deleteFlag;
    }
    
    public Book(String bookTitle, String author, int publicationYear, String publisher, String isbn, boolean activeBook) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.isbn = isbn;
        this.activeBook = activeBook;
    }

    public Book(String bookTitle, String author, int publicationYear, String publisher, boolean activeBook) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.activeBook = activeBook;
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

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isActiveBook() {
        return activeBook;
    }

    public void setActiveBook(boolean activeBook) {
        this.activeBook = activeBook;
    }

    public byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    private String statusToString(boolean activeBook){
        String result = activeBook ? "Available" : "Not Available";
        return result;
    }
    
    public String convertToLine(){
        return bookID +
                " : " + bookTitle + 
                " : " + author + 
                " : " + publicationYear +
                " : " + publisher + 
                " : " + isbn + 
                " : " + activeBook +
                " : " + deleteFlag ;
    }
    
    @Override
    public String toString() {
            return String.format("Book{" +
                    "BookID=%-8d, Book Title='%-14s', Author='%-14s', Publication Year='%-16d', Publisher='%-16s', ISBN=%-17s, Active Book='%-16s'}",
                    bookID, bookTitle, author, publicationYear, publisher, isbn, statusToString(activeBook));

    }

}