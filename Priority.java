package osProject;

import java.util.ArrayList;
import java.util.HashMap;

public class Priority {
	
	public static ArrayList<Process> priorityNonPremmetive(ArrayList<Process> data){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> process = data;
		int curTime = 0;
		while(process.size() > 0) {
			int ind = curProcess(curTime,process);
			if(ind != -1) {
				Process p = process.get(ind);
				p.setCurrentBurst(String.valueOf(p.getBurstTime()));
				p.setStartTime(curTime);
				ans.add(p);
				curTime+=p.getBurstTime();
				process.remove(ind);
			}else curTime ++;
		}
		return ans;
	}
	public static ArrayList<Process> priorityPremmetive(ArrayList<Process> data){
		ArrayList<Process> ans = new ArrayList<>();
		ArrayList<Process> process = data;

		int curTime = 0,curProcess = 0,prevInd = -1,dur = 0;
		while(process.size() > 0) {
			if(curTime != 0 && prevInd != -1) {
				Process p = process.get(prevInd);
				if(p.getBurstTime() - 1 == 0) {
					process.remove(prevInd);
				}else {
					process.get(prevInd).setBurstTime(p.getBurstTime()-1);
				}
			}
			int ind = curProcess(curTime,process);
			if(ind != -1) {
				Process p = process.get(ind);
				Process g = new Process(p.getID(),Double.parseDouble(p.getArrivaltime()),Double.parseDouble(p.getCurrentBurst()),p.getPriority(),p.getColor());
				if(curProcess != Integer.valueOf(g.getID())){
					g.setStartTime(curTime);
					if(ans.size()>0)
						ans.get(ans.size()-1).setCurrentBurst(String.valueOf(dur + 1));
					ans.add(g);
					dur = 0;
				}else dur++;
				prevInd = ind;
				curProcess = Integer.valueOf(g.getID());
			}else {
				if(process.size()==0) {
					if(ans.size()>0)
						ans.get(ans.size()-1).setCurrentBurst(String.valueOf(dur + 1));
				}
			}

			curTime++;
		}
		return ans;
	}
	private static int curProcess(int timer, ArrayList<Process> data) {
		int priority = 0,ans = -1;
		for(int i = 0;i<data.size();i++) {
			Process p = data.get(i);
			if(p.getArrivalTime() <= timer &&
			   Integer.valueOf(p.getPriority()) > priority) {
				ans = i;
				priority = Integer.valueOf(p.getPriority());
			}
		}
		//System.out.println(timer);
		return ans;
	}
	public static void main(String args[]) {
		Process p1 = new Process("2","1","3","r");
		Process p2 = new Process("3","7","4","r");
		Process p3 = new Process("6","7","2","r");
		Process p4 = new Process("1","7","3","r");
		Process p5 = new Process("7","6","1","r");
		ArrayList<Process> data = new ArrayList<>();
		data.add(p1);
		data.add(p3);
		data.add(p2);
		data.add(p5);
		data.add(p4);

		ArrayList<Process> ans = Priority.priorityNonPremmetive(data);
		for(int i = 0;i<ans.size();i++) {
			Process e = ans.get(i);
			System.out.println(e.getID() + " Start at: " + e.getStartTime() + " Dur: " + e.getCurrentBurst());
		}
	}
}
