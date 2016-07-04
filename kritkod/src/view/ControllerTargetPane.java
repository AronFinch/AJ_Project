package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.Target;

public class ControllerTargetPane implements Initializable {

	@FXML
    private Label LabelTargetName;
	@FXML
    private Label LabelTaskCount;
	@FXML
    private Label LabelDayCount;
	@FXML
    private ProgressBar TargetProgressBar;
	
	static Target target = null;
	
	@Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
		 	LabelTargetName.setText(target.getLabel());
			LabelTaskCount.setText("����� ����� = " + Integer.toString(target.numberDoteTasks()) 
			+ "/" + Integer.toString(target.numberAllTasks()));
			
			//String time = 
			int Month = target.getEndDate().getMonth().getValue() - target.getStartDate().getMonth().getValue();
			int day = target.getEndDate().getDayOfMonth() - target.getEndDate().getDayOfMonth();
			//LocalDate day = target.getEndDate().minusDays(target.getStartDate().getDayOfMonth());
			LabelDayCount.setText("�������: " + Month + " ����: " + day);
			System.out.println(target.getStartDate().until(target.getEndDate()));
			LabelDayCount.setText("�������� �������: " + target.getStartDate().until(target.getEndDate()).toString());
			
		}
		
 }
