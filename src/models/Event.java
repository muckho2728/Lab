package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private int eventID;
    private String eventName;
    private String eventLocation;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;
    private int eventAttendence;
    private boolean status;
    private byte deleteFlag;


    public Event() {
    }
    public Event(int eventID, String eventName, String eventLocation, LocalDate dateOfStart, LocalDate dateOfEnd, int eventAttendence, boolean status) {
            this.eventID = eventID;
            this.eventName = eventName;
            this.eventLocation = eventLocation;
            this.dateOfStart = dateOfStart;
            this.dateOfEnd = dateOfEnd;
            this.eventAttendence = eventAttendence;
            this.status = status;
        }

    public Event(int eventID, String eventName, String eventLocation, LocalDate dateOfStart, LocalDate dateOfEnd, int eventAttendence, boolean status, byte deleteFlag) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.eventAttendence = eventAttendence;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }
    
    public Event( String eventName, String eventLocation, LocalDate dateOfStart, LocalDate dateOfEnd, int eventAttendence, boolean status) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.eventAttendence = eventAttendence;
        this.status = status;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDate dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

    public int getEventAttendence() {
        return eventAttendence;
    }

    public void setEventAttendence(int eventAttendence) {
        this.eventAttendence = eventAttendence;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    private String statusToString(boolean status){
        String result = status ? "Available" : "Not Available";
        return result;
    }
    
    private String formartDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String result = date.format(formatter);
        return result;
    }
    
    public String convertToLine(){
        return eventID + " : " + eventName + " : " + eventLocation + " : " +
               formartDate(dateOfStart) + " : " + formartDate(dateOfEnd) + " : " +
               eventAttendence + " : " + 
                status + " : " + deleteFlag;
//        "1 : j : l : 2023/04/05 : 2023/05/04 : 23 : true : 1"
    }


//    @Override
//    public String toString() {
//        return "Event{" + "eventID=" + eventID + ", eventName=" 
//                + eventName + ", eventLocation=" 
//                + eventLocation + ", dateOfStart=" 
//                + dateOfStart + ", dateOfEnd=" 
//                + dateOfEnd + ", eventAttendence=" 
//                + eventAttendence + ", status=" 
//                + statusToString(status) + '}';

//          return eventID + eventName + eventLocation + 
//                 dateOfStart + dateOfEnd + eventAttendence + statusToString(status);
 //   }
    @Override
public String toString() {
    return String.format("Event{" +
            "ID=%-8d, NAME='%-14s', LOCATION='%-14s', DATE OF START='%-16s', DATE OF END='%-16s', ATTENDEES=%-17d, STATUS='%-16s'}",
            eventID, eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, statusToString(status));
}

}