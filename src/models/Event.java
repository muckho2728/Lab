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
    private boolean deleteFlag;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

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
    
    public Event( String eventName, String eventLocation, LocalDate dateOfStart, LocalDate dateOfEnd, int eventAttendence, boolean status) {
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.dateOfStart = dateOfStart;
        this.dateOfEnd = dateOfEnd;
        this.eventAttendence = eventAttendence;
        this.status = status;
    }

    Event(int parseInt, String string, String string0, LocalDate localDate, boolean parseBoolean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Event(String eventName, LocalDate eventDate, String eventLocation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private String statusToString(boolean status){
        String result = status ? "Available" : "Not Available";
        return result;
    }
    
    @Override
    public String toString() {
//        return String.format("%d : %s : %s : %s : %s : %d : %b", eventID, eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, status);
//        return "Event { EventID =  " + eventID +
//               ", EventName = " + eventName +
//               ", EventLocation = " + eventLocation +
//               ", DateOfStart = " + dateOfStart +
//               ", DateOfEnd = " + dateOfEnd +
//               ", EventAttendence = " + eventAttendence +
//               ", Status = " + statusToString(status)+ "}";
        return String.format("|%10d|%15s|%30s|%15s|%20d|%15s|\n",
                    eventID,
                    eventName,
                    dateOfEnd + " -> " + dateOfStart,
                    eventLocation,
                    eventAttendence,
                    statusToString(status));

    }
}