package application;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.HBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.GanttChart.ExtraData;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


public class ActionController  implements Initializable {
	SampleController x;
	@FXML	Label nameLabel;
	@FXML	Label nameLabel1;
	@FXML	Label nameLabel2;
	@FXML  Label  AvgT1;
	@FXML TableView<Process> tableview;
	@FXML TableColumn<Process,String> AT;
	@FXML TableColumn<Process,String> P;
	@FXML TableColumn<Process,String> BT;
	@FXML TableColumn<Process,String> ID;
	@FXML TableColumn<Process,String> C;
	@FXML TextField ATtext;
	@FXML TextField BTtext;
	@FXML TextField Ptext;
	@FXML ColorPicker chooserColor;
	@FXML ComboBox choiceBox;
	@FXML Label QV;
	@FXML Label QT;
	@FXML Label pr;
	@FXML  HBox hbox = new HBox();
	Timeline timeline= new Timeline();
	private int elapsed = 0;
	Priority pro=new Priority();

	private Stage stage;
	private Scene scene;
	private Parent root;
	static int Flive= SampleController.Flive;
	NumberAxis xAxis = new NumberAxis();
	CategoryAxis yAxis = new CategoryAxis();
	GanttChart<Number,String> lineChart = new GanttChart<Number,String>(xAxis,yAxis);
	ArrayList<Process> data=new ArrayList<Process>();
	String machine =   "Processes" ;
	XYChart.Series series1 = new XYChart.Series();
	Scheduler fcfs = new FirstComeFirstServe();

	Scheduler fcfs2 = new FirstComeFirstServe();
	SJF sjf=new SJF();
	ArrayList<Process> shortestJobFirst=new ArrayList<Process>();
	RoundRobin roundRobin = new RoundRobin(x.getQv());
	ArrayList<Things> RRprocess = new ArrayList<Things>();
	ArrayList<Process> AV=new ArrayList<Process>();
	//To compute time right
	ArrayList<Process> TimeAV=new ArrayList<Process>();

	//Function to choose color

	public String choice() {
		String v=choiceBox.getValue().toString();
		return v;
	}
	// set the gantt chart
	public void Action() throws IOException {
		xAxis = new NumberAxis();
		yAxis = new CategoryAxis();
		lineChart = new GanttChart<Number,String>(xAxis,yAxis);
		machine =   "Processes" ;
		XYChart.Series series1 = new XYChart.Series();
		Color paint = new Color(0.5059, 0.6471, 0.7725, 1.0);

		xAxis.setLabel("");
		xAxis.setTickLabelFill(paint);
		xAxis.setMinorTickCount(1);

		yAxis.setLabel("");
		yAxis.setTickLabelFill(paint);
		yAxis.setTickLabelGap(10);
		yAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(machine)));
		lineChart.setLegendVisible(false);
		lineChart.setBlockHeight( 50);
		lineChart.getData().addAll(series1);
		lineChart.getStylesheets().add(getClass().getResource("ganttchart.css").toExternalForm());
		lineChart.setLayoutX(-1);
		lineChart.setLayoutY(0);
		hbox.getChildren().addAll(lineChart);

	}

	//To return to the start page
	public void switchToScene1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void displayName(String username) {
		nameLabel.setText(username+" scheduler" );
	}
	public void displayAVwaiting(String username) {
		nameLabel2.setText(username );
	}

	public void displayElapsed(String username) {
		AvgT1.setText(username );
	}
	public void displayAvTurnAround(String username) {
		nameLabel1.setText(username );
	}
	public void displayQt(String username) {
		QV.setText(username );
	}


	//Add new process to table
	public void Add() throws IOException, InterruptedException {
		Process p;

		if(x.type==1) {
			p=new Process("0",ATtext.getText(),BTtext.getText(),choice());
			//(String ID,double arrivalTime,double burstTime,String priority,String color)
			Process p2=new Process(p.getID(),p.getArrivalTime(),p.getBurstTime(),p.getPriority(),choice());
			AV.add(p2);
			data.add(p);
			tableview.getItems().add(p);
			if(elapsed>0)   {
				timeline.stop();
				for(int i=0;i<data.size();i++) {
					data.get(i).compareP(shortestJobFirst,elapsed);
					int f=(int) (shortestJobFirst.get(elapsed).getStartTime());
					if(data.get(i).getArrivalTime()<f)
						data.get(i).setArrivalTime(f);
				}
				elapsed=0;
			}
		}else if(x.type==2) {
			p=new Process("0",ATtext.getText(),BTtext.getText(),choice());
			Process p2=new Process(p.getID(),p.getArrivalTime(),p.getBurstTime(),p.getPriority(),choice());
			AV.add(p2);
			data.add(p);
			tableview.getItems().add(p);
			if(elapsed>0)   {
				timeline.stop();
				int pos=0;
				for(int i=0;i<data.size();i++) {

					data.get(i).compareP(shortestJobFirst,elapsed);
					int f=(int) (shortestJobFirst.get(elapsed).getStartTime());
//					if(data.get(i).getArrivalTime()<f)
//						data.get(i).setArrivalTime(f);
					if(shortestJobFirst.get(elapsed-1).getID().equals(data.get(i).getID())) {
						data.get(i).setArrivalTime(f);
						pos=(int)data.get(i).getBurstTime();
					}
					else {
						f=(int) (shortestJobFirst.get(elapsed).getStartTime()+pos);
						if(data.get(i).getArrivalTime()<f) {
							data.get(i).setArrivalTime(f);
						}
					}
				}
				elapsed=0;
			}
		}else if(x.type==3) {
			p=new Process(ATtext.getText(),BTtext.getText(),choice());
			if(!fcfs.getRows().contains(p))
				fcfs.add(p);
			tableview.getItems().add(p);
		}
		else if(x.type==4) {
			p=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
			Process p2=new Process(p.getID(),p.getArrivalTime(),p.getBurstTime(),p.getPriority(),choice());
			AV.add(p2);
			data.add(p);
			tableview.getItems().add(p);
			if(elapsed>0)   {
				timeline.stop();
				for(int i=0;i<data.size();i++) {
					data.get(i).compareP(priority,elapsed);
					int f=(int) (priority.get(elapsed).getStartTime());
					if(data.get(i).getArrivalTime()<f)
						data.get(i).setArrivalTime(f);
				}
				elapsed=0;
			}
		}
		else if(x.type==5) {
			p=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
			Process p2=new Process(p.getID(),p.getArrivalTime(),p.getBurstTime(),p.getPriority(),choice());
			AV.add(p2);
			data.add(p);
			tableview.getItems().add(p);
			if(elapsed>0)   {
				timeline.stop();
				int pos=0;
				for(int i=0;i<data.size();i++) {

					data.get(i).compareP(priority,elapsed);
					int f=(int) (priority.get(elapsed).getStartTime());
//						if(data.get(i).getArrivalTime()<f)
//							data.get(i).setArrivalTime(f);
					if(priority.get(elapsed-1).getID().equals(data.get(i).getID())) {
						data.get(i).setArrivalTime(f);
						pos=(int)data.get(i).getBurstTime();
					}
					else {
						f=(int) (priority.get(elapsed).getStartTime()+pos);
						if(data.get(i).getArrivalTime()<f) {
							data.get(i).setArrivalTime(f);
						}
					}
				}
				elapsed=0;
			}
		}
		//Round Robin
		else if(x.type==6) {
			p=new Process("0",ATtext.getText(),BTtext.getText(),choice());
			Process p2=new Process(p.getID(),p.getArrivalTime(),p.getBurstTime(),p.getPriority(),choice());
			System.out.println(roundRobin.getProcesses().size());
			AV.add(p2);
			data.add(p);
			roundRobin.SetQ(x.getQv());

			roundRobin.setProcesses(data);
			tableview.getItems().add(p);
			if(elapsed>0)   {
				timeline.stop();
//				int pos=x.getQv();
//				String idpos=RRprocess.get(elapsed-1).getName();
//				int s=1;
//				while(RRprocess.get(elapsed-1).getName().equals(RRprocess.get(elapsed-s).getName())) {
//					s++;pos--;
//				}
//				System.out.println("The pos and S "+pos+" "+s+" "+RRprocess.get(elapsed-1).getColor());
				for(int i=0;i<data.size();i++) {
					data.get(i).compare(RRprocess,elapsed);
					int f=(int) (RRprocess.get(elapsed).getStart());

					if(data.get(i).getArrivalTime()<f) {
						data.get(i).setArrivalTime(f);
					}


				}

				//	roundRobin.SetQ(x.getQv());
				roundRobin = new RoundRobin(x.getQv());
				roundRobin.setProcesses(data);
				//	roundRobin.setTimer(0);
				elapsed=0;


			}

		}
	}

	ArrayList<Process> priority=new ArrayList<Process>();

	//Basic Display process on chart function
	public void Basicact(int n) throws InterruptedException{
		if(x.type==1||x.type==2) {
			series1 = new XYChart.Series();
			int el=1;
			for (int i = 0; i < n; i++)
			{   displayElapsed(String.valueOf(el));
				Process e = shortestJobFirst.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, shortestJobFirst.get(i).getColor())));

				if(!lineChart.getData().contains(series1))
					lineChart.getData().addAll(series1);
				el++;
			}

			this.displayAvTurnAround(String.valueOf(sjf.turnAround(TimeAV)));
			this.displayAVwaiting(String.valueOf(sjf.waitingTime(TimeAV)));
		}

		else if(x.type==3) {
			series1 = new XYChart.Series();
			List<Event> timeline = fcfs2.getTimeline();
			int e=1;
			System.out.println("size of time line before Acsses"+fcfs.getTimeline().size());
			for (int i = 0; i < n; i++)
			{
				displayElapsed(String.valueOf(e));
				series1.getData().addAll(new XYChart.Data((int)timeline.get(i).getStartTime(), machine, new ExtraData((int)(timeline.get(i).getFinishTime()-timeline.get(i).getStartTime()),fcfs.getRow(timeline.get(i).getProcessName()).color)));
				if(!lineChart.getData().contains(series1)) lineChart.getData().addAll(series1);
				if (i == n - 1) {System.out.println("finish"+timeline.get(i).getFinishTime());}
				e++;
			}
			displayAvTurnAround(String.valueOf(fcfs.getAverageTurnAroundTime()));
			displayAVwaiting(String.valueOf(fcfs.getAverageWaitingTime()));
			if (timeline.size() == n) timeline.clear();

		}

		else if(x.type==4) {
			series1 = new XYChart.Series();
			int el=1;
			for (int i = 0; i < n; i++)
			{   displayElapsed(String.valueOf(el));
				Process e = priority.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, priority.get(i).getColor())));

				if(!lineChart.getData().contains(series1))
					lineChart.getData().addAll(series1);
				System.out.println(e.getColor() + " Start at: " + e.getStartTime() + " Btime: " + e.getBurstTime());
				el++;
			}
			this.displayAvTurnAround(String.valueOf(pro.turnAround(TimeAV)));
			this.displayAVwaiting(String.valueOf(pro.waitingTime(TimeAV)));

		}
		else if(x.type==5) {
			series1 = new XYChart.Series();
			int el=1;
			for (int i = 0; i < n; i++)
			{   displayElapsed(String.valueOf(el));
				Process e = priority.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, priority.get(i).getColor())));

				if(!lineChart.getData().contains(series1))
					lineChart.getData().addAll(series1);


				System.out.println(e.getColor() + " Start at: " + e.getStartTime() + " Btime: " + e.getBurstTime());
				el++;
			}
			this.displayAvTurnAround(String.valueOf(pro.turnAround(TimeAV)));
			this.displayAVwaiting(String.valueOf(pro.waitingTime(TimeAV)));
		}
		//RR
		else if(x.type==6) {
			series1 = new XYChart.Series();
			int el=1;
			for (int i = 0; i < n; i++) {
				displayElapsed(String.valueOf(el));
				int c=RRprocess.get(i).end-RRprocess.get(i).start;

				series1.getData().addAll(new XYChart.Data(RRprocess.get(i).getStart(), machine,new ExtraData( c, RRprocess.get(i).color)));

				if(!lineChart.getData().contains(series1))
					lineChart.getData().addAll(series1);
				el++;
			}
			this.displayAvTurnAround(String.valueOf(roundRobin.getAverageTurnAroundTime()));
			this.displayAVwaiting(String.valueOf(roundRobin.getAverageWaitingTime()));
		}

	}

	@FXML
	//Man function to display dynamic and static charts.
	public void start() throws InterruptedException {
		data.clear();
		data.addAll(AV);
		if(x.type==1) {
			shortestJobFirst=new ArrayList<Process>();
			shortestJobFirst=sjf.modify(sjf.shortestJobFirstPreemptive(data));
			TimeAV=sjf.shortestJobFirstPreemptive(data);
			sjf.print(shortestJobFirst);
			if(Flive==0) {
				Basicact(shortestJobFirst.size());
			} else {


				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{

					if(elapsed < shortestJobFirst.size()) {	 try {
						Basicact(++elapsed);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}

		}
		else if(x.type==2) {
			shortestJobFirst=new ArrayList<Process>();
			shortestJobFirst=sjf.modify(sjf.shortestJobFirstNonPreemptive(data));
			TimeAV=sjf.shortestJobFirstNonPreemptive(data);
			sjf.print(shortestJobFirst);
			if(Flive==0) {
				Basicact(shortestJobFirst.size());
			} else {


				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{

					if(elapsed < shortestJobFirst.size()) {	 try {
						Basicact(++elapsed);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}

		}

		//FCFS
		else if(x.type==3) {
			//if not live (static): invoke the function once with all data.
			if (Flive == 0) {
				System.out.println("rows"+fcfs.getRows().size());
				fcfs2.Modify(fcfs.getRows());
				fcfs2.process();
				fcfs.process();
				Basicact(fcfs2.getTimeline().size());


			}

			// if Live (dynamic): invoke the function every time unit and update it.
			else {
				fcfs2.Modify(fcfs.getRows());
				fcfs2.process();
				fcfs.process();
				System.out.println("\n"+fcfs2.getRows().size()+"time line"+fcfs2.getTimeline().size());
				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{
					if(elapsed < fcfs2.getTimeline().size()) {	 try {
						System.out.println(elapsed);
						System.out.println("size"+fcfs.getTimeline().size());
						Basicact(++elapsed);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();

			}

		}
		else if(x.type==4) {
			priority=new ArrayList<Process>();
			priority=pro.modify(pro.pp(data));
			TimeAV=pro.pp(data);
			System.out.println("The size of array"+priority.size());
			if(Flive==0) {
				Basicact(priority.size());

			} else {


				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{

					if(elapsed < priority.size()) {	 try {
						Basicact(++elapsed);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}

		}
		else if(x.type==5) {
			priority=new ArrayList<Process>();
			priority=pro.modify(pro.priorityNonPremmetive(data));
			TimeAV=pro.priorityNonPremmetive(data);
			if(Flive==0) {
				Basicact(priority.size());
				priority.clear();
			} else {


				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{

					if(elapsed < priority.size()) {	 try {
						Basicact(++elapsed);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}
		}

		//Round Robin
		else if(x.type==6) {

			RRprocess=new ArrayList<Things>();
			RRprocess=roundRobin.modify(roundRobin.execute());
			roundRobin.print();
			if (Flive == 0) {
				//RRprocess=new ArrayList<Things>();

				Basicact(RRprocess.size());}
			else {


				timeline = new Timeline(new KeyFrame(Duration.seconds(1), e ->{

					if(elapsed < RRprocess.size()) {	 try {
						Basicact(++elapsed);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					}}));
				timeline.setCycleCount(Animation.INDEFINITE);
				timeline.play();
			}
		}
	}

	// Both for setting the Table view
	public ObservableList<Process>  getprocesses()
	{
		ObservableList<Process> processes = FXCollections.observableArrayList();

		return processes;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		choiceBox.getItems().addAll("red", "green", "blue","yellow","pink","olive");

		AT.setCellValueFactory(new PropertyValueFactory<Process, String>("arrivaltime"));
		BT.setCellValueFactory(new PropertyValueFactory<Process, String>("currentBurst"));
		P.setCellValueFactory(new PropertyValueFactory<Process, String>("priority"));
		ID.setCellValueFactory(new PropertyValueFactory<Process, String>("ID"));
		C.setCellValueFactory(new PropertyValueFactory<Process, String>("color"));
		//load dummy data
		tableview.setItems(getprocesses());
	}

}