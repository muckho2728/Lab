package Controller;

import Model.User;
import Model.UserList;
import View.Helper;
import View.Menu;
import java.time.LocalDate;
import java.util.Map;

public class UserController extends Menu {

    static String[] mainMenu = {
                "Add User",
                "Update User Information",
                "Delete User",
                "Back to Main Menu"
        };
    
    private final UserList userList;
    private User user;

    public UserController(UserList userList) {
        super("User Management Menu", mainMenu);
        this.userList = userList;
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createNewUser();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                doDeleteUser();
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
    
    private void createNewUser() {
        
            String name = Helper.getString("Enter new name (or leave blank to keep existing)");
            String email = Helper.getString("Enter new email (or leave blank to keep existing)");
            int phoneNumber = Helper.getInt("Enter new phone number (or leave blank to keep existing)");
            boolean activeUser = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
            user = new User(name, LocalDate.MIN, phoneNumber, email, activeUser);
            boolean result = userList.addUser(user);
            if(result){
                System.out.println("Create success!");
            }else{
                System.out.println("Create fail!");
            }
        }
    
    private void checkActiveUser(){
        int ID = Helper.getInt("Enter ID event");
        if (userList.isActiveUser(ID)){
            System.out.println("Exist Book");
        }else{
            System.out.println("No Book Found!");
        }
    }
    
    private void searchByUserName(){
        String userName = Helper.getString("Enter User Name");
        Map<Integer, User> kq = userList.searchUser(userList.getUserList(),
                s -> s.getName().equalsIgnoreCase(userName));
        if (kq.isEmpty())
            System.out.println("Not Found !!!");
        else{
            userList.displayUserList(kq);
        }
    }

    private void updateUser() {
        int userID = Helper.getInt("Enter User ID to update");
        String name = Helper.getString("Enter new name (or leave blank to keep existing)");
        String email = Helper.getString("Enter new email (or leave blank to keep existing)");
        String phone = Helper.getString("Enter new phone number (or leave blank to keep existing)");
        if (userList.updateUser(user)) {
            System.out.println("User information updated successfully!");
        }
    }

    private void doDeleteUser() {
        displayUserList(userList.getUserToDisplay());
            int userID = Helper.getInt("Enter Book ID to delete");
            boolean result = userList.deleteUser(userID);

            if (result) {
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete fail!");
            }
    }
  
    public void displayUserList(Map<Integer, User> users){
        userList.displayUserList(userList.getUserList());
    }
}