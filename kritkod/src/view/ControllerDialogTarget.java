package view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Target;

public class ControllerDialogTarget implements Initializable {
	
	@FXML
    private DatePicker addDataTargetStart;
	
	@FXML
    private DatePicker addDataTargetFail;
	
	@FXML
    private TextField addNameTarget;
	
	@FXML
    private TextField addTargetReward;
	
	@FXML
	private TextArea addDiscriptionTarget;
	
	@FXML
    private Label labelAddTarget;
	
	private Stage dialogStage;
    private Target target;
    private boolean okClicked = false;
    
    public void SetDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public boolean isOkClicked() {
        return okClicked;
    }
    
    public void SetTarget(Target target) {
    	
        this.target = target;
    }
	
	@FXML
    public void addNewTarget(ActionEvent actionEvent){
		Target newTarget = new Target();
		try {
			newTarget.setDescription(addDiscriptionTarget);
			newTarget.setLabel(addNameTarget);
			newTarget.setStartDate(addDataTargetStart);
			newTarget.setEndDate(addDataTargetFail);
	        SetTarget(target);
	        Main.mainUser.TargetList.add(newTarget);
	        okClicked = true;
	        dialogStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
    private void handleCancel() {
		okClicked=false;
        dialogStage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
