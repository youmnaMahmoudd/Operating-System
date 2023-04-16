package osProject;

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
	 	public int getStartTime() {
			return startTime;
		}public void setStartTime(int startTime) {
			this.startTime = startTime;
		}
		public Process(String ID,double arrivalTime,double burstTime,String priority,String color) {
			this.ID = ID;
			this.arrivalTime = arrivalTime;
			this.burstTime = burstTime;
			this.priority = priority;
			this.color = color;
			this.priorityNumber=Integer.parseInt(priority);
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
			this.ID=" "+counter+" ";
			this.arrivaltime = arrivaltime;
			this.currentBurst = currentBurst;
		}
	    public Process(String color, double arrivalTime, double burstTime)
	    {
	    	this.ID=" "+counter+" ";
	        this.color = color;
	        this.arrivalTime = arrivalTime;
	        this.burstTime = burstTime;
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
		ID = counter+"0";
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
 
 
}