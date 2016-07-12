package view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
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

	public Target target = new Target();
	public static Target newTarget = new Target();
	public Parent root;
	public FlowPane pane;
	public FlowPane pane1;
	public FlowPane pane2;
	
	/**
	 * ћетод удал€ющий цель. —овсем.
	 * @throws IOException 
	 */
	@FXML
	public void DeleteTarget () throws IOException{
		
		
		try {
			Main.mainUser.TargetList.remove(target);
			target.delete();
		} catch (SQLException e1) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
    	    alert.setHeaderText("SQLERROR");
    	    alert.setContentText(e1.getMessage());
    	    alert.showAndWait();
		}
		
		Main.MiniTargetInic();	
	}
	
	/**
	 * ћетод вызывающий окно просмотра задач этой цели.
	 * @throws IOException 
	 */
	@FXML
	public void ShowTask () throws IOException{
		
		Stage stage = new Stage();
		ControllerDialogTask.target = target;
		ControllerDialogTask.dialogStage = stage;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerTargetPane.class.getResource("taskList.fxml"));
	 	Parent root = loader.load();
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("«адачи цели " + target.getLabel());
		
	 	ControllerDialogTask controller = loader.getController();
	 	controller.dialogStage = stage;
        controller.target = target;
        
		stage.show();
		
		
		
	}
	
	/**
	 * ћетод открывающий окно с просмотром цели.
	 * @throws IOException 
	 */
	@FXML
	public void ShowTargetInfo () throws IOException{
		
		Target tempTarget = target;
		ControllerDialogTarget.target = tempTarget;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogTarget.fxml"));
	 	Parent root = loader.load();
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("÷ель:");
		
	 	ControllerDialogTarget controller = loader.getController();
        controller.SetDialogStage(stage);
        controller.SetTarget(tempTarget);
        
		stage.showAndWait();
		
		Main.MiniTargetInic();
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
			LabelTargetName.setText(newTarget.getLabel());
			LabelTaskCount.setText("„исло задач = " + Integer.toString(newTarget.numberDoneTasks()) 
			+ "/" + Integer.toString(newTarget.numberAllTasks()));
			
			int Month = newTarget.getEndDate().getMonth().getValue() - newTarget.getStartDate().getMonth().getValue();
			int day = newTarget.getEndDate().getDayOfMonth() - newTarget.getEndDate().getDayOfMonth();
			//LocalDate day = target.getEndDate().minusDays(target.getStartDate().getDayOfMonth());
			//LabelDayCount.setText("ћес€цев: " + Month + " ƒней: " + day);
			LabelDayCount.setText("ќсталось времени: " + newTarget.getStartDate().until(newTarget.getEndDate()).toString());
			target = newTarget;
		}
		
 }
