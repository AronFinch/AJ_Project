package view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.Task;	

	public class ControllerDialogTask implements Initializable {
		
	@FXML
	private FlowPane PaneMiniTask;
	
	private Stage dialogStage;
	static Target target = null;
	
	@FXML
    public void NewTask(ActionEvent actionEvent) throws IOException{
		
		
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
        
		stage.showAndWait();
		Inic();
	}

	public void SetTarget(Target target) {
		// TODO Auto-generated method stub
		this.target = target;
	}
	
    public void SetDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		Inic();
	 
	}
	
	public void Inic(){
		
		PaneMiniTask.getChildren().clear();
		
		Iterator<Task> itr = target.TaskList.iterator();
		while (itr.hasNext()) {
			ControllerMiniTask.task = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("miniTask.fxml"));
					 	Parent root = loader.load();
					 	PaneMiniTask.getChildren().add(root);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		 		 	

		}
		
	}
		
}