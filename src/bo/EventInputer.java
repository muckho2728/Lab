
package bo;

import entity.Event;
import entity.Status;
import utils.Validator;

public class EventInputer {
    private Event event;

    public EventInputer() {
        event= new Event();
    }
    
    public Event input(){
        event.setNameEvent(Validator.getString("Enter name: ",
                "Please enter event name is at least five characters long and does not contain spaces", "^[^\\s]{5,}$"));
        event.setDate(Validator.getDate("Enter Date: ", "Just be format date (yyyy-MM-dd)", "yyyy-MM-dd"));
        event.setLocation(Validator.getString("Enter location: ", "Location can not empty!!!", "^(?!\\s*$).+"));
        event.setNumberOfAttedees(Validator.getInt("Enter number of attendees: ",
                "Please enter number >0 ", "Invalid!", 1, Integer.MAX_VALUE));
        int choice = Validator.getInt("Only two status:\n"
                + "1. Available\n"
                + "2. Not Available\n"
                + "Enter you choice: ", "Just 1->2", "Invalid!", 1, 2);
        event.setStatus(Status.getStatus(choice));
        return event;
    }
}
