package Model;

import java.time.LocalDate;

public class User {
        private int userID;
        private String name;
        private LocalDate dateOfBirth;
        private int phoneNumber;
        private String email;
        private boolean activeUser;
        private byte deleteFlag;

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

    public User(int userID, String name, LocalDate dateOfBirth, int phoneNumber, String email, boolean activeUser, byte deleteFlag) {
        this.userID = userID;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
        this.deleteFlag = deleteFlag;
    }
    
    public byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    public User(String name, LocalDate dateOfBirth, int phoneNumber, String email, boolean activeUser) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.activeUser = activeUser;
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

    private String statusToString(boolean activeUser){
        String result = activeUser ? "Available" : "Not Available";
        return result;
    }
    
    public String convertToLine(){
        return userID +
                " : " + name + 
                " : " + dateOfBirth + 
                " : " + phoneNumber +
                " : " + email + 
                " : " + activeUser + 
                " : " + deleteFlag ;
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
        
}