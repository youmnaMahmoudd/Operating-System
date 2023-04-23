package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Priority {

	public static ArrayList<Process> priorityNonPremmetive(ArrayList<Process> data){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> process = data;
		int curTime = 0;
		while(process.size() > 0) {
			int ind = curProcess(curTime,process);
			if(ind != -1) {
				Process p = process.get(ind);
				p.setDur(p.getBurstTime());
				p.setStartTime(curTime);
				ans.add(p);
				curTime+=p.getBurstTime();
				process.remove(ind);
			}else curTime ++;
		}
		return ans;
	}
	public static ArrayList<Process> pp (ArrayList<Process> data){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> process = new ArrayList<>();
		ArrayList<Process> sort = new ArrayList<>();
		for(int i9=0;i9<data.size();i9++) {
			Process p = data.get(i9);
			Process g = new Process(p.getID(),p.getArrivalTime(),
					p.getBurstTime(),p.getPriority(),
					p.getColor());
			Process gg = new Process(p.getID(),p.getArrivalTime(),
					p.getBurstTime(),p.getPriority(),
					p.getColor());
			process.add(g);
			sort.add(gg);
		}

		ArrayList<Process> sorted = sortProcess(sort);

		int curTime = 0;
		while(process.size() > 0) {
			int ind = curProcess(curTime,process);
			double minArr = 100000.0;
			int in = 0;
			if(ind != -1) {
				Process p = process.get(ind);

				for(int i = 0;i<sorted.size();i++) {
					Process s = sorted.get(i);
					int pd = Integer.parseInt(p.getID());
					int sd = Integer.parseInt(s.getID());
					if(sd == pd) {
						in = i;
						break;
					}
					if(s.getArrivalTime() < minArr) {

						minArr = s.getArrivalTime();

					}
				}

				Process g = new Process(p.getID(),p.getArrivalTime(),
						p.getBurstTime(),p.getPriority(),
						p.getColor());
				g.setStartTime(curTime);

				if(minArr >= ((double)curTime+p.getBurstTime())) {
					g.setDur(p.getBurstTime());
					curTime = (int) (curTime+p.getBurstTime());
					sorted.remove(in);
					process.remove(ind);

				}else {
					double stay = minArr - curTime;
					g.setDur(stay);
					process.get(ind).setBurstTime(p.getBurstTime() - stay);
					curTime = (int) (curTime+stay);
				}
				ans.add(g);
			}else curTime++;
		}
		return ans;
	}
	private static int curProcess(int timer, ArrayList<Process> data) {
		int priority = 100000,ans = -1;
		for(int i = 0;i<data.size();i++) {
			Process p = data.get(i);
			if(p.getArrivalTime() <= timer &&
					p.getPriorityNumber() < priority) {
				ans = i;
				priority = p.getPriorityNumber();
			}
		}
		return ans;
	}
	public static ArrayList<Process> sortProcess(ArrayList<Process> process){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> data = process;
		int ind = -1,max = 100000;
		double arr = 100000;
		while(data.size() > 0) {
			ind = -1;max = 100000;arr = 100000;
			for(int i=0;i<data.size();i++) {
				Process p = data.get(i);
				if(p.getPriorityNumber() < max) {
					ind = i;
					max = p.getPriorityNumber();
					arr = p.getArrivalTime();
				}else if(p.getPriorityNumber() == max && p.getArrivalTime() < arr) {
					ind = i;
					max = p.getPriorityNumber();
					arr = p.getArrivalTime();
				}
			}
			ans.add(data.get(ind));
			data.remove(ind);
		}
		return ans;
	}
	public static ArrayList<Process> modify(ArrayList<Process> data){
		ArrayList<Process> ans = new ArrayList<>();
		for(int i=0;i<data.size();i++) {
			Process p = data.get(i);
			int startTime = p.getStartTime();
			int dur = (int)p.getDur();

			for(int j = startTime;j<(dur+startTime);j++) {
				Process g = new Process(p.getID(),p.getArrivalTime(),
						p.getBurstTime(),p.getPriority(),
						p.getColor());
				g.setStartTime(j);
				g.setDur(1);
				ans.add(g);
			}
		}
		return ans;
	}
	public static double waitingTime(ArrayList<Process> data) {
		double totalWaitingTime = 0.0;
		HashMap<Integer, Double> waitingTimeMap = new HashMap<>(); // map to keep track of the total waiting time for each process
		HashMap<Integer, Integer> countMap = new HashMap<>(); // map to keep track of the number of parts for each process
		HashMap<Integer, Double> prevEndTimeMap = new HashMap<>(); // map to keep track of the end time of the previous part for each process
		for (Process p : data) {
			int id = Integer.parseInt(p.getID());
			double arrivalTime = prevEndTimeMap.getOrDefault(id, p.getArrivalTime());
			double startTime = p.getStartTime();
			double duration = p.getDur();
			double waitingTime = Math.max(0, startTime - arrivalTime); // calculate the waiting time for the current part
			if (countMap.containsKey(id)) { // if the process has already started, add the waiting time of the current part to the total waiting time
				waitingTime += waitingTimeMap.get(id);
			}
			waitingTimeMap.put(id, waitingTime); // update the total waiting time for the current process in the map
			countMap.put(id, countMap.getOrDefault(id, 0) + 1); // update the number of parts for the current process in the map
			prevEndTimeMap.put(id, startTime + duration); // update the end time of the previous part for the current process
			totalWaitingTime += waitingTime;
		}
		double numProcesses = waitingTimeMap.keySet().size();
		double avgWaitingTime = totalWaitingTime / numProcesses;
		return avgWaitingTime;
	}

	public static double turnAround(ArrayList<Process> data) {
		HashMap<Integer, Double> turnaroundTimes = new HashMap<>(); // map to keep track of the turnaround time for each process
		double totalTurnaroundTime = 0.0; // variable to keep track of the total turnaround time
		for (Process p : data) {
			int id = Integer.parseInt(p.getID());
			double arrivalTime = p.getArrivalTime();
			double startTime = p.getStartTime();
			double duration = p.getDur();
			double turnaroundTime = duration + Math.max(0, startTime - arrivalTime); // calculate the turnaround time for the current process
			if (turnaroundTimes.containsKey(id)) { // if we've already seen this process, replace its turnaround time with the new one
				turnaroundTimes.put(id, turnaroundTime);
			} else { // otherwise, add it to the map
				turnaroundTimes.put(id, turnaroundTime);
			}
		}
		for (double turnaroundTime : turnaroundTimes.values()) { // loop through the turnaround times and add them up
			totalTurnaroundTime += turnaroundTime;
		}
		double numProcesses = turnaroundTimes.keySet().size(); // calculate the number of processes
		double avgTurnaroundTime = totalTurnaroundTime / numProcesses; // calculate the average turnaround time
		return avgTurnaroundTime;
	}
	public int TotalBurst(ArrayList<Process> data){
		int burst=0;
		for(int i=0; i<data.size();i++){
			burst= (int) (burst+(int)data.get(i).getDur());
		}
		System.out.println("burst"+burst);
		return burst;

	}

}
