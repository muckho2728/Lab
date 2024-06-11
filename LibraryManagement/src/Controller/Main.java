package Controller;

import Model.BookList;
import Model.LoanList;
import Model.UserList;
import View.Menu;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();
        LoanList loanList = new LoanList();
        UserList userList = new UserList();

        Menu mainMenu = new Menu("Library Management", new String[]{
          "Book menu",
          "User menu",
          "Loan menu",
          "Exit!" }) {
            @Override
            public void execute(int n) {
                switch (n) {
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
              System.exit(0);
          }
            }
        }; 
        mainMenu.run();
    }
}