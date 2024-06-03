package Controller;

import Model.BookList;

public class Main {
    public static void main(String[] args) {
        BookList bookList = new BookList();
        new BookController(bookList).run();
        
    }
}
