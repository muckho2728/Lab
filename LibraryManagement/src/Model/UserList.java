package Model;
import java.util.HashMap;
import java.util.Map;

public class UserList {

    private static final String FILE_NAME = "Users.dat";
    private final Map<Integer, User> users;

    public UserList() {
        users = new HashMap<>();
    }

    private Map<Integer, User> readUsersFromFile () {
        Map<Integer, User> user
    }
    // Add a user (check for duplicate ID)
    public boolean addUser(User user) {
        if (!users.containsKey(user.getUserID())) {
            users.put(user.getUserID(), user);
            return true;
        } else {
            System.out.println("Error: User ID already exists!");
            return false;
        }
    }

    // Update user information (handle potential user not found)
    public boolean updateUser(int userID, String name, String email, String phone) {
        if (users.containsKey(userID)) {
            User user = users.get(userID);
            if (name != null && !name.isEmpty()) {
                user.setName(name);
            }
            if (email != null && !email.isEmpty()) {
                user.setEmail(email);
            }
            if (phone != null && !phone.isEmpty()) {
                user.setPhoneNumber(phone);
            }
            return true;
        } else {
            System.out.println("Error: User not found!");
            return false;
        }
    }

    // Delete a user (deactivate user instead)
    public boolean deleteUser(int userID) {
        if (users.containsKey(userID)) {
            User user = users.get(userID);
            user.setActiveUser(false); // Update active status
            return true;
        } else {
            System.out.println("Error: User not found!");
            return false;
        }
    }

    // Get active users
    public Map<Integer, User> getActiveUsers() {
        Map<Integer, User> activeUsers = new HashMap<>();
        users.values().stream().filter((user) -> (user.isActiveUser())).forEachOrdered((user) -> {
            activeUsers.put(user.getUserID(), user);
        });
        return activeUsers;
    }

  
}

