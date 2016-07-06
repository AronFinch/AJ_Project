package view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Target;
import model.Task;

public class ControllerDialogNewTask implements Initializable {
	
	@FXML
    private DatePicker DataStart;
	
	@FXML
    private DatePicker DataFail;
	
	
	@FXML
    private TextField Level;
	
	@FXML
	private TextArea Discription;
	
	private Stage dialogStage;
	Target target = null;
	static Task task = new Task();
	
    public void SetDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	public void SetTarget(Target target) {
		// TODO Auto-generated method stub
		this.target = target;
		
	}
	
	
	@FXML
    public void ActionOk(ActionEvent actionEvent){
		target.TaskList.remove(task);
		Task task = new Task();
		task.setDescription(Discription.getText());
		task.setStartDate(DataStart.getValue());
		task.setEndDate(DataFail.getValue());
		task.setLevel(Integer.parseUnsignedInt(Level.getText()));
		try {
			if(target.createTask(task)) {
				// задача успешно создана
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
	    	    alert.setHeaderText("Почему-то не удалось создать задачу!");
	    	    alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
    	    alert.setHeaderText("SQLERROR!");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
		}
//		target.TaskList.add(task);
		dialogStage.close();
	}
	
	@FXML
    private void ActionExit() {
        dialogStage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Discription.setText(task.getDescription());
		DataStart.setValue(task.getStartDate());
		DataFail.setValue(task.getEndDate());
		Level.setText(Integer.toString(task.getLevel()));
	}

}
