package application;

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
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ActionController  implements Initializable {

	@FXML	Label nameLabel;
	@FXML TableView<process> tableview;
	@FXML TableColumn<process,String> AT;
	@FXML TableColumn<process,String> P;
	@FXML TableColumn<process,String> BT;
	@FXML TableColumn<process,String> ID;
//	@FXML TableColumn<process,String> Color;
    @FXML  TextField ATtext;
	@FXML  TextField BTtext;
	   @FXML  TextField Ptext;
	   @FXML  TextField PID;
	 //  @FXML  Color x;

	
	public void displayName(String username) {
		nameLabel.setText(username+" scheduler" );
	}
	public void Add() {
		process p=new process(Ptext.getText(),ATtext.getText(),BTtext.getText(),PID.getText());
        tableview.getItems().add(p);
        System.out.println(ATtext.getText());

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

	        //load dummy data
	        tableview.setItems(getprocesses());
	        
	    }
	
}
