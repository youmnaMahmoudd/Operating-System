package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.layout.HBox;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import application.GanttChart.ExtraData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	NumberAxis xAxis = new NumberAxis();
	CategoryAxis yAxis = new CategoryAxis();
    GanttChart<Number,String> lineChart = new GanttChart<Number,String>(xAxis,yAxis);
    ArrayList<Process> data=new ArrayList<Process>();
	 String machine =   "Processes" ;
     XYChart.Series series1 = new XYChart.Series(); 
     //Function to choose color
    public String choice() {
    	String v=choiceBox.getValue().toString();
    	return v;
    	}
    // set the gantt chart
	public void Action() throws IOException {
		

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
	//For live display
//    Thread updateThread = new Thread(() -> {
//        while (true) {
//          try {
//            Thread.sleep(2000);
//            Platform.runLater(() -> series1.getData().add(new XYChart.Data(0, machine, new ExtraData( 1, "pink"))));
//          
//          } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//          }
//        }
//      });
//      updateThread.setDaemon(true);
//      updateThread.start();	 


	   private Stage stage;
	   private Scene scene;
	   private Parent root;
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
   
   // For adding new process
	public void Add() {
		Process p;
		
		if(x.type==3) {
			 p=new Process(ATtext.getText(),BTtext.getText(),choice());
			 Scheduler fcfs = new FirstComeFirstServe();
//		        fcfs.add(new Process("green", 0, 2));
//		        fcfs.add(new Process("blue", 0, 4));
//		        fcfs.add(new Process("pink", 2, 1));
//		        fcfs.add(new Process("olive", 2, 3));
			 fcfs.add(p);
		      fcfs.process();
			 data.add(p);
			  for (int i = 0; i < fcfs.getTimeline().size(); i++)
              {
                  List<Event> timeline = fcfs.getTimeline();
                  System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
	              series1.getData().add(new XYChart.Data((int)timeline.get(i).getStartTime(), machine, new ExtraData((int)(timeline.get(i).getFinishTime()-timeline.get(i).getStartTime()), timeline.get(i).getProcessName())));

                  if (i == fcfs.getTimeline().size() - 1)
                  {
                      System.out.print(timeline.get(i).getFinishTime());
                  }
              }
			  
			  displayAvTurnAround(String.valueOf(fcfs.getAverageTurnAroundTime()));

			  displayAVwaiting(String.valueOf(fcfs.getAverageWaitingTime()));
		}
		else {
			
			 p=new Process(Ptext.getText(),ATtext.getText(),BTtext.getText(),choice());
			data.add(p);
		}
        tableview.getItems().add(p);

	}
	@FXML
	public void act() throws InterruptedException {
		 Scheduler fcfs = new FirstComeFirstServe();
	        fcfs.add(new Process("green", 0, 2));
	        fcfs.add(new Process("blue", 0, 4));
	        fcfs.add(new Process("pink", 2, 1));
	        fcfs.add(new Process("olive", 2, 3));
	       
	        fcfs.process();
	     
	        	  
	        	 // Thread.sleep(1000);
	        	  for (int i = 0; i < fcfs.getTimeline().size(); i++)
	              {
	                  List<Event> timeline = fcfs.getTimeline();
	                  System.out.print(timeline.get(i).getStartTime() + "(" + timeline.get(i).getProcessName() + ")");
		              series1.getData().add(new XYChart.Data((int)timeline.get(i).getStartTime(), machine, new ExtraData((int)(timeline.get(i).getFinishTime()-timeline.get(i).getStartTime()), timeline.get(i).getProcessName())));

	                  if (i == fcfs.getTimeline().size() - 1)
	                  {
	                      System.out.print(timeline.get(i).getFinishTime());
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
