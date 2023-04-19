package osProject;

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
	public static double turnAround(ArrayList<Process> data) {
		double ans = 0.0;
		HashMap<Integer,Integer> m = new HashMap<>();
		for(int i=0;i<data.size();i++) {
			Process p = data.get(i);
			String id = p.getID();
			int startTime = p.getStartTime(); 
			int dur = (int)p.getDur();
			m.put(Integer.parseInt(id), startTime+dur - (int)p.getArrivalTime());
		}
		double num = 0;
		for(Entry<Integer, Integer> e : m.entrySet()) {
			num++;
			ans+=e.getValue();
		}
		return ans / num;
	}
	public static double waitingTime(ArrayList<Process> data) {
		double ans = 0.0;
		HashMap<Integer,Integer> m = new HashMap<>();
		HashMap<Integer,Integer> count = new HashMap<>();
		for(int i=0;i<data.size();i++) {
			Process p = data.get(i);
			String id = p.getID();
			int startTime = p.getStartTime(); 
			int dur = (int)p.getDur();
			m.put(Integer.parseInt(id), startTime+dur - (int)p.getArrivalTime());
			if(count.get(Integer.parseInt(id)) != null)
				count.put(Integer.parseInt(id), count.get(Integer.parseInt(id)) + 1);
			else count.put(Integer.parseInt(id),1);
		}
		double num = 0;
		for(Entry<Integer, Integer> e : m.entrySet()) {
			num++;
			ans+=e.getValue()-count.get(e.getKey());
		}
		return ans / num;
	}
	public static void main(String args[]) {
		Process p1 = new Process("2","0","5","r");
		Process p2 = new Process("3","0","4","r");
		Process p3 = new Process("6","4","2","r");
		Process p4 = new Process("1","7","3","r");
		Process p5 = new Process("7","2","1","r");
		/*Process p1 = new Process("2","0","0","r");
        Process p2 = new Process("3","0","0","r");
        Process p3 = new Process("6","0","0","r");
        Process p4 = new Process("1","0","0","r");*/
		ArrayList<Process> data = new ArrayList<>();
		data.add(p1);
		data.add(p2);
		data.add(p3);
		data.add(p4);
		data.add(p5);

		ArrayList<Process> ansnp = Priority.pp(data);
		for(int i = 0;i<ansnp.size();i++) {
			Process e = ansnp.get(i);
			System.out.println(e.getID() + " Start at: " + e.getStartTime() + " Dur: " + e.getDur());
			//System.out.println(e.getID());
		}
		System.out.println("----------------");
		ArrayList<Process> m = Priority.modify(ansnp);
		for(int i = 0;i<m.size();i++) {
			Process e = m.get(i);
			System.out.println(e.getID() + " Start at: " + e.getStartTime() + " Dur: " + e.getDur());
		}
		System.out.println("----------------");
		System.out.println(Priority.turnAround(m));
		System.out.println(Priority.waitingTime(m));
	}
}
