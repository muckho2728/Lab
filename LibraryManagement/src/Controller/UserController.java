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
        "Show All User",
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
                updateUserInformation();
                break;
            case 3:
                doDeleteUser();
                break;
            case 4:
                displayUserList(userList.getUserToDisplay());
                break;
            case 5:
                System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {
            int choice = getSelected();

            if (choice == 5) { // Exit condition for Book Management menu
                break;
            }
            execute(choice);
        }
    }

    private void createNewUser() {

        String name = Helper.getString("Enter User Name");
        LocalDate dateOfBirth = Helper.getLocalDate("Enter Date Of Birth User");
        String email = Helper.getString("Enter User Email");
        int phoneNumber = Helper.getInt("Enter User Phone Number");
        boolean activeUser = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
        user = new User(name, dateOfBirth, phoneNumber, email, activeUser);
        boolean result = userList.addUser(user);
        if (result) {
            System.out.println("Create success!");
        } else {
            System.out.println("Create fail!");
        }
    }

    private void checkActiveUser() {
        int ID = Helper.getInt("Enter ID User");
        if (userList.isActiveUser(ID)) {
            System.out.println("Exist User");
        } else {
            System.out.println("No User Found!");
        }
    }

    private void searchByUserName() {
        String userName = Helper.getString("Enter User Name");
        Map<Integer, User> kq = userList.searchUser(userList.getUserList(),
                s -> s.getName().equalsIgnoreCase(userName));
        if (kq.isEmpty()) {
            System.out.println("Not Found !!!");
        } else {
            userList.displayUserList(kq);
        }
    }

    private void updateUserInformation() {
        displayUserList(userList.getUserToDisplay());
        int userID = Helper.getInt("Enter User ID to update");
        User existUser = userList.getUser(userID);
        if (existUser == null) {
            System.out.println("Not Found !!!");
            return;
        }
        LocalDate dateOfBirth = Helper.getLocalDate("Enter your Date Of Birth");
        String name = Helper.getString("Enter new User Name");
        String email = Helper.getString("Enter new User Email");
        int phoneNumber = Helper.getInt("Enter new User Phone Number");
        boolean activeUser = Helper.getStatus("Enter 1 - Available : 0 - Not Available");
        
        existUser.setDateOfBirth(dateOfBirth);
        existUser.setName(name);
        existUser.setEmail(email);
        existUser.setPhoneNumber(phoneNumber);
        existUser.setActiveUser(activeUser);
        
        boolean result = userList.updateUser(existUser); // Pass the existing book

        if (result) {
          System.out.println("Update success!");
        } else {
          System.out.println("Update fail!");
        }

    }

    private void doDeleteUser() {
        displayUserList(userList.getUserToDisplay());
        int userID = Helper.getInt("Enter User ID to delete");
        boolean result = userList.deleteUser(userID);

        if (result) {
            System.out.println("Delete success!");
        } else {
            System.out.println("Delete fail!");
        }
    }

    public void displayUserList(Map<Integer, User> userList) {
        if (userList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.printf("| %-8s | %-20s | %-14s | %-16s | %-16s | %-12s |\n",
                    "UserID", "User Name", "Date Of Birth", "Email", "Phone Number", "Active Book");
            System.out.println("|----------|----------------------|----------------|------------------|------------------|--------------|");

            // Format each book entry with consistent spacing
            userList.values().forEach((b) -> {
                System.out.printf("| %-8s | %-20s | %-14s | %-16s | %-16s | %-12s |\n",
                        b.getUserID(), b.getName(), b.getDateOfBirth(), b.getEmail(),
                        b.getPhoneNumber(), String.valueOf(b.isActiveUser()));
            });
        }
    }
}
