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
          "1.Book menu",
          "2. User menu",
          "3. Loan menu",
          "4. Exit!"
        }) {
            @Override
            public void execute(int n) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        while (true) {
//          mainMenu.display();
          int choice = mainMenu.getSelected();

          switch (choice) {
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
            default:
              System.out.println("Invalid Choice!");
          }
        }
      }
}