
package controllers;
import java.io.IOException;
import models.EventsList;

public class Main {
    
    public static void main(String[] args) throws IOException {
        EventsList eventList = new EventsList();
        new EventsManagement(eventList).run();
    }
}
