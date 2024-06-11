package Model;

import java.time.LocalDate;

public class Loan {
    
        private int transactionID;
        private int bookID;
        private int userID;
        private LocalDate borrowDate;
        private LocalDate returnDate;
        private boolean status;
        private byte deleteFlag;

    
    public Loan() {
    }

    public Loan(int transactionID, int bookID, int userID, LocalDate borrowDate, LocalDate returnDate, boolean status, byte deleteFlag) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }

    public Loan(int transactionID, int bookID, int userID, LocalDate borrowDate, LocalDate returnDate, boolean status) {
        this.transactionID = transactionID;
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }
    
    public Loan(int bookID, int userID, LocalDate borrowDate, LocalDate returnDate, boolean status) {
        this.bookID = bookID;
        this.userID = userID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    private String statusToString(boolean status){
        String result = status ? "Available" : "Not Available";
        return result;
    }
    
    public byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    public String convertToLine(){
        return   
                transactionID +
                " : " + bookID + 
                " : " + userID +
                " : " + borrowDate +
                " : " + returnDate +
                " : " + status;
    }
    
    @Override
    public String toString() {
        return "Borrow{" + "transactionID=" + transactionID +
                ", bookID=" + bookID + ", userID=" + userID +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", status=" + status +'}';
    }

    
}