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
        listEvent = readDataFromFile();
    }
    
//--------------------------------------------------------------------------

    private List<Event> readDataFromFile() {
        ArrayList<Event> arr = new ArrayList<>();
        File file = new File("events.dat");
        String fileName = file.getAbsolutePath();
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(!line .isEmpty()) {
                        arr.add(addEventFromFile(line));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();
        }
        return arr;
    }

    private Event addEventFromFile(String s) {
        String[] a = s.split(" : ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate dateOfStart = LocalDate.parse(a[3], formatter);
        LocalDate dateOfEnd = LocalDate.parse(a[4], formatter);
        byte deleteFlag = Byte.parseByte(a[7]);
        return new Event(Integer.parseInt(a[0]), a[1], a[2], dateOfStart, dateOfEnd, Integer.parseInt(a[5]), Boolean.parseBoolean(a[6]), deleteFlag);
    }

    public boolean addEvent(Event event) {

        int maxID = 0;
        byte delFlag = 0;
        for (Event e : listEvent) {
            int currentID = e.getEventID();
            if (currentID > maxID) {
                maxID = currentID;
            }
        }
        event.setEventID(maxID + 1);
        event.setDeleteFlag(delFlag);
        listEvent.add(event);
        saveEventsToFile();
        return true;
//        try {
//            FileWriter fileWriter = new FileWriter("events.dat", true);
//            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//            bufferedWriter.write(event.toString());
//            bufferedWriter.newLine();
//        } catch (Exception e) {
//        }

    }

//    public void addEvent(Event event) {
//        
//        try {
//            int maxID = 0;
//            for (Event e : listEvent) {
//                int currentID = e.getEventID();
//                if (currentID > maxID) {
//                    maxID = currentID;
//                }
//            }
//            event.setEventID(maxID + 1);
//            listEvent.add(event);
//            FileWriter fileWriter = new FileWriter("events.dat", true);
//            try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
//                bufferedWriter.write(event.toString());
//                bufferedWriter.newLine();
//            }
//        } catch (IOException e) {
//            
//        }
//}
    public boolean updateEvent(Event event) {
        for (Event e : listEvent) {
            if (e.getEventID() == event.getEventID() && e.getDeleteFlag()==0) {
                e.setEventName(event.getEventName());
                e.setEventLocation(event.getEventLocation());
                e.setDateOfStart(event.getDateOfStart());
                e.setDateOfEnd(event.getDateOfEnd());
                e.setEventAttendence(event.getEventAttendence());
                e.setStatus(event.getStatus());
                saveEventsToFile();
                return true;
            } 
        }return false;
    }

    public List<Event> getEventToDisplay (){
        List<Event> result = new ArrayList<>();
        //duyet tung phan tu trong list
        for (Event e : listEvent) {
                if(e.getDeleteFlag()==0){
                    result.add(e);
                }
            }
        return result;
        //ktra phan tu day DlF == 0 (true) nếu bằng thì lấy phần tử đấy 
        //trả về list kết quả
    }
            
    
    public Event getEventByID(int eventID) {
        for (Event eve : listEvent) {
            if (eventID == eve.getEventID() 
                    && eve.getDeleteFlag() == 0 ) {
                return eve;
            }
        }
        return null;
    }
    
    public boolean deleteEvent(int eventID) {
        //nhận vào 1 id
        Event gEvt = getEventByID(eventID);
        if(gEvt == null) return false;
        byte dl = 1;
        //ktra có tồn tại hay ko getByID
        gEvt.setDeleteFlag(dl);
        saveEventsToFile();
        return true;
        //thực hiện deleteflag == 1-> đã xóa
        
    }

    public List<Event> getEventList() {
        return this.listEvent;
    }

    

    public List<Event> searchEvent(List<Event> eventList, Predicate<Event> s) {
        List<Event> result = new ArrayList<>();
        for (Event event : eventList) {
            if (s.test(event) && event.getDeleteFlag()==0) {
                result.add(event);
            }
        }
        return result;
    }

    public Event getEvent(int eventID) {
        Event event = new Event();
        int size = listEvent.size();
        for (int i = 0; i < size; i++) {
            if (eventID == listEvent.get(i).getEventID()) {
                event = listEvent.get(i);
            }
        }
        return event;
    }

    public boolean isExistEvent(int ID) {
        return listEvent.stream().anyMatch((event) -> (event.getEventID() == ID));
    }

    
    public void saveEventsToFile() {
        try (FileWriter fileWriter = new FileWriter("events.dat")) {
            for (Event event : listEvent) {
                fileWriter.write(event.convertToLine());
                fileWriter.write(System.lineSeparator()); // Add a newline after each event
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
}

}
