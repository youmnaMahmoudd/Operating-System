package application;

import java.util.ArrayList;

public class Things {

	int start;
	int end;
	String color;
	String name;


 

  

public Things() {
		
		this.start = 0;
		this.end = 0;
		this.color = "";
		this.name = "";
	}
	public Things(int start, int end, String color) {
		
		this.start = start;
		this.end = end;
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
}
