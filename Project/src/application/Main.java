package application;
	


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
 
 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Processes Scheduler");
   stage.show();
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 }}