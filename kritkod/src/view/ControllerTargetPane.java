package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
	
	/**
	 * ����� ��������� ����. ������.
	 */
	@FXML
	public void DeleteTarget (){
		
	}
	
	/**
	 * ����� ���������� ���� ��������� ����� ���� ����.
	 */
	@FXML
	public void ShowTask (){
		
	}
	
	/**
	 * ����� ����������� ���� � ���������� ����. ����������.
	 * @throws IOException 
	 */
	@FXML
	public void ShowTargetInfo () throws IOException{
		
		//Target target = new Target();
		Main.showTargetEditDialog(target);
		
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
		 	//LabelTargetName.setText(target.getLabel());
		LabelTargetName.setText(target.getDescription());
			LabelTaskCount.setText("����� ����� = " + Integer.toString(target.numberDoneTasks()) 
			+ "/" + Integer.toString(target.numberAllTasks()));
			
			int Month = target.getEndDate().getMonth().getValue() - target.getStartDate().getMonth().getValue();
			int day = target.getEndDate().getDayOfMonth() - target.getEndDate().getDayOfMonth();
			//LocalDate day = target.getEndDate().minusDays(target.getStartDate().getDayOfMonth());
			//LabelDayCount.setText("�������: " + Month + " ����: " + day);
			LabelDayCount.setText("�������� �������: " + target.getStartDate().until(target.getEndDate()).toString());
			
		}
		
 }
