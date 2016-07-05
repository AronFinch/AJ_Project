package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	 * @throws IOException 
	 */
	@FXML
	public void ShowTask () throws IOException{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerTargetPane.class.getResource("taskList.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("������ ���� " + target.getDescription());
		
	 	ControllerDialogTask controller = loader.getController();
	 	controller.SetDialogStage(stage);
        controller.SetTarget(target);
        
		stage.show();
		
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
