package View;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu {
    
    protected String title;
    protected ArrayList<String> choose;

    public Menu() {
    }

    public Menu(String td, String[] menuOptions) {
        title = td;
        choose = new ArrayList<>();
        for (String option : menuOptions) {
            choose.add((String) option);
        }
    }
//------------------------------------------------------------------------------    
    
    public void display(){
        System.out.println("              ");
        System.out.println(title);
        System.out.println("-------------------------------------------");
        for (int i = 0; i<choose.size(); i++) {
            System.out.println((i+1) + "." + choose.get(i));
        }
        System.out.println("-------------------------------------------");
    }
//------------------------------------------------------------------------------

    public int getSelected(){
        display();
        int n = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter selection: ");
        try {
            n = sc.nextInt();
            if (n<1 || n > choose.size()){
                System.out.println("Please enter choice from 1 to " + choose.size());
                getSelected();
            }
        } catch (Exception e) {
            System.out.println("Please enter integer!");
            getSelected();
        }
        return n;
    }
//------------------------------------------------------------------------------

    public abstract void execute(int n);
    
//------------------------------------------------------------------------------

    public void run() {
        while(true) {
            int n = getSelected();
////            if(n==5) return;
//              // Check for exit conditions
            if (n == 0 || n == -1) {
                break; // Exit condition
            }
        execute(n);
        }
    }
}