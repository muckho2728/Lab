package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class LoanList {

    private static final String FILE_NAME = "loans.dat";
    private final HashMap<Integer, Loan> loans;  // HashMap for efficient loan storage

    public LoanList() {
        loans = (HashMap<Integer, Loan>) readLoansFromFile();
    }

    private Map<Integer, Loan> readLoansFromFile() {
        Map<Integer, Loan> loanMap = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return loanMap;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null ) {
                if (!line.isEmpty()) {
                    Loan borrow = parseLoanFromFile(line);
                    if (borrow != null ) {
                        loanMap.put(borrow.getBookID(), borrow);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error reading file: " + FILE_NAME);
        }
        return loanMap;
    }
        
    private Loan parseLoanFromFile (String line) {
        String[] data = line.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            int transactionID = Integer.parseInt(data[0]);
            int bookID = Integer.parseInt(data[1]);
            int usersID = Integer.parseInt(data[2]);
            LocalDate borrowDate = LocalDate.parse(data[3], formatter);
            LocalDate returnDate = LocalDate.parse(data[4], formatter);
            return new Loan(transactionID, bookID, usersID, borrowDate, returnDate);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + line);
            return null; // Handle invalid data gracefully
        }
    }
    
    private boolean saveLoanToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Loan loan : loans.values()) {
                bw.write(loan.toString() + "\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to file: " + FILE_NAME);
            return false;
        }
    }
    // Add a loan (consider checking for existing borrow for the same book)
    public boolean addLoan(Loan loan) {
        if (!loans.containsKey(loan.getTransactionID())) {
            loans.put(loan.getTransactionID(), loan);
            return true;
        } else {
            System.out.println("Error: Loan transaction ID already exists!");
            return false;
        }
    }

    // Update loan information (handle potential loan not found)
    public boolean updateLoan(int transactionID, LocalDate returnDate) {
        if (loans.containsKey(transactionID)) {
            Loan loan = loans.get(transactionID);
            loan.setReturnDate(returnDate);
            return true;
        } else {
            System.out.println("Error: Loan not found!");
            return false;
        }
    }

    
    // Get all loans (or filter based on criteria)
    public Map<Integer, Loan> getAllLoans() {
        return loans; // Return the entire HashMap (modify if needed)
    }
}
