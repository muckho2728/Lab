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
import views.Helper;

public class EventsList {
    
    List<Event> listEvent = new ArrayList<>();
    
    public EventsList() {
    }
//--------------------------------------------------------------------------
    private List<Event> readDataFromFile() throws IOException{
        ArrayList<Event> listEvent = new ArrayList<>();
        File file = new File("events.dat");
        String fileName = file.getAbsolutePath();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line ;
                while ((line = br.readLine()) != null){
                    listEvent.add(addEventFromFile(line));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }catch (IOException e) { 
            System.out.println("Error reading file: "+ fileName);
        }
        return listEvent;
    }
    
    private Event addEventFromFile(String s){
        String[] a = s.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(a[3], formatter);
        return new Event(Integer.parseInt(a[0]), a[1], a[2], localDate, Boolean.parseBoolean(a[4]));
    }
    
    public void addEvent(Event event) {
        try {
            List<Event> eventsList = readDataFromFile();
            int maxID = 0;
            for (Event e : eventsList) {
                int currentID = e.getEventID();
                if (currentID > maxID) {
                    maxID = currentID;
                }
            }
            event.setEventID(maxID + 1);
            eventsList.add(event);
            FileWriter fileWriter = new FileWriter("events.dat");
            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write(event.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            
        }
}

public void updateEvent(Event event) {
    try {
        List<Event> eventsList = readDataFromFile(); // assuming this method returns the list of events from the file
        int index = -1;
        for (int i = 0; i < eventsList.size(); i++) {
            if (eventsList.get(i).getEventID() == event.getEventID()) {
                index = i;
                break;
            }   
        }
        if (index != -1) {
            eventsList.set(index, event); // replace the old event with the updated one
            FileWriter fileWriter = new FileWriter("events.dat"); // overwrite the file
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Event e : eventsList) {
                bufferedWriter.write(e.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        }
    } catch (IOException e) {
        // handle the exception
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
    
    public void deleteEvent(Event event){
        listEvent.remove(event);
    }
    
    public List<Event> getEventList(){
        return this.listEvent;
    }
    
    public void displayEventList(List<Event> eventsList) {
        if (eventsList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            eventsList.forEach(System.out::println);
        }
    }
    
    public List<Event> searchEvent(List<Event> eventList, Predicate<Event> s){
        List<Event> result = new ArrayList<>();
        eventList.stream().filter((event) -> (s.test(event))).forEachOrdered((event) -> {
            result.add(event);
        });
return result;
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
