package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class EventsList {
    
    List<Event> listEvent = new ArrayList<>();
    
    public EventsList() {
    }
//--------------------------------------------------------------------------
    private List<Event> readDataFromFile() throws IOException{
        ArrayList<Event> arr = new ArrayList<>();
        File file = new File("events.dat");
        String fileName = file.getAbsolutePath();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line ;
                while ((line = br.readLine()) != null){
                    arr.add(addEventFromFile(line));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            e.printStackTrace();
        }catch (IOException e) { 
            System.out.println("Error reading file: "+ fileName);
            e.printStackTrace();
        }
        return arr;
    }
    
    private Event addEventFromFile(String s){
        String[] a = s.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(a[3], formatter);
        return new Event(Integer.parseInt(a[0]), a[1], a[2], localDate, Boolean.parseBoolean(a[4]));
    }
    
    
    public void addEvent(Event event) {
        try {
            int maxID = 0;
            for (Event e : listEvent) {
                int currentID = e.getEventID();
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
            event.setEventID(maxID + 1);
            listEvent.add(event);
            FileWriter fileWriter = new FileWriter("events.dat", true);
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(event.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            
        }
}

    public void updateEvent(Event event){
        int size = listEvent.size();
        for (int i = 0; i < size; i++) {
            int eventID = listEvent.get(i).getEventID();
            if(eventID == event.getEventID()) {
                listEvent.get(i).setEventName(event.getEventName());
                listEvent.get(i).setEventLocation(event.getEventLocation());
                listEvent.get(i).setDateOfStart(event.getDateOfStart());
                listEvent.get(i).setDateOfEnd(event.getDateOfEnd());
                listEvent.get(i).setEventAttendence(event.getEventAttendence());
                listEvent.get(i).setStatus(event.getStatus());
            }
        }
    }
    
    public Event getEventByID(int eventID) {
        int size = listEvent.size();
        Event eve;
        for (int i = 0; i < size; i++) {
            eve = listEvent.get(i);
            if (eventID == eve.getEventID()) {
                return eve;
            }
        }
        return null;
    }
    
    public List<Event> deleteEvent(Event event){
        listEvent.remove(event);
        return null;
    }
    
    public List<Event> getEventList(){
        return this.listEvent;
    }
    
    public void displayEventList(List<Event> eventsList) {
        if (eventsList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            eventsList.forEach((e) -> {
                System.out.println(e);
            });
        }
    }
    
    public List<Event> searchEvent(List<Event> eventList, Predicate<Event> s){
        List<Event> result = new ArrayList<>();
        for (Event event : eventList){
            if(s.test(event)){
                result.add(event);
            }
        }return result;
    }
    
    public Event getEvent(int eventID){
        Event event = new Event();
        int size = listEvent.size();
        for (int i = 0; i<size; i++){
            if (eventID == listEvent.get(i).getEventID()){
                event = listEvent.get(i);
            }
        }return event;
    }
    
    public boolean isExistEvent(int ID) {
        return listEvent.stream().anyMatch((event) -> (event.getEventID()== ID));
    }
    
}