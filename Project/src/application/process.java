package application;

public class process {
	static int counter = 0;
	String priority;
	String arrivalTime;	
	String currentBurst;
	String color;
	String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public process(String priority, String arrivalTime, String currentBurst,String ID) {
		counter++;
		this.ID=ID;
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.currentBurst = currentBurst;
	}
	public process(String arrivalTime, String currentBurst) {
		counter++;
		this.arrivalTime = arrivalTime;
		this.currentBurst = currentBurst;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getCurrentBurst() {
		return currentBurst;
	}
	public void setCurrentBurst(String currentBurst) {
		this.currentBurst = currentBurst;
	}
	
	
}
