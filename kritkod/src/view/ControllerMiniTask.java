package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
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
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.Task;

public class ControllerMiniTask implements Initializable {
	
	@FXML
	private Label Description;
	
	@FXML
	private Button ButtonOk;
	
	public static Stage newDialogStage = null;
	public static Target newTarget = new Target();
	public static Task newTask = new Task();
	public  static FlowPane newPane;

	public Stage dialogStage = null;
	public Target target = new Target();
	public Task task = new Task();
	public FlowPane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Description.setText(newTask.getDescription());
		if(newTask.getApproved()){
			ButtonOk.setDisable(true);
		}
		dialogStage = newDialogStage;
		target = newTarget;
		task = newTask;
		pane = newPane;
		
	}
	
	@FXML
    public void ActionClick() throws IOException {

		//dialogStage.close();
		ControllerDialogNewTask.task = task;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Controller.class.getResource("task.fxml"));
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
		
		pane.getChildren().clear();
		
		Iterator<Task> itr = target.TaskList.iterator();
		while (itr.hasNext()) {
			ControllerMiniTask.newTask = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("miniTask.fxml"));
					 	Parent root = loader.load();
					 	
					 	pane.getChildren().add(root);
					 	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		 		 	

		}
	}

}
