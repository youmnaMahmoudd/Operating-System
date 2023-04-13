package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ActionController  implements Initializable {
	SampleController x;
	@FXML	Label nameLabel;
	@FXML TableView<process> tableview;
	@FXML TableColumn<process,String> AT;
	@FXML TableColumn<process,String> P;
	@FXML TableColumn<process,String> BT;
	@FXML TableColumn<process,String> ID;
	@FXML TableColumn<process,Color> C;
    @FXML TextField ATtext;
	@FXML TextField BTtext;
	@FXML TextField Ptext;
	@FXML ColorPicker chooserColor;
	@FXML Label QV;
	@FXML Label QT;
	   private Stage stage;
	   private Scene scene;
	   private Parent root;

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
	public void Add() {
		process p;
		if(x.type==3) {
			 p=new process(ATtext.getText(),BTtext.getText(),chooserColor.getValue());
		}
		else {
			 p=new process(Ptext.getText(),ATtext.getText(),BTtext.getText(),chooserColor.getValue());
		}
        tableview.getItems().add(p);

	}
	 public ObservableList<process>  getprocesses()
	    {
	        ObservableList<process> processes = FXCollections.observableArrayList();
	      
	        return processes;
	    }
	  @Override
	    public void initialize(URL url, ResourceBundle rb) {
		  AT.setCellValueFactory(new PropertyValueFactory<process, String>("arrivalTime"));
	        BT.setCellValueFactory(new PropertyValueFactory<process, String>("currentBurst"));
	        P.setCellValueFactory(new PropertyValueFactory<process, String>("priority"));
	        ID.setCellValueFactory(new PropertyValueFactory<process, String>("ID"));
	        C.setCellValueFactory(new PropertyValueFactory<process, Color>("color"));
	        //load dummy data
	        tableview.setItems(getprocesses());
	        
	    }
	
}
