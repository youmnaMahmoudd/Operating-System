package application;

import javafx.scene.paint.Color;

public class process {
	static int counter = 0;
	String priority;
	String arrivalTime;	
	String currentBurst;
	Color color;
	String ID;
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = counter+"0";
	}

	public process(String priority, String arrivalTime, String currentBurst,Color c) {
		counter++;
		color=c;
		this.ID=" "+counter+" ";
		this.priority = priority;
		this.arrivalTime = arrivalTime;
		this.currentBurst = currentBurst;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public process(String arrivalTime, String currentBurst,Color c) {
		counter++;
		color=c;
		this.ID=" "+counter+" ";
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
