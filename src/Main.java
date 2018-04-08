import java.sql.Time;
import java.util.Scanner;

/**
 * Created by Armaghan on 12/2/2017.
 */
public class Main {
    private Scanner scanner= new Scanner(System.in);
    private String input;
    private GList gList = new GList();
    AgencyList agencyList= new AgencyList(gList);

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println("enter your command please");
        while (true){
            main.process();
        }
    }

    private void process(){
        input = scanner.nextLine();

        if (input.toLowerCase().contains("add service"))
            gList.add_service(input.split(" ")[2]);

        else if (input.toLowerCase().contains("add subservice"))
            gList.add_subservice(input.split(" ")[2], input.split(" ")[4]);

        else if (input.toLowerCase().contains("add agency"))
            agencyList.addAgency(input.split(" ")[2]);

        else if (input.toLowerCase().contains("list agencies")){
            System.out.println("the list of agencies:");
            agencyList.printAList();
            System.out.println();
        }

        else if (input.toLowerCase().equals("list services")){
            System.out.println("the list of services and subservices:");
            gList.printGList(gList.head);
            System.out.println();
        }

        else if (input.toLowerCase().contains("add offer"))
            agencyList.add_offer(input.split(" ")[2], input.split(" ")[4]);

        else if (input.toLowerCase().contains("list offers"))
            agencyList.printOffers(input.split(" ")[2]);

        else if (input.toLowerCase().contains("list services from")) {   // printing the subservices of a service
            System.out.println("the subservices of " + input.split(" ")[3] + " :" );
            gList.printSubOfService(input.split(" ")[3]);
        }

        else if (input.toLowerCase().contains("delete"))
            agencyList.delete(input.split(" ")[1], input.split(" ")[3]);

        else if(input.toLowerCase().contains("list orders")) {
            System.out.println("the orders by priority are:");
            if (agencyList.getAgency(input.split(" ")[2]) != null)
                agencyList.getAgency(input.split(" ")[2]).pQueue.printHeap();
            else
                System.out.println("this agency doesn't exict");
        }

        else if (input.toLowerCase().contains("order")){
           Order o = new Order(input.split(" ")[1], input.split(" ")[3],
                  getTime(),
                   input.split(" ")[5] , Integer.parseInt(input.split(" ")[7]));
           if (agencyList.isOffered(input.split(" ")[1], input.split(" ")[3]))
                agencyList.getAgency(input.split(" ")[3]).addToPQueue(o);
        }

        else if (input.equals("end"))
            System.exit(0);

        else
            System.out.println("this input is not valid");
    }

    private int getTime(){
        Time t= new Time(System.currentTimeMillis());
        String s1= t.toString().split(":")[2];
        String s2= t.toString().split(":")[1];
        return Integer.parseInt(s1) + 60*Integer.parseInt(s2);
    }
}