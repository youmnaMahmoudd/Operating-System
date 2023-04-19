package application;

import java.util.ArrayList;

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
					double stay = (curTime+p.getBurstTime()) - minArr;
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
		int priority = 0,ans = -1;
		for(int i = 0;i<data.size();i++) {
			Process p = data.get(i);
			if(p.getArrivalTime() <= timer &&
			   p.getPriorityNumber() > priority) {
				ans = i;
				priority = p.getPriorityNumber();
			}
		}
		return ans;
	}
	public static ArrayList<Process> sortProcess(ArrayList<Process> process){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> data = process;
		int ind = -1,max = 0;
		double arr = 100000;
		while(data.size() > 0) {
			ind = -1;max = 0;arr = 100000;
			for(int i=0;i<data.size();i++) {
				Process p = data.get(i);
				if(p.getPriorityNumber() > max) {
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
	
}