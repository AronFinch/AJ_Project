package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.Task;

public class ControllerMiniTask implements Initializable {
	
	@FXML
	private Label Description;
	
	@FXML
	private Button ButtonOk;

	public static Stage dialogStage;
	public static Target target = new Target();
	public static Task task = new Task();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Description.setText(task.getDescription());
		if(task.getApproved()){
			ButtonOk.setDisable(true);
		}
		
	}
	
	@FXML
    public void ActionClick(ActionEvent actionEvent) throws IOException {

		//dialogStage.close();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerTargetPane.class.getResource("task.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(dialogStage);
		stage.setTitle("Задача:");
		
	 	ControllerDialogNewTask controller = loader.getController();
	 	controller.SetDialogStage(stage);
        controller.SetTarget(target);
        controller.task = task;
        
		stage.showAndWait();
		InicDialog();
	}
	
	@FXML
    public void ActionOk(ActionEvent actionEvent) throws IOException{
		
		task.setApproved(true);
		InicDialog();

	}
	
	@FXML
    private void ActionDelete() throws IOException, SQLException {
		
		target.TaskList.remove(task);
		task.delete();
		InicDialog();

    }

	private void InicDialog() throws IOException {
		// TODO Auto-generated method stub
		dialogStage.close();
		
		ControllerDialogTask.target = target; 
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerTargetPane.class.getResource("taskList.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	dialogStage = stage;
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("Задачи цели " + target.getLabel());
		
	 	ControllerDialogTask controller = loader.getController();
	 	controller.SetDialogStage(stage);
        controller.SetTarget(target);
        
		stage.show();
	}

}
