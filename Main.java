import java.util.*;

public class Main {
    public static void main(String[] args){

        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number of Processes : ");
        int num = s.nextInt();
        ArrayList<Process> processes = new ArrayList<Process>(num);

        System.out.println("Enter time quantum in ms : ");
        int q = s.nextInt();
        System.out.println("Enter context switch time in ms : ");
        int contxtswitch = s.nextInt();

        for(int i=0 ; i<num;i++){
            System.out.println("Enter Arrival time for P" + (i + 1) + " : ");
            double arrtime = s.nextInt();
            System.out.println("Enter Burst time for P" + (i + 1) + " : ");
            double busrttime = s.nextInt();
            processes.add(new Process(i + 1, "P" + (i + 1), 0, arrtime, busrttime, 0, 0, false, false, false, false));
        }

        RoundRobin roundRobin = new RoundRobin(processes, contxtswitch,q );
        roundRobin.execute();

    }
}
