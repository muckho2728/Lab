package Model;

import java.time.LocalDate;

public class Loan {
    
        private int transactionID;
        private int bookID;
        private int userID;
        private LocalDate borrowDate;
        private LocalDate returnDate;

    public Loan() {
    }

    public Loan(int transactionID, int bookID, int userID, LocalDate borrowDate, LocalDate returnDate) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Loan(int bookID, int userID, LocalDate borrowDate, LocalDate returnDate) {
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    Loan(String generateLoanId, User user, Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" + "transactionID=" + transactionID +
                ", bookID=" + bookID + ", userID=" + userID +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate + '}';
    }
        
        
}
