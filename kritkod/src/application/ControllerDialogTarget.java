package application;

import java.io.IOException;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	
	@FXML
    public void addNewTarget(ActionEvent actionEvent){
		Target newTarget = new Target();
		try {
			newTarget.setDescription(addDiscriptionTarget);
			newTarget.setLabel(addNameTarget);
			newTarget.setStartDate(addDataTargetStart);
			newTarget.setEndDate(addDataTargetFail);
			Main.TargetList.add(newTarget);
			//labelAddTarget.setText("Цель " + addNameTarget.getText() + " добавлена!");
			//Controller.NewPaneTarget(newTarget);
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


}
