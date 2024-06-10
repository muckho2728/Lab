package Model;
import java.io.BufferedReader;
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

public class UserList {

    private String fileName;
    private final Map<Integer, User> users;

    public UserList() {
        users = readUsersFromFile();
    }

    private Map<Integer, User> readUsersFromFile () {
        Map<Integer, User> userMap = new HashMap<>();
        File file = new File("users.dat");
        String fileName = file.getAbsolutePath();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line ;
                while ((line = br.readLine()) != null){
                    User user =  parseUserFromFile(line);
                    if (user != null) { // Check for valid book data before adding
                        users.put(user.getUserID(), user); // Use book ID as key
                    } else {
                        System.err.println("Error parsing book data: " + line); // Log invalid data
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error opening file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + fileName);
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
        int maxID = 0;
        byte delFlag = 0;
        for (User u : users.values()) {
            int currentID = u.getUserID();
            if (currentID > maxID) {
                maxID = currentID;
            }
        }
        user.setUserID(maxID + 1);
        user.setDeleteFlag(delFlag);
        users.put(user.getUserID() + 1, user); // Assuming bookID is the key
        saveUserToFile();
        return true;
    }

    // Update user information (handle potential user not found)
    public boolean updateUser(User user) {
        for (User u : users.values()) {
            if(u.getUserID() == user.getUserID() && u.getDeleteFlag()== 0) {
                u.setName(user.getName());
                u.setDateOfBirth(user.getDateOfBirth());
                u.setPhoneNumber(user.getPhoneNumber());
                u.setEmail(user.getEmail());
                u.setActiveUser(user.isActiveUser());
                saveUserToFile();
                return true;
            }
        } return false;
    }

    public Map<Integer, User> getUserToDisplay(){
        Map<Integer, User> result = new HashMap<>();
        users.values().stream().filter((b) -> (b.getDeleteFlag()==0)).forEachOrdered((User b) -> {
            result.put(Integer.SIZE, b);
        });
        return result;
    }
    
    public User getUserByID(int userID) {
        for (User b : users.values()) {
            if (userID == b.getUserID()
                    && b.getDeleteFlag() == 0) {
                return b;
            }
        }
        return null;
    }
    
    // Delete a user (deactivate user instead)
    public boolean deleteUser(int userID) {
        User user = getUserByID(userID);
        if(user == null) return false;
        byte dl = 1;
        // check availible getByID
        user.setDeleteFlag(dl);
        saveUserToFile();
        return true;
        }

 public Map<Integer, User> getUserList(){
        return new HashMap<>(users);
    }
    
    // Get active users
    public void displayUserList(Map<Integer, User> userList){
        if(userList.isEmpty()) {
            System.out.println("List is empty!");
        }else{
            userList.values().forEach((b) -> {
                System.out.println(b);
            });
        }
    }
    
    public Map<Integer, User> searchUser(Map<Integer, User> userList, Predicate<User> s) {
        Map<Integer, User> result = new HashMap<>();
        users.values().stream().filter((user) -> (s.test(user) && user.getDeleteFlag() == 0)).forEachOrdered((user) -> {
            result.put(Integer.SIZE, user);
        });
        return result;
    }

    public User getUser(int userID){
        User user = new User();
        int size = users.size();
        for(int i = 0; i<size; i++) {
            if(userID == users.get(i).getUserID()){
                user = users.get(i);
            }
        }return user;
    }
    
    public boolean isActiveUser (int ID){
        return users.values().stream().anyMatch((user) -> (user.getUserID()==ID));
    }
            
    public void saveUserToFile() {
        try(FileWriter fileWriter = new FileWriter("users.dat")) {
            for (User user : users.values()) {
                fileWriter.write(user.convertToLine());
                fileWriter.write(System.lineSeparator());
            }
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    
  
}