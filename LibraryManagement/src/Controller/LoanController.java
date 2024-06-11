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
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                viewLoans();
                break;
            case 4:
                return;
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
    
    public void borrowBook() {
        Loan loan = new Loan(); 
        if (loanList.addLoan(loan)) {
            System.out.println("Book borrowed successfully!");
        }else {
            System.out.println("Error: Failed to borrow book. Please try again.");
        }
    }

    public void returnBook() {
        int transactionID = Helper.getInt("Enter Loan Transaction ID to return");
        LocalDate returnDate = Helper.getLocalDate(""); 

        if (loanList.updateLoan(loan)) {
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Error: Loan not found or already returned!");
        }
    }

    public void viewLoans() {
        Map<Integer, Loan> loans = loanList.getAllLoans();
        if (loans.isEmpty()) {
            System.out.println("No loans found!");
        } else {
            System.out.println("**Loan Information**");
            loans.values().forEach((loan) -> {
                System.out.println(loan); // Use Loan class toString() for formatted output
            });
        }
    }
    
}