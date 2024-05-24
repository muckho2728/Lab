package views;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper { //This class to handle data type, validate input from user
  
    /*
    Object: Learn about static key word and Exception handler in Java
    */
    
    public static String getString(String msg) {
        String str = "";
        while(true){
            try{
                System.out.println(msg + " : ");
                str = new Scanner(System.in).nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
                getString(msg);
            }
        }
        return str;
    }
//------------------------------------------------------------------------------    
    
    public static int getInt(String msg){
        int tg = 0;
        while(true) {
            try {
                System.out.println(msg + " : ");
                tg = new Scanner(System.in).nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
                getInt(msg);
            }
        }
        return tg;
    }
//------------------------------------------------------------------------------
    
    public static double getDouble(String msg) {
        double db = 0;
        while (true) {
            try {
                System.out.println(msg + " : ");
                db = new Scanner(System.in).nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
                getDouble(msg);
            }
        }
        return db;
    }
//------------------------------------------------------------------------------
    
    public static LocalDate getLocalDate(String msg) {
        LocalDate result;
        String str;
        //Accept user enter again if input is invalid
        while(true) {
            try {
                //Print message for user enter input
                System.out.println(msg + " : ");
                str = new Scanner(System.in).nextLine();
                //Declare a date time formatter with format of date if YYYY-MM-DD
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                result = LocalDate.parse(str, formatter);
                break;
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
                getLocalDate(msg);
            }
        }
        return result;
    }
//------------------------------------------------------------------------------
    
    /**
     * This method to get gender from 0 and 1 covert to boolean type
     * @param msg
     * @return True if Available, false if Not Available
     */
    
    public static boolean getStatus(String msg) {
        int i;
        //Accept user enter again if input is invalid
        while (true) {
            //Exception handler when user input wrong data type
            try {
                System.out.println(msg + " : "); 
                i = new Scanner(System.in).nextInt();
                if (i==0) {
                    return false;
                }
                if (i==1) {
                    return true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input, Please try again!");
                getStatus(msg);
            }
        }
    }
//------------------------------------------------------------------------------
    private static boolean getConfirmExit() {
        while (true) {
            String choice = Helper.getString("Do u want to exit? (Y/N)");
            if(choice.toLowerCase().equals("y")){
                return true;
            }
            if (choice.toLowerCase().equals("n")) {
                return false;
            }
            System.out.println("Enter only Y or N");
        }
    }

    public static void actionWithConfirmExit(Runnable function){
        while (true) {
            function.run();
            if(getConfirmExit()){
                break;
            }
        }
    }
}
    
    
