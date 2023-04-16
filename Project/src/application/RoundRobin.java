package application;


import java.util.*;
public class RoundRobin{

    public int q;
    public ArrayList<Process> processes;
    public static int currentPId;
    public int numberOfProcesses;
    public static int timer;
    public static int totalBurst = 0;
    double AverageTurnAroundTime, AverageWaitingTime, TotalTurnAround=0, TotalWaiting=0;

	public ArrayList<Things> t= new  ArrayList<Things>() ;
    public RoundRobin(int q) {
    	t= new  ArrayList<Things>() ;
        timer = 0;
        this.processes = new  ArrayList<Process>();
        this.numberOfProcesses = processes.size();
        this.q = q;
    }
    public RoundRobin(ArrayList<Process> processes,int q) {
    	t= new  ArrayList<Things>() ;
        timer = 0;
        this.processes = processes;
        this.numberOfProcesses = processes.size();
        this.q = q;
    }
   
   public ArrayList<Things>  add(Process process) {
	   
	  processes.add(process);
	timer=0;
	return  this.execute();
   }
    public ArrayList<Things>  execute() {
    	numberOfProcesses = processes.size();
        double [] bursts = new double[numberOfProcesses];

        for (int i = 0; i < numberOfProcesses; i++) {
            bursts[i] = processes.get(i).getBurstTime();
            totalBurst += bursts[i];
        }

        int counter=0;

        do {
            for (int i = 0; i < numberOfProcesses; i++) {
            	Things temp =new Things();
                if (processes.get(i).getArrivalTime() <= timer){
                    processes.get(i).setArrived(true);
                    counter=0;
                }
                else{
                    counter++;
                }

                if (processes.get(i).getBurstTime() > q && processes.get(i).isArrived()) {
                	temp.setName(processes.get(i).getID());
                	temp.setColor(processes.get(i).getColor());
                    processes.get(i).setStarted(true);
                    processes.get(i).setActive(true);
                    temp.setStart(timer);
                    processes.get(i).setBurstTime(processes.get(i).getBurstTime() - q);
                    timer += q;
                    temp.setEnd(timer);
                    totalBurst -= q;
                    processes.get(i).setActive(false);
                }
                else if (processes.get(i).getBurstTime() <= q && processes.get(i).getBurstTime() > 0 && processes.get(i).isArrived()) {
                	temp.setName(processes.get(i).getID());
                	temp.setColor(processes.get(i).getColor());
                	processes.get(i).setStarted(true);
                    processes.get(i).setActive(true);
                    double rem = processes.get(i).getBurstTime();
                    temp.setStart(timer);
                    timer += rem;
                    temp.setEnd(timer);
                    totalBurst -= rem;
                    processes.get(i).setTurnAroundTime(timer-processes.get(i).getArrivalTime());
                    processes.get(i).setWaitingTime(processes.get(i).getTurnAroundTime() - bursts[i]);
                    processes.get(i).setBurstTime(0);
                    processes.get(i).setActive(false);
                    processes.get(i).setFinished(true);
                }

                if (counter == numberOfProcesses){
                    timer += 1;
                    counter=0;
                }
                t.add(temp);
            }
        } while (totalBurst != 0);

         AverageTurnAroundTime=0; AverageWaitingTime=0; TotalTurnAround=0; TotalWaiting=0;
        System.out.println("Process\t\t\tWaitingTime\t\t\tTurnAroundTime");
        for (int i = 0; i < numberOfProcesses; i++) {
            double w = processes.get(i).getWaitingTime();
            double t = processes.get(i).getTurnAroundTime();
            TotalTurnAround += t;
            TotalWaiting += w;
        }

        AverageTurnAroundTime = (TotalTurnAround / numberOfProcesses);
        AverageWaitingTime = (TotalWaiting / numberOfProcesses);
     
        return t;
    }

    public double getAverageTurnAroundTime() {
		return AverageTurnAroundTime;
	}
	public void setAverageTurnAroundTime(double averageTurnAroundTime) {
		AverageTurnAroundTime = averageTurnAroundTime;
	}
	public double getAverageWaitingTime() {
		return AverageWaitingTime;
	}
	public void setAverageWaitingTime(double averageWaitingTime) {
		AverageWaitingTime = averageWaitingTime;
	}
    public void print() {
    	for(Things temp:t) {
    		if(temp.getStart()!=temp.getEnd())
    		 System.out.print(temp.getStart()+"("+temp.getColor()+")"+temp.getEnd()+" ");
    	}
    }
}