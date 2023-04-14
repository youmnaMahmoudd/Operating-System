import java.util.*;
public class RoundRobin extends Scheduler {

    public int q;
    public static int totalBurst = 0;
    static int presentProcesses;

    public RoundRobin(ArrayList<Process> processes, int contSw, int q) {
        super(processes, contSw);
        this.q = q;
        this.contextSwitch = contSw;
    }

    int numberOfProcesses = processes.size();
    double[] bursts = new double[numberOfProcesses];

    public void execute() {

        for (int i = 0; i < numberOfProcesses; i++) {
            bursts[i] = processes.get(i).getBurstTime();
            totalBurst += bursts[i];
        }

        presentProcesses = numberOfProcesses;
        int counter=0;

        do {
            for (int i = 0; i < numberOfProcesses; i++) {

                if (processes.get(i).getArrivalTime() <= timer){
                    processes.get(i).setArrived(true);
                    counter=0;
                }
                else{
                    counter++;
                }

                if (processes.get(i).getBurstTime() > q && processes.get(i).isArrived()) {
                    processes.get(i).setStarted(true);
                    processes.get(i).setActive(true);
                    processes.get(i).setBurstTime(processes.get(i).getBurstTime() - q);
                    timer += q;
                    totalBurst -= q;
                    processes.get(i).setActive(false);
                    timer += contextSwitch;
                }
                else if (processes.get(i).getBurstTime() <= q && processes.get(i).getBurstTime() > 0 && processes.get(i).isArrived()) {
                    processes.get(i).setStarted(true);
                    processes.get(i).setActive(true);
                    double rem = processes.get(i).getBurstTime();
                    for (int j = 0; j < numberOfProcesses; j++) {
                        if (processes.get(j).getBurstTime() != 0)
                            processes.get(j).setTurnAroundTime(processes.get(j).getTurnAroundTime() + rem);
                    }
                    totalBurst -= rem;
                    timer += rem;
                    processes.get(i).setTurnAroundTime(timer-processes.get(i).getArrivalTime());
                    processes.get(i).setWaitingTime(processes.get(i).getTurnAroundTime() - bursts[i]);
                    processes.get(i).setBurstTime(0);
                    processes.get(i).setActive(false);
                    processes.get(i).setFinished(true);
                    presentProcesses--;
                    if(presentProcesses!=0)
                        timer += contextSwitch;
                }

                if (counter == numberOfProcesses){
                    timer += 1;
                    counter=0;
                }
            }
        } while (totalBurst != 0);

        double AverageTurnAroundTime, AverageWaitingTime, TotalTurnAround=0, TotalWaiting=0;
        System.out.println("Process\t\t\tWaitingTime\t\t\tTurnAroundTime");
        for (int i = 0; i < numberOfProcesses; i++) {
            double w = processes.get(i).getWaitingTime();
            double t = processes.get(i).getTurnAroundTime();
            TotalTurnAround += t;
            TotalWaiting += w;
            System.out.println(processes.get(i).getName() + "\t\t\t\t" + w + "\t\t\t\t\t" + t);
        }

        AverageTurnAroundTime = (TotalTurnAround / numberOfProcesses);
        AverageWaitingTime = (TotalWaiting / numberOfProcesses);
        System.out.println("AverageTurnAroundTime = " + AverageTurnAroundTime);
        System.out.println("AverageWaitingTime = " + AverageWaitingTime);
        System.out.println("Time of Execution = "+timer);
    }
}
