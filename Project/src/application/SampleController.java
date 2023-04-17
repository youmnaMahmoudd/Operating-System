package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class SampleController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML RadioButton live;
	@FXML TextField Quantumvalue;
	static int Qv=0;
	static int Flive=0;

	static int type=0;//To declare the scheduler

	public void switchToScene1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	@FXML public void Liveflag() {
		Flive=1;
	}
	public void SJF(ActionEvent event) throws IOException{
		type=1;

		//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		ActionController scene2Controller = loader.getController();
		String username="SJF";
		scene2Controller.displayName(username);
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("QV.css").toExternalForm();
		scene.getStylesheets().add(css);

		stage.setScene(scene);
		stage.show();

	}
	public void NPSJF(ActionEvent event) throws IOException{
		type=2;
		//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		ActionController scene2Controller = loader.getController();
		String username="NP-SJF";
		scene2Controller.displayName(username);
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("QV.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void FCFS(ActionEvent event) throws IOException{
		type=3;
		//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		ActionController scene2Controller = loader.getController();
		String username="FCFS";
		scene2Controller.displayName(username);
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("application.css").toExternalForm();
		scene.getStylesheets().add(css);
		String css1 = this.getClass().getResource("QV.css").toExternalForm();
		scene.getStylesheets().add(css1);
		stage.setScene(scene);
		stage.show();
	}
	public void Priority(ActionEvent event) throws IOException{
		type=4;
		//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		ActionController scene2Controller = loader.getController();
		String username="Priority";
		scene2Controller.displayName(username);
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("QV.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.show();
	}
	public void PriorityNP(ActionEvent event) throws IOException{
		type=5;
		//  Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		ActionController scene2Controller = loader.getController();
		String username="NP-Priority";
		scene2Controller.displayName(username);
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		String css = this.getClass().getResource("QV.css").toExternalForm();
		scene.getStylesheets().add(css);
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
		type=6;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Action.fxml"));
		root = loader.load();

		Qv=Integer.parseInt(Quantumvalue.getText());
		ActionController scene2Controller = loader.getController();
		String username="Round Robin";
		scene2Controller.displayName(username);
		scene2Controller.displayQt(Quantumvalue.getText());
		scene2Controller.Action();
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}