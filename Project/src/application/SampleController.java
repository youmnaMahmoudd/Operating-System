package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
public class SampleController {

 private Stage stage;
 private Scene scene;
 private Parent root;
@FXML TableView<process> tableview;
@FXML TableColumn<process,Integer> AT;
@FXML TableColumn<process,Integer> P;
@FXML TableColumn<process,Integer> BT;


 public void switchToScene1(ActionEvent event) throws IOException {
  root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 public void SJF(ActionEvent event) throws IOException{

	//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="SJF";
		scene2Controller.displayName(username);
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
 }
 public void NPSJF(ActionEvent event) throws IOException{

	//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="NP-SJF";
		scene2Controller.displayName(username);
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
 }
 public void FCFS(ActionEvent event) throws IOException{

	//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="FCFS";
		scene2Controller.displayName(username);
		 
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  String css = this.getClass().getResource("application.css").toExternalForm();
	   scene.getStylesheets().add(css);
	  stage.setScene(scene);
	  stage.show();
 }
 public void Priority(ActionEvent event) throws IOException{

	//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="Priority";
		scene2Controller.displayName(username);
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
 }
 public void PriorityNP(ActionEvent event) throws IOException{

	//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
	  FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="NP-Priority";
		scene2Controller.displayName(username);
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
 }
 public void switchToScene2(ActionEvent event) throws IOException {
  Parent root = FXMLLoader.load(getClass().getResource("Sample1.fxml"));
  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
  scene = new Scene(root);
  stage.setScene(scene);
  stage.show();
 }
 public void RR(ActionEvent event) throws IOException {
	 FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));	
		root = loader.load();	
		
		ActionController scene2Controller = loader.getController();
		String username="Round Robin";
		scene2Controller.displayName(username);
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
}