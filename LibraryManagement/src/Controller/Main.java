package Controller;

import Model.BookList;
import Model.LoanList;
import Model.UserList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();
        
        LoanList loanList = new LoanList();
        
        UserList userList = new UserList();
    while(true) {   
        System.out.println("------Library Management--------");
        System.out.println("1. Book menu");
        System.out.println("2. User menu");
        System.out.println("3. Loan menu");
        System.out.println("4. Exit!");
        System.out.println("Your option!");
        
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();
        switch(choice) {
            case 1: 
                new BookController(bookList).run();
                break;
            case 2: 
                new UserController(userList).run();
                break;
            case 3: 
                new LoanController(loanList).run();
                break;
            case 4: 
                return;
        }
        System.out.println();
        }  
    } 
}
