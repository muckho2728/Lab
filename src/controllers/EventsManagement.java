package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.Event;
import models.EventsList;
import views.Helper;
import views.Menu;

public class EventsManagement extends Menu {
    
    static String[] mainMenu = {"Create a new event", 
                                "Check if an event exists",
                                "Search for event information by location", 
                                "Update event", 
                                "Delete event",
                                "Print the list of events from the file",
                                "Others - Quit"};
    private static EventsList eventsList;
    private static Event event;
    

    public EventsManagement(EventsList eventsList) {
        super("Event Management", mainMenu);
        EventsManagement.eventsList = eventsList;
        
    }
    
    @Override
    public void execute(int n) {
        switch(n) {
            case 1:
                Helper.actionWithConfirmExit(()->createNewEvent());
                break;
            case 2:
                checkExists();
                break;
            case 3:
                Helper.actionWithConfirmExit(()->searchByLocation());
                break;
            case 4:
                Helper.actionWithConfirmExit(()->updateEvent());
                break;
            case 5:
                Helper.actionWithConfirmExit(()->doDelete());
                break;
            case 6:
                displayEventList(eventsList.getEventToDisplay());
                break;
            case 7:
                System.exit(0);
        }
    }

    private void createNewEvent() {
            String eventName = Helper.getString("Enter Event Name");
            String eventLocation = Helper.getString("Enter Event Location");
            LocalDate dateOfStart = Helper.getLocalDate("Enter Date Of Start Event");
            LocalDate dateOfEnd = Helper.getLocalDate("Enter Date Of End Event");
            int eventAttendence = Helper.getInt("Enter Number Of Member Attendence Event");
            boolean status = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
            event = new Event(eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, status);
            boolean result = eventsList.addEvent(event);
            if(result){
                System.out.println("Create success!");
            }else{
                System.out.println("Create fail!");
            }
    }
    
    private void checkExists() {
        int ID = Helper.getInt("Enter ID event");
        if (eventsList.isExistEvent(ID)) {
            System.out.println("Exist Event");
        } else {
            System.out.println("No Event Found!");
        }
    }
    private void searchByLocation(){
        String location = Helper.getString("Enter event location");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getEventLocation().equalsIgnoreCase(location));
        if (kq.isEmpty())
            System.out.println("Not Found !!!");
        else{
            displayEventList(kq);
        }
    }
    private void updateEvent() {
            displayEventList(eventsList.getEventToDisplay());
            int id = Helper.getInt("Enter event ID you want to update");
            Event existEvent = eventsList.getEvent(id);
            if (existEvent == null){
                System.out.println("Not Found !!!");
                return;
            }
            String eventName = Helper.getString("Enter event name");
            String eventLocation = Helper.getString("Enter location event");
            LocalDate dateOfStart = Helper.getLocalDate("Enter date of start event with fomart yyyy/MM/dd");
            LocalDate dateOfEnd = Helper.getLocalDate("Enter date of end event with fomart yyyy/MM/dd");
            int eventAttendence = Helper.getInt("Enter number member attendence of event");
            boolean status = Helper.getStatus("Enter 1 - Available : 0 - Not Available");
            Event event = new Event(id, eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, status);
            boolean result = eventsList.updateEvent(event);
            if(result) {
                System.out.println("Update success!");
            }else{
                System.out.println("Update fail!");
            }
         
    }
    private void doDelete(){
            displayEventList(eventsList.getEventToDisplay());
            int eveID = Helper.getInt("Enter Event ID you want to delete");
            boolean result = eventsList.deleteEvent(eveID);
            if(result) {
                System.out.println("Delete success!");
            } else {
                System.out.println("Delete fail!");
            }
            
    }
    
       public void displayEventList(List<Event> eventsList) {
    if (eventsList.isEmpty()) {
        System.out.println("List is empty!");
    } else {
        System.out.println("| ID       | NAME           | LOCATION       | DATE OF START    | DATE OF END      | ATTENDEES         | STATUS           |");
        System.out.println("|----------|----------------|----------------|------------------|------------------|-------------------|------------------|");

        eventsList.forEach((event) -> {
            System.out.printf("| %-8d | %-14s | %-14s | %-16s | %-16s | %-17d | %-16s |\n",
                    event.getEventID(), event.getEventName(), event.getEventLocation(),
                    event.getDateOfStart(), event.getDateOfEnd(), event.getEventAttendence(),
                    event.getStatus());
        });
    }
//    public void displayEventList(List<Event> eventsList) {
//        if (eventsList.isEmpty()) {
//            System.out.println("List is empty!");
//        } else {
//            System.out.println(String.format("|%10s|%15s|%15s|%20s|%20s|%20s|%15s|\n",
//                "ID", "NAME", "LOCATION", "DATE OF START","DATE OF END", "NUMBER OF ATTENDEES", "STATUS"));
//            eventsList.forEach((e) -> {
////                System.out.printf("| %-3d | %-15s | %-12s | %-9s | %-7d | %-14s |%-13s|\n", 
////                       event.getEventID(), event.getEventName(),event.getDateOfStart(), event.getDateOfEnd(),
////                       event.getEventLocation(), event.getEventAttendence(), event.getStatus());
//                System.out.println(e);
//            });
//        }
//    }
 
}

    
}    
    
    
    
    

    

    

    
