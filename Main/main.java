package Main;
import java.util.List;

public class main {
    public static void main(String[] args){
        Scheduler fcfs = new FirstComeFirstServe();
        fcfs.add(new Process("P1", 0, 2));
        fcfs.add(new Process("P2", 0, 4));
        fcfs.add(new Process("P3", 2, 1));
        fcfs.add(new Process("P4", 2, 3));
        fcfs.add(new Process("P5", 3, 2));
        fcfs.process();
        display(fcfs);
    }
    public static void display(Scheduler object)
    {
        System.out.println("Process\tAT\tBT\tWT\tTAT");

        for (Process row : object.getRows())
        {
            System.out.println(row.getName() + "\t" + row.getArrivalTime() + "\t" + row.getBurstTime() + "\t" + row.getWaitingTime() + "\t" + row.getTurnAroundTime());
        }

        System.out.println();

        for (int i = 0; i < object.getTimeline().size(); i++)
        {
            List<Event> timeline = object.getTimeline();
            System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");

            if (i == object.getTimeline().size() - 1)
            {
                System.out.print(timeline.get(i).getFinishTime());
            }
        }

        System.out.println("\n\nAverage WT: " + object.getAverageWaitingTime() + "\nAverage TAT: " + object.getAverageTurnAroundTime());
    }
}
