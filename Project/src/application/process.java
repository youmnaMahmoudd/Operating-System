package application;

import java.util.ArrayList;

public class Process {
		static int counter = 0;
		String priority;
		String arrivaltime;	
		String currentBurst;
		String color;
		String ID;
		private int priorityNumber;
		private double arrivalTime = 0.0;
		private double burstTime = 0.0;
		private double waitingTime = 0.0;
		private double turnAroundTime = 0.0;
		private boolean isActive = false;
		private boolean isArrived = false;
		private boolean isStarted = false;
		private boolean isFinished = false;
		private int startTime = 0;
		private double dur = 0;
		
		public Process(String ID,double arrivalTime,double burstTime,String priority,String color) {
			this.ID = ID;
			this.arrivalTime = arrivalTime;
			this.burstTime = burstTime;
			this.color = color;
			this.priorityNumber=Integer.parseInt(priority);
			this.priority = priority;
			this.arrivaltime = String.valueOf(arrivalTime);
			this.currentBurst = String.valueOf(burstTime);
		}

		public Process(String ID,double arrivalTime,double burstTime,String color) {
			
			this.ID = ID;
			this.arrivalTime = arrivalTime;
			this.burstTime = burstTime;
			this.color = color;
			this.priorityNumber=Integer.parseInt(priority);
			this.priority = priority;
			this.arrivaltime = String.valueOf(arrivalTime);
			this.currentBurst = String.valueOf(burstTime);
		}

	public Process(int ID,double arrivalTime,double burstTime,String color) {

		this.ID = String.valueOf(ID);
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.color = color;
		this.arrivaltime = String.valueOf(arrivalTime);
		this.currentBurst = String.valueOf(burstTime);
	}
		public Process(String priority, String arrivaltime, String currentBurst,String c) {
			counter++;
			color=c;
			this.arrivalTime=Double.parseDouble(arrivaltime);
			this.burstTime=Double.parseDouble(currentBurst);
			this.priorityNumber=Integer.parseInt(priority);
 
			this.ID=""+counter+"";
			this.priority = priority;
			this.arrivaltime = arrivaltime;
			this.currentBurst = currentBurst;
		}
		public Process(String arrivaltime, String currentBurst,String c) {
			counter++;
			color=c;
			this.arrivalTime=Double.parseDouble(arrivaltime);
			this.burstTime=Double.parseDouble(currentBurst);
			this.ID=""+counter+"";
			this.arrivaltime = arrivaltime;
			this.currentBurst = currentBurst;
		}
	public Process(double arrivaltime, double currentBurst,String c) {
		counter++;
		color=c;
		this.arrivalTime=arrivaltime;
		this.burstTime=currentBurst;
		this.ID=""+counter+"";
		this.arrivaltime = String.valueOf(arrivaltime);
		this.currentBurst = String.valueOf(currentBurst);
	}
	    public Process(String color, double arrivalTime, double burstTime)
	    {
	    	this.ID=""+counter+"";
	        this.color = color;
	        this.arrivalTime = arrivalTime;
	        this.burstTime = burstTime;
	        
	    }
	   public void compare(ArrayList<Things> processes,int i) {
		//   System.out.println(x);
		   for(int j=0;j<i;j++) {
			   if(processes.get(j).getName().equals(this.getID())) {
		           if(this.getBurstTime()>0) 
		        	   this.setBurstTime(this.getBurstTime()-1);
				System.out.println(this.getColor() + " IN process Start at: " + this.getArrivalTime() + " BTime: " + this.getBurstTime());
			
				
				   }
		   }
		  
	   }
	
	   public void compareP(ArrayList<Process> processes,int i) {
			 
			   for(int j=0;j<i;j++) {
				   System.out.println("ID: " + processes.get(j).getID()+" Start at: " +this.getID() );
				   if(processes.get(j).getID().equals(this.getID())) {
					System.out.println(this.getColor() + " Start at: " + this.getArrivalTime() + " BTime: " + this.getBurstTime());

					   if(this.getBurstTime()>0) {
			        	   this.setBurstTime(this.getBurstTime()-1);

			           } 
				   }
			   }
			  
		   }
	
		public String getName() {
		        return ID;
		    }
 
		    public void setName(String name) {
		        ID = name;
		    }
	    public int getPriorityNumber() {
	        return priorityNumber;
	    }
 
	    public void setPriorityNumber(int priorityNumber) {
	        this.priorityNumber = priorityNumber;
	    }
 
	    public double getArrivalTime() {
	        return arrivalTime;
	    }
 
	    public void setArrivalTime(double arrivalTime) {
	        this.arrivalTime = arrivalTime;
	    }
 
	    public double getBurstTime() {
	        return burstTime;
	    }
 
	    public void setBurstTime(double burstTime) {
	        this.burstTime = burstTime;
	    }
 
	    public double getWaitingTime() {
	        return waitingTime;
	    }
 
	    public void setWaitingTime(double waitingTime) {
	        this.waitingTime = waitingTime;
	    }
 
	    public double getTurnAroundTime() {
	        return turnAroundTime;
	    }
 
	    public void setTurnAroundTime(double turnAroundTime) {
	        this.turnAroundTime = turnAroundTime;
	    }
 
	    public boolean isActive() {
	        return isActive;
	    }
 
	    public void setActive(boolean active) {
	        isActive = active;
	    }
 
	    public boolean isArrived() {
	        return isArrived;
	    }
 
	    public void setArrived(boolean arrived) {
	        isArrived = arrived;
	    }
 
	    public boolean isStarted() {
	        return isStarted;
	    }
 
	    public void setStarted(boolean started) {
	        isStarted = started;
	    }
 
	    public boolean isFinished() {
	        return isFinished;
	    }
 
	    public void setFinished(boolean finished) {
	        isFinished = finished;
	    }
 
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = counter+"";
	}
 
 
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
 
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getArrivaltime() {
		return arrivaltime;
	}
	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}
	public String getCurrentBurst() {
		return currentBurst;
	}
	public void setCurrentBurst(String currentBurst) {
		this.currentBurst = currentBurst;
	}
 	public int getStartTime() {
		return startTime;
	}public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
 	public double getDur() {
		return dur;
	}public void setDur(double dur) {
		this.dur = dur;
	}
 
 
}