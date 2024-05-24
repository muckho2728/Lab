package controllers;

import java.time.LocalDate;
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
    private List<Event> listsEvent;

    public EventsManagement(EventsList eventsList) {
        super("Event Management", mainMenu);
        EventsManagement.eventsList = eventsList;
        
    }
    
    @Override
    public void execute(int n) {
        switch(n) {
            case 1:
                while(true) {
                    boolean isSuccess = createNewEvent();
                        if(isSuccess){
                            System.out.println("Create success!");
                        }else{
                            System.out.println("Create fail1");
                        }if(Helper.getConfirmExit()==true){break;}
                        }
                break;
            case 2:
                checkExists();
                break;
            case 3:
                searchByLocation();
                break;
            case 4:
                displayEventList(eventsList.getEventList());
                doUpdateEvent();
//                while(true) {
//                    boolean isSuccess = doUpdateEvent();
//                if(isSuccess){
//                    System.out.println("Update success!");
//                }else{
//                    System.out.println("Update fail!");
//                }if(Helper.getConfirmExit()==true){break;}
//                }
                break;
            case 5:
                displayEventList(eventsList.getEventList());
                doDelete();
//                while(true) {
//                    boolean isSuccess = doDelete();
//                if(isSuccess){
//                    System.out.println("Delete success!");
//                }else{
//                    System.out.println("Delete fail!");
//                }if(Helper.getConfirmExit()==true){break;}
//                }
                break;
            case 6:
                printList();
                break;
            case 7:
                System.exit(0);
        }
    }

//    private void displayEventList(List<Event> eventsList) {
//        if (eventsList.isEmpty()) {
//            System.out.println("List is empty!");
//        } else {
//            eventsList.forEach((e) -> {
//                System.out.println(e);
//            });
//        }
//    }
    public void displayEventList(List<Event> eventList){
        eventsList.displayEventList(eventsList.getEventList());
    }
    
    private boolean isDelete(int eveID) {
    try {
        Event existEvent = eventsList.getEventByID(eveID);
        if (existEvent == null) {
            System.out.println("Event not found!");
            return false;
        }
        System.out.println("Event found:\n" + existEvent); // Assuming Event class has a proper toString() method
        return true;
    } catch (Exception e) {
        return false;
    }
}
    
    private void doDelete() {
        try {
            int evtID = Helper.getInt("Enter Student ID");
            event = eventsList.getEventByID(evtID);
            if (event != null) {
                eventsList.deleteEvent(event);
                System.out.println("Delete sucess!");
            } else {
                System.out.println("Student is not exist. Delete failed!");
        } 
            }catch (Exception e) {
                System.out.println("Some thing is wrong!");
                }
        }

    private boolean createNewEvent() {
        try {
            String eventName = Helper.getString("Enter Event Name");
            String eventLocation = Helper.getString("Enter Event Location");
            LocalDate dateOfStart = Helper.getLocalDate("Enter Date Of Start Event with fomart yyyy/MM/dd");
            LocalDate dateOfEnd = Helper.getLocalDate("Enter Date Of End Event with fomart yyyy/MM/dd");
            int eventAttendence = Helper.getInt("Enter Number Of Member Attendence Event");
            boolean status = Helper.getStatus("Enter status 1 - Available, 0 - Not Available");
            Event event = new Event(eventName, eventLocation, dateOfStart, dateOfEnd, eventAttendence, status);
            eventsList.addEvent(event);
            
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    


    private void doUpdateEvent() {
        try {
            int evtID = Helper.getInt("Enter Student ID");
            event = eventsList.getEventByID(evtID);
            if (event != null) {
                eventsList.updateEvent(event);
                System.out.println("Update success!");
            } else {
                System.out.println("Student is not exist. Update failed!");
            }
        } catch (Exception e) {
            System.out.println("Some thing is wrong!");
        }
//        try {
//            displayEventList();
//            int id = Helper.getInt("Enter event ID you want to update");
//            Event existEvent = eventsList.getEvent(id);
//            if (existEvent == null){
//                System.out.println("Not Found !!!");
//                return false;
//            }
//            String eventName = Helper.getString("Enter event name");
//            String eventLocation = Helper.getString("Enter location event");
//            LocalDate dateOfStart = Helper.getLocalDate("Enter date of start event with fomart yyyy/MM/dd");
//            LocalDate dateOfEnd = Helper.getLocalDate("Enter date of end event with fomart yyyy/MM/dd");
//            int eventAttendence = Helper.getInt("Enter number member attendence of event");
//            boolean status = Helper.getStatus("Enter 1 - Available : 0 - Not Available");
//            eventsList.addEvent(event);
//            displayEventList();
//        } catch (Exception e) {
//            return false;
//        }return true;
    }
    
    
    
    private void searchByLocation(){
        String location = Helper.getString("Enter event location");
        List<Event> kq = eventsList.searchEvent(eventsList.getEventList(), 
                s->s.getEventLocation().equalsIgnoreCase(location));
        if (kq.isEmpty())
            System.out.println("Not Found !!!");
        else{
            eventsList.displayEventList(kq);
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

    private void printList() {
//        List<Event> eventList = eventsList.getEventList();
//        if (eventList.isEmpty()) {
//            System.out.println("No events found.");
//        } else {
//            System.out.println("Event List:");
//            eventList.forEach((event) -> {
//                System.out.println(event); // Assuming Event class has a proper toString() method
//                });
//            }
//        }
        List<Event> eventList = eventsList.getEventList();
        if (eventList.isEmpty()) {
            System.out.println("No events found.");
        } else {
            System.out.println(String.format("|%10s|%15s|%30s|%15s|%20s|%15s|\n",
                "ID", "NAME", "DATE", "LOCATION", "NUMBER OF ATTENDEES", "STATUS"));

            eventList.forEach((event) -> {
                System.out.println(event.toString());
            });
        }
    }
}

    
