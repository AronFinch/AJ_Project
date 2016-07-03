package view;

import java.net.URL;
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
			LabelTaskCount.setText("Число задач... Заглушка!");
			int Month = target.getEndDate().getMonth().getValue() - target.getStartDate().getMonth().getValue();
			int day = target.getEndDate().getDayOfMonth() - target.getEndDate().getDayOfMonth();
			LabelDayCount.setText("Месяцев: " + Month + " Дней: " + day);
			
		}
		
 }
