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
import java.util.function.Predicate;

public class LoanList {

    private String fileName;
    private final Map<Integer, Loan> loans;  // HashMap for efficient loan 
    
    Map<Integer, Loan> loanMap = new HashMap<>();
    
    public LoanList() {
        loans =  readLoansFromFile();
    }

    private Map<Integer, Loan> readLoansFromFile() {
        Map<Integer, Loan> loans = new HashMap<>();
        File file = new File("loans.dat");
        String fileName = file.getAbsolutePath();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null ) {
                if (!line.isEmpty()) {
                    Loan borrow = parseLoanFromFile(line);
                    if (borrow != null && borrow.getBorrowDate() != null && borrow.getReturnDate() != null ) {
                        loans.put(borrow.getBookID(), borrow);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
        }
        return loans;
    }
        
    private Loan parseLoanFromFile (String line) {
        String[] data = line.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            int transactionID = Integer.parseInt(data[0]);
            int bookID = Integer.parseInt(data[1]);
            int usersID = Integer.parseInt(data[2]);
            // Handle potential null values for borrow and return dates
            LocalDate borrowDate = null;
            if (!data[3].equals("null")) {
                borrowDate = LocalDate.parse(data[3], formatter);
            }

            LocalDate returnDate = null;
            if (!data[4].equals("null")) {
                returnDate = LocalDate.parse(data[4], formatter);
            }
            return new Loan(transactionID, bookID, usersID, borrowDate, returnDate);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + line);
            return null; // Handle invalid data gracefully
        }
    }
    
    private void saveLoanToFile() {
        try (FileWriter fileWriter = new FileWriter("loans.dat")) {
            for (Loan loan : loans.values()) {
                fileWriter.write(loan.convertToLine());
                fileWriter.write(System.lineSeparator());
            }
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    
    // Add a loan (consider checking for existing borrow for the same book)
    public boolean addLoan(Loan loan) {
        int maxID = 0;
        byte delFlag = 0;
        for (Loan l : loans.values()) {
            int currentID = l.getTransactionID();
            if (currentID > maxID) {
                maxID = currentID;
            }
        }
        loan.setTransactionID(maxID + 1);
//        loan.setDeleteFlag(delFlag);
        loans.put(loan.getTransactionID()+ 1, loan); // Assuming bookID is the key
        saveLoanToFile();
        return true;
    }

    // Update loan information (handle potential loan not found)
    public boolean updateLoan(Loan loan) {
       for (Loan l : loans.values()) {
            if(l.getTransactionID()== loan.getTransactionID()) {
                l.setBookID(loan.getBookID());
                l.setBorrowDate(loan.getBorrowDate());
                l.setReturnDate(loan.getReturnDate());
                l.setUserID(loan.getUserID());
                
                saveLoanToFile();
                return true;
            }
        } return false;
    }

    
    // Get all loans (or filter based on criteria)
    public Map<Integer, Loan> getAllLoans() {
        return new HashMap<>(loans); // Return the entire HashMap (modify if needed)
        
}
    
    public void displayBookList(Map<Integer, Loan> loanList){
     if(loanList.isEmpty()) {
         System.out.println("List is empty!");
     }else{
         loanList.values().forEach((l) -> {
             System.out.println(l);
         });
     }
    }
    
    public Map<Integer, Loan> searchLoan(Map<Integer, Loan> loanList, Predicate<Loan> s) {
        Map<Integer, Loan> result = new HashMap<>();
        loans.values().stream().filter((loan) -> (s.test(loan))).forEachOrdered((loan) -> {
            result.put(Integer.SIZE, loan);
        });
        return result;
    }

    public Loan getLoan (int transactionID){
        Loan loan = new Loan();
        int size = loans.size();
        for(int i = 0; i<size; i++) {
            if(transactionID == loans.get(i).getTransactionID()){
                loan = loans.get(i);
            }
        }return loan;
    }
    
    public boolean isActiveLoan (int ID){
        return loans.values().stream().anyMatch((loan) -> (loan.getTransactionID()==ID));
    }
}