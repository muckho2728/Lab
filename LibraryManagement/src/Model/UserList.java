package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class UserList {

    private static final String FILE_NAME = "Users.dat";
    private final Map<Integer, User> users;

    public UserList() {
        users = new HashMap<>();
    }

    private Map<Integer, User> readUsersFromFile () {
        Map<Integer, User> userMap = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return userMap;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))){
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty()) {
                    User user = parseUserFromFile(line);
                    if (user != null ) {
                        userMap.put(user.getUserID(), user);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error reading file: " + FILE_NAME);
        }
        return userMap;
    }
    
    private User parseUserFromFile (String line) {
        String[] data = line.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            int userID = Integer.parseInt(data[0]);
            String name = data[1];
            LocalDate dateOfBirth = LocalDate.parse(data[2], formatter);
            int phoneNumber = Integer.parseInt(data[3]);
            String email = data[4];
            boolean activeUser = Boolean.parseBoolean(data[5]);      
            return new User(userID, name, dateOfBirth, phoneNumber, email, activeUser);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + line);
            return null; // Handle invalid data gracefully
        }
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

