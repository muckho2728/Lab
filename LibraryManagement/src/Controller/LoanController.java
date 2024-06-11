package Controller;

import Model.Loan;
import Model.LoanList;
import View.Helper;
import View.Menu;
import java.time.LocalDate;
import java.util.Map;

public class LoanController extends Menu {

    static String[] mainMenu = {
                "Borrow books",
                "Update borrowing information.",
                "Display all currently borrowed books",
                "Back to Main Menu"
        };
    
    private static LoanList loanList;
    private static Loan loan;

    public LoanController(LoanList loanList) {
        super("Loan Management Menu", mainMenu);
        LoanController.loanList = loanList;
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                doBorrowBook();
                break;
            case 2:
                updateInfomationLoan();
                break;
            case 3:
                displayLoanList(loanList.getLoanToDisplay());
                break;
            case 4:
                break;
        }
    }
    @Override
    public void run() {
            while (true) {
                int choice = getSelected();

                if (choice == 4) { // Exit condition for Book Management menu
                    break;
                }
                execute(choice);
            }
        }
//------------------------------------------------------------------------------
    private void doBorrowBook() {
        String[] menuBorrow = {"Borrow Book", "Return Book", "Exist"};
        new Menu("Choose your option", menuBorrow){
            @Override
            public void execute(int n) {
                switch(n) {
                    case 1:
                        borrowBook();
                        break;
                    case 2:
                        returnBook();
                        break;
                    case 3:
                        System.out.println("Exit search");
                        new LoanController(loanList).run();
                }
            }
        }.run();
    }
    
    public void borrowBook() {
        int bookID = Helper.getInt("Enter Book ID ");
        int userID = Helper.getInt("Enter User ID ");
        LocalDate borrowDate = Helper.getLocalDate("Enter Date To Borrow Book");
        LocalDate returnDate = Helper.getLocalDate("Enter Date To Return Book");
        boolean status = Helper.getStatus("Enter 1 - Late , 0 - Done");
        loan = new Loan(bookID, userID, borrowDate, returnDate, status); 
        boolean result = loanList.addLoan(loan);
            if(result){
                System.out.println("Create success!");
            }else{
                System.out.println("Create fail!");
            }
    }

    public void updateInfomationLoan() {
        displayLoanList(loanList.getLoanToDisplay()); // Assuming it doesn't return a collection
        int id = Helper.getInt("Enter Book ID you want to update");
        Loan existLoan = loanList.getLoan(id);
        if (existLoan == null) {
          System.out.println("Loan Not found!");
          return;
        }

        int bookID = Helper.getInt("Enter Book ID ");
        int userID = Helper.getInt("Enter User ID ");
        LocalDate borrowDate = Helper.getLocalDate("Enter Date To Borrow Book");
        LocalDate returnDate = Helper.getLocalDate("Enter Date To Return Book");
        boolean status = Helper.getStatus("Enter 1 - Late , 0 - Done");

        // Update properties of the existing book
        existLoan.setBookID(bookID);
        existLoan.setUserID(userID);
        existLoan.setBorrowDate(borrowDate);
        existLoan.setReturnDate(returnDate);
        existLoan.setStatus(status);

        boolean result = loanList.updateLoan(existLoan); // Pass the existing book

        if (result) {
          System.out.println("Update success!");
        } else {
          System.out.println("Update fail!");
        }
    }

    public void returnBook() {
        displayLoanList(loanList.getLoanToDisplay());
        int transactionID = Helper.getInt("Enter Transaction ID to return book ");
        boolean result = loanList.deleteLoan(transactionID);

            if (result) {
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete fail!");
            } 
    }
    
    
//    public void viewLoans() {
//        Map<Integer, Loan> loans = loanList.getAllLoans();
//        if (loans.isEmpty()) {
//            System.out.println("No loans found!");
//        } else {
//            System.out.println("**Loan Information**");
//            loans.values().forEach((loan) -> {
//                System.out.println(loan); // Use Loan class toString() for formatted output
//            });
//        }
//    }
    
    public void displayLoanList(Map<Integer, Loan> loanList){
    if(loanList.isEmpty()) {
        System.out.println("List is empty!");
    }else{
        System.out.printf("| %-8s | %-8s | %-8s | %-16s | %-16s | %-12s | %-16s |\n",
                "TransID", "BookID", "UserID", "BorrowDate", "ReturnDate", "Status", "Signal");
        System.out.println("|----------|----------|----------|------------------|------------------|--------------|------------------|");

        // Format each book entry with consistent spacing
        loanList.values().forEach((b) -> {
            System.out.printf("| %-8s | %-8s | %-8s | %-16s | %-16s | %-12s | %-16s |\n",
                    b.getTransactionID(), b.getBookID(), b.getUserID(), b.getBorrowDate(),
                    b.getReturnDate(), String.valueOf(b.isStatus()), b.getDeleteFlag());
        });
    }
}

    
}