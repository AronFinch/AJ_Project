package view;

import java.io.IOException;
import java.util.Iterator;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Target;

public class ControllerDialogTarget {
	
	@FXML
    private DatePicker addDataTargetStart;
	
	@FXML
    private DatePicker addDataTargetFail;
	
	@FXML
    private TextField addNameTarget;
	
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
        /*this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
        */
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
	        okClicked = true;
	        dialogStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Main.TargetList.iterator().next());
		
		Iterator<Target> itr = Main.TargetList.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next().toString()
					+ ",");
		}
		System.out.println("\n");
	}

	@FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
