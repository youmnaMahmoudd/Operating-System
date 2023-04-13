import java.awt.desktop.SystemEventListener;
import java.util.*;
public class RoundRobin {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number of Processes : ");
        int num = s.nextInt();
        int totalBurst = 0;
        int time = 0;

        Process process[] = new Process[num];

        for (int i = 0; i < num; i++) {
            process[i] = new Process(i + 1, "P" + (i + 1), 0, 0, 0, 0, 0, false, false, false, false);
        }

        for (int i = 0; i < num; i++) {
            System.out.println("Enter Arrival time for P" + (i + 1) + " : ");
            process[i].setArrivalTime(s.nextInt());
        }

        double bursts[] = new double[num];

        for (int i = 0; i < num; i++) {
            System.out.println("Enter Burst time for P" + (i + 1) + " : ");
            int burst = s.nextInt();
            process[i].setBurstTime(burst);
            bursts[i] = burst;
            totalBurst += burst;
        }

        System.out.println("Enter time quantum in ms : ");
        int q = s.nextInt();

        boolean entered;

        do {
            for (int i = 0; i < num; i++) {
                entered = false;

                if (process[i].getArrivalTime() <= time)
                    process[i].setArrived(true);

                if (process[i].getBurstTime() >= q && process[i].isArrived()) {
                    entered = true;
                    process[i].setStarted(true);
                    process[i].setActive(true);
                    for (int j = 0; j < num; j++) {
                        if (process[j].getBurstTime() != 0)
                            process[j].setTurnAroundTime(process[j].getTurnAroundTime() + q);
                    }
                    process[i].setBurstTime(process[i].getBurstTime() - q);
                    time += q;
                    totalBurst -= q;
                    process[i].setActive(false);
                }
                else if (process[i].getBurstTime() < q && process[i].getBurstTime() > 0 && process[i].isArrived()) {
                    entered = true;
                    process[i].setStarted(true);
                    process[i].setActive(true);
                    double rem = process[i].getBurstTime();
                    for (int j = 0; j < num; j++) {
                        if (process[j].getBurstTime() != 0)
                            process[j].setTurnAroundTime(process[j].getTurnAroundTime() + rem);
                    }
                    totalBurst -= rem;
                    time += rem;
                    process[i].setBurstTime(0);
                    process[i].setActive(false);
                    process[i].setFinished(true);
                }

                if (!entered)
                    time += 1;
            }
        } while (totalBurst != 0) ;

        for (int i = 0; i < num; i++) {
            process[i].setTurnAroundTime(process[i].getTurnAroundTime() - process[i].getArrivalTime());
            process[i].setWaitingTime(process[i].getTurnAroundTime() - bursts[i]);
        }

        double AverageTurnAroundTime = 0, AverageWaitingTime = 0;
        System.out.println("Process\t\t\tWaitingTime\t\t\tTurnAroundTime");
        for (int i = 0; i < num; i++) {
            double w = process[i].getWaitingTime();
            double t = process[i].getTurnAroundTime();
            AverageTurnAroundTime += t;
            AverageWaitingTime += w;
            System.out.println(process[i].getName() + "\t\t\t\t" + w + "\t\t\t\t\t" + t);
        }

        AverageTurnAroundTime = (AverageTurnAroundTime / num);
        AverageWaitingTime = (AverageWaitingTime / num);
        System.out.println("AverageTurnAroundTime = " + AverageTurnAroundTime);
        System.out.println("AverageWaitingTime = " + AverageWaitingTime);
    }
}
