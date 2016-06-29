package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
	
	 @FXML
	    private Button buttonNewTask;
	 
	 @FXML
	static ScrollPane ScrollPane0;
	 
	 @FXML
	    public void ShowDialog(ActionEvent actionEvent) throws IOException{
		 	
		 	Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialog1.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
			stage.setTitle("Создать цель:");
			stage.show();
	    }
	 
	 public static void NewPaneTarget (Target target){
		/*
		   Parent root = FXMLLoader.load(getClass().getResource("scrollPaneNext.fxml"));
		 
		 Controller.ScrollPane0.getChildrenUnmodifiable();
		 */
	 }

}
