package Controller;

import Model.Loan;
import Model.LoanList;
import View.Helper;
import View.Menu;
import java.time.LocalDate;
import java.util.Map;

public class LoanController extends Menu {

    private final LoanList loanList;

    public LoanController(LoanList loanList) { 
        this.loanList = loanList;
    }

    public void borrowBook() {
        Loan loan = new Loan(); 
        if (loanList.addLoan(loan)) {
            System.out.println("Book borrowed successfully!");
        }
    }

    public void returnBook() {
        int transactionID = Helper.getInt("Enter Loan Transaction ID to return");
        LocalDate returnDate = Helper.getLocalDate(""); 

        if (loanList.updateLoan(transactionID, returnDate)) {
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

    @Override
    public void execute(int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}