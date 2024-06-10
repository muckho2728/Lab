package Controller;

import Model.User;
import Model.UserList;
import View.Helper;
import View.Menu;

public class UserController extends Menu {

    private final UserList userList;

    public UserController(UserList userList) {
        super("User Management Menu", new String[]{
                "Add User",
                "Update User Information",
                "Delete User",
                "Back to Main Menu"
        });
        this.userList = userList;
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                addUser();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                System.exit(0);
        }
    }

    public void run() {
        while (true) {
            int choice = getSelected();

            if (choice == 4) { // Exit condition for Book Management menu
                break;
            }
            execute(choice);
        }
    }
    
    private void addUser() {
        // Get user information from user
        User user = createUserFromInput();
        if (userList.addUser(user)) {
            System.out.println("User added successfully!");
        }
        askForContinue("Add Users");
    }

    private void updateUser() {
        int userID = Helper.getInt("Enter User ID to update");
        String name = Helper.getString("Enter new name (or leave blank to keep existing)");
        String email = Helper.getString("Enter new email (or leave blank to keep existing)");
        String phone = Helper.getString("Enter new phone number (or leave blank to keep existing)");
        if (userList.updateUser(userID, name, email, phone)) {
            System.out.println("User information updated successfully!");
        }
    }

    private void deleteUser() {
        int userID = Helper.getInt("Enter User ID to delete");
        if (userList.deleteUser(userID)) {
            System.out.println("User deleted successfully!");
        }
    }

    private User createUserFromInput() {
        return null;
    }
   

    private void askForContinue(String add_Users) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}