package view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Target;
import model.Task;

public class ControllerDialogNewTask {
	
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
	
    public void SetDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

	public void SetTarget(Target target) {
		// TODO Auto-generated method stub
		this.target = target;
		
	}
	
	
	@FXML
    public void ActionOk(ActionEvent actionEvent){
		Task task = new Task();
		task.setDescription(Discription.getText());
		task.setStartDate(DataStart.getValue());
		task.setEndDate(DataFail.getValue());
		task.setLevel(Integer.parseUnsignedInt(Level.getText()));
		target.TaskList.add(task);
		//Main.mainUser.TargetList.add(newTarget);
		dialogStage.close();
	}
	
	@FXML
    private void ActionExit() {
        dialogStage.close();
    }

}
