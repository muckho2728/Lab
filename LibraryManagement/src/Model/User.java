package Model;

import java.time.LocalDate;

public class User {
        private int userID;
        private String name;
        private LocalDate dateOfBirth;
        private int phoneNumber;
        private String email;
        private boolean activeUser;

    public User() {
    }

    public User(int userID, String name, LocalDate dateOfBirth, int phoneNumber, String email, boolean activeUser) {
        this.userID = userID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
    }

    public User(String name, LocalDate dateOfBirth, int phoneNumber, String email, boolean activeUser) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
    }

    User(String userId, String userName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActiveUser() {
        return activeUser;
    }

    public void setActiveUser(boolean activeUser) {
        this.activeUser = activeUser;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID +
                ", name=" + name +
                ", dateOfBirth=" + dateOfBirth +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", activeUser=" + activeUser + '}';
    }

    void setPhoneNumber(String phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
}