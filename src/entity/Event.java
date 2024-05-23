
package entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event implements Serializable,Comparable<Event>{ 
    private int ID;
    private String nameEvent;
    private Date date;
    private String location;
    private int numberOfAttedees;
    private Status status;

    public Event(int ID, String nameEvent, Date date, String location, int numberOfAttedees, Status status) {
        this.ID = ID;
        this.nameEvent = nameEvent;
        this.date = date;
        this.location = location;
        this.numberOfAttedees = numberOfAttedees;
        this.status = status;
    }

    public Event() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfAttedees() {
        return numberOfAttedees;
    }

    public void setNumberOfAttedees(int numberOfAttedees) {
        this.numberOfAttedees = numberOfAttedees;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public int compareTo(Event o) {
        if(date.compareTo(o.date)==0){
            return nameEvent.compareTo(o.nameEvent);
        }
        return date.compareTo(date);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String name = null;
        return "Event{" + "ID=" + ID + ", name=" + name + ", date=" + dateFormat.format(date) +
                ", location=" + location + ", numberOfAttedees=" + numberOfAttedees + ", status=" + status.getValue() + '}';
    }

}
