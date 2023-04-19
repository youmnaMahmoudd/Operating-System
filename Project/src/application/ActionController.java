package application;
import javafx.concurrent.Task;
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
	SJF sjf=new SJF();
	ArrayList<Process> shortestJobFirst=new ArrayList<Process>();
	RoundRobin roundRobin = new RoundRobin(x.getQv());
	ArrayList<Things> RRprocess = new ArrayList<Things>();
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
		nameLabel1.setText(username );
	}
	public void displayAvTurnAround(String username) {
		nameLabel2.setText(username );
	}
	public void displayQt(String username) {
		QV.setText(username );
	}


	//Add new process to table
	public void Add() throws IOException, InterruptedException {
	    Process p;

	    if(x.type==1||x.type==2||x.type==4||x.type==5) {
	    p=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
	 //   Process p2=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
        data.add(p);
	    tableview.getItems().add(p);
	    if(elapsed>0)   {
	    	   timeline.stop();
	    
	    } 
	    }else if(x.type==3) {
	        p=new Process(ATtext.getText(),BTtext.getText(),choice());
	        if(!fcfs.getRows().contains(p))
	            fcfs.add(p);
	        Platform.runLater(() -> {
	            tableview.getItems().add(p);
	        });
	    }

	    //Round Robin
	    else if(x.type==6) {
	        p=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
	        Process p2=new Process(choice(),Double.parseDouble(ATtext.getText()),Double.parseDouble(BTtext.getText()));
	        System.out.println(roundRobin.getProcesses().size());
	        data.add(p2);
   	        roundRobin.SetQ(x.getQv());
	        roundRobin.getProcesses().add(p);
	        tableview.getItems().add(p);
	       if(elapsed>0)   {
	    	   timeline.stop();
	    //	  roundRobin = new RoundRobin(x.getQv());
	    	   for(int i=0;i<data.size()-1;i++) {
	    		 data.get(i).compare(RRprocess,elapsed);  
	    		roundRobin.getProcesses().add( data.get(i)); 
	    	   }
	    	   for(int i=elapsed;i<RRprocess.size();i++) {
	    		   RRprocess.get(i).setEnd(0);
	    		   RRprocess.get(i).setStart(0);
	    	   }
	    	
	   
		    	  roundRobin.setTimer(elapsed+1);
		    	  elapsed= RRprocess.size();
	    	 
	    	   
	       } 
	       
	    }
	}

	ArrayList<Process> priority=new ArrayList<Process>();
	//Basic Display process on chart function
	public void Basicact(int n) throws InterruptedException{
		if(x.type==1||x.type==2) {
			series1 = new XYChart.Series();
			for (int i = 0; i < n; i++)
			{
				Process e = shortestJobFirst.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, shortestJobFirst.get(i).getColor())));
				
					if(!lineChart.getData().contains(series1))
						lineChart.getData().addAll(series1);
				
				
					System.out.println(e.getColor() + " Start at: " + e.getStartTime() + " Dur: " + e.getCurrentBurst());
				
				}
		}
		else if(x.type==3) {
			for (int i = 0; i < n; i++)
			{
				series1 = new XYChart.Series();
				List<Event> timeline = fcfs.getTimeline();
				series1.getData().addAll(new XYChart.Data((int)timeline.get(i).getStartTime(), machine, new ExtraData((int)(timeline.get(i).getFinishTime()-timeline.get(i).getStartTime()), timeline.get(i).getProcessName())));
				lineChart.getData().addAll(series1);

				if (i == n - 1) {System.out.println("finish"+timeline.get(i).getFinishTime());}
			}
//			fcfs.getRows().clear();
//
//			//Still need update
//			displayAvTurnAround(String.valueOf(fcfs.getAverageTurnAroundTime()));
//			displayAVwaiting(String.valueOf(fcfs.getAverageWaitingTime()));
		}
		else if(x.type==4) {
			series1 = new XYChart.Series();
			for (int i = 0; i < n; i++)
			{
				Process e = priority.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, priority.get(i).getColor())));
				
					if(!lineChart.getData().contains(series1))
						lineChart.getData().addAll(series1);

				}
		}
		else if(x.type==5) {
			series1 = new XYChart.Series();
			for (int i = 0; i < n; i++)
			{
				Process e = priority.get(i);
				int x=(int) e.getDur();
				series1.getData().addAll(new XYChart.Data(e.getStartTime(), machine,new ExtraData(x, priority.get(i).getColor())));
			
					if(!lineChart.getData().contains(series1))
						lineChart.getData().addAll(series1);
				
					
				System.out.println(e.getColor() + " Start at: " + e.getStartTime() + " Btime: " + e.getBurstTime());
				
				}
			}
		//RR
		else if(x.type==6) {
			series1 = new XYChart.Series();
			for (int i = 0; i < n; i++) {
				int c=RRprocess.get(i).end-RRprocess.get(i).start;
			
				series1.getData().addAll(new XYChart.Data(RRprocess.get(i).getStart(), machine,new ExtraData( c, RRprocess.get(i).color)));
				
					if(!lineChart.getData().contains(series1))
						lineChart.getData().addAll(series1);
				
			System.out.println( elapsed+" <-The output time "+RRprocess.get(i).start);			
					
			}

			
		//	displayAvTurnAround(String.valueOf(roundRobin.getAverageTurnAroundTime()));
		}

	}


	@FXML
	//Man function to display dynamic and static charts.
	public void start() throws InterruptedException {
		
	    
		if(x.type==1) {
	    	
	    	shortestJobFirst=sjf.shortestJobFirstPreemptive(data);
	    	
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
	    	
	    	shortestJobFirst=sjf.shortestJobFirstNonPreemptive(data);
	    	
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
	        Task<Void> task = new Task<Void>() {
	            @Override
	            protected Void call() throws Exception {
	                //if not live (static): invoke the function once with all data.
	                if (Flive == 0) {
	                    fcfs.process();
	                    Basicact(fcfs.getTimeline().size());
	                }
	                // if Live (dynamic): invoke the function every time unit and update it.
	                else {
	                    //Time passed (to be displayed)
	                    int elapsed = 0;
	                    fcfs.process();
	                    while (elapsed < fcfs.getTimeline().size()) {
	                        elapsed++;
	                        //To be put in the thread
	                        final int elapsedFinal = elapsed;
	                        Platform.runLater(() -> {
	                            try {
	                                Basicact(elapsedFinal);
	                            } catch (InterruptedException e) {
	                                throw new RuntimeException(e);
	                            }
	                        });
	                        //Wait one second between each
	                        Thread.sleep(1000);
	                    }
	                }
	                return null;
	            }
	        };
	        Thread thread = new Thread(task);
	        thread.setDaemon(true);
	        thread.start();
	    }
	    else if(x.type==4) {
	    	Priority P=new Priority();
	    	priority=Priority.pp(data);
	    	
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
	    	Priority P=new Priority();
	    	priority=Priority.priorityNonPremmetive(data);
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
	    
	    //Round Robin
	    else if(x.type==6) {
	    	 if (Flive == 0) {
	    	//RRprocess=new ArrayList<Things>();
	    	RRprocess=roundRobin.execute();
           Basicact(RRprocess.size());}
	    	 else {
                RRprocess= roundRobin.execute();
              
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