package controllers;

import java.time.LocalDate;
import java.util.List;
import models.Event;
import models.EventsList;
import views.Helper;
import views.Menu;

public class EventsManagement extends Menu {
    
    static String[] mainMenu = {"Create An Event", "Search event",
        "Add new event", "Update event", 
        "Delete event", "Exit"};
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
                boolean isSuccess = createNewEvent();
                if(isSuccess){
                    System.out.println("Create success!");
                }else{
                    System.out.println("Create fail1");
                }
                
                break;
            case 2:
                checkExists();
                break;
            case 3:
                searchByLocation();
                break;
            case 4:
                isSuccess = updateEvent();
                if(isSuccess){
                    System.out.println("Update success!");
                }else{
                    System.out.println("Update fail!");
                }
                break;
            case 5:
                isSuccess = doDelete();
                if(isSuccess){
                    System.out.println("Delete success!");
                }else{
                    System.out.println("Delete fail!");
                }
                break;
            case 6:
                printList();
            case 7:
                System.exit(0);
        }
    }

    public void displayEventList(){
        eventsList.displayEventList(eventsList.getEventList());
    }
    private boolean doDelete(){
        try {
            displayEventList();
            int eveID = Helper.getInt("Enter Event ID you want to delete");
            Event existEvent = eventsList.getEventByID(eveID);
            if(existEvent == null){
                System.out.println("Not Found !!!");
                return false;
            }
            eventsList.deleteEvent(existEvent);
        } catch (Exception e) {
            return false;
        }return true;
    }

    private boolean createNewEvent() {
        try {
            String eventName = Helper.getString("Enter Event Name");
            String eventLocation = Helper.getString("Enter Event Location");
            LocalDate dateOfStart = Helper.getLocalDate("Enter Date Of Start Event");
            LocalDate dateOfEnd = Helper.getLocalDate("Enter Date Of End Event");
            int eventAttendence = Helper.getInt("Enter Number Of Member Attendence Event");
            boolean status = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
            Event event = new Event(eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, status);
            eventsList.addEvent(event);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private boolean updateEvent() {
        try {
            displayEventList();
            int id = Helper.getInt("Enter event ID you want to update");
            Event existEvent = eventsList.getEvent(id);
            if (existEvent == null){
                System.out.println("Not Found !!!");
                return false;
            }
            String eventName = Helper.getString("Enter event name");
            String eventLocation = Helper.getString("Enter location event");
            LocalDate dateOfStart = Helper.getLocalDate("Enter date of start event with fomart yyyy/MM/dd");
            LocalDate dateOfEnd = Helper.getLocalDate("Enter date of end event with fomart yyyy/MM/dd");
            int eventAttendence = Helper.getInt("Enter number member attendence of event");
            boolean status = Helper.getStatus("Enter 1 - Available : 0 - Not Available");
            eventsList.updateEvent(event);
        } catch (Exception e) {
            return false;
        }return true;
    }
    
    private void doSearching() {
        String[] menuSearch = {"Search by ID", "Search by Event Name", "Search by Location",
            "Search by Date Of Start Event", "Search by Date Of End Event", "Search by Event Attendence", 
            "Search by Status", "Exist"
        };
        new Menu("Search Option", menuSearch) {
            @Override
            public void execute(int n) {
                switch(n) {
                    case 1:
                        searchByID();
                        break;
                    case 2:
                        searchByEventName();
                        break;
                    case 3:
                        searchByLocation();
                        break;
                    case 4:
                        searchByDateOfStart();
                        break;
                    case 5:
                        searchByDateOfEnd();
                        break;
                    case 6:
                        searchByEventAttendence();
                        break;
                    case 7:
                        searchByStatus();
                        break;
                    case 8:
                        System.out.println("Exit search");
                        new EventsManagement(eventsList).run();
                }
            }
        }.run();
    }
    
    private void searchByID(){
        int id = Helper.getInt("Enter event ID");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(),
                s->s.getEventID() == id);
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByEventName(){
        String eventName = Helper.getString("Enter event name");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getEventName().equalsIgnoreCase(eventName));
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByLocation(){
        String location = Helper.getString("Enter event location");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getEventLocation().equalsIgnoreCase(location));
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByDateOfStart(){
        LocalDate dateOfStart = Helper.getLocalDate("Enter date of start event");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getDateOfStart().equals(dateOfStart));
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByDateOfEnd(){
        LocalDate dateOfEnd = Helper.getLocalDate("Enter date of end event");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getDateOfEnd().equals(dateOfEnd));
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByEventAttendence(){
        int at = Helper.getInt("Enter event attendence");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(),
                s->s.getEventAttendence()== at);
        if (kq.size() == 0)
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
        }
    }
    
    private void searchByStatus(){
        boolean status = Helper.getStatus("Enter 1 - Available : 0 - Not Available");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(),
                s->s.getStatus() == status);
        if (kq.isEmpty())
            System.out.println("Not Found !!!");
        else {
            eventsList.displayEventList(kq);
        }
                
    }

    private void checkExists() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void printList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

    
