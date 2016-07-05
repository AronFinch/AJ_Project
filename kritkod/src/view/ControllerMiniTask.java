package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Task;

public class ControllerMiniTask implements Initializable {
	
	@FXML
	private Label Description;

	public static Task task;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Description.setText(task.getDescription());
		
	}
	
	@FXML
    public void ActionOk(ActionEvent actionEvent){

	}
	
	@FXML
    private void ActionDelete() {

    }
	
	

}
