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

	static Target target = null;
	public static Parent root;
	public static FlowPane pane;
	
	/**
	 * Метод удаляющий цель. Совсем.
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
		
		pane.getChildren().clear();
		
		Iterator<Target> itr = Main.mainUser.TargetList.iterator();
		while (itr.hasNext()) {
			ControllerTargetPane.target = itr.next();
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("miniTarget.fxml"));
				 	Parent root = loader.load();
				 	//ControllerTargetPane controller = loader.getController();
				 	//controller.target = itr.next();
				 	
				 	pane.getChildren().add(root);
				 	ControllerTargetPane.root = root;
				 	ControllerTargetPane.pane = pane;
				 	
				 	/*
				 	if(itr.next().TaskList.getEndDate().isAfter(LocalDate.now())&& itr.next().){
				 		ActiveTargetFlowPane.getChildren().add(root);
				 	}else if(false){
				 		
				 		ActiveTargetFlowPane1.getChildren().add(root);
				 	}
				 	else{
				 		
				 		ActiveTargetFlowPane2.getChildren().add(root);
				 	}
				 	*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	 
		
		
	}
	
	/**
	 * Метод вызывающий окно просмотра задач этой цели.
	 * @throws IOException 
	 */
	@FXML
	public void ShowTask () throws IOException{
		
		ControllerDialogTask.target = target; 
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerTargetPane.class.getResource("taskList.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("Задачи цели " + target.getLabel());
		
	 	ControllerDialogTask controller = loader.getController();
	 	controller.SetDialogStage(stage);
        controller.SetTarget(target);
        
		stage.show();
		
		
		
	}
	
	/**
	 * Метод открывающий окно с просмотром цели. Переделать.
	 * @throws IOException 
	 */
	@FXML
	public void ShowTargetInfo () throws IOException{
		
		Target tempTarget = target;
		ControllerDialogTarget.target = tempTarget;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogTarget.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("Цель:");
		
	 	ControllerDialogTarget controller = loader.getController();
        controller.SetDialogStage(stage);
        controller.SetTarget(tempTarget);
        
		stage.showAndWait();
		
		pane.getChildren().clear();
		
		Iterator<Target> itr = Main.mainUser.TargetList.iterator();
		while (itr.hasNext()) {
			ControllerTargetPane.target = itr.next();
				try {
					loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("miniTarget.fxml"));
				 	root = loader.load();
				 	//ControllerTargetPane controller = loader.getController();
				 	//controller.target = itr.next();
				 	
				 	pane.getChildren().add(root);
				 	ControllerTargetPane.root = root;
				 	ControllerTargetPane.pane = pane;
				 	
				 	/*
				 	if(itr.next().TaskList.getEndDate().isAfter(LocalDate.now())&& itr.next().){
				 		ActiveTargetFlowPane.getChildren().add(root);
				 	}else if(false){
				 		
				 		ActiveTargetFlowPane1.getChildren().add(root);
				 	}
				 	else{
				 		
				 		ActiveTargetFlowPane2.getChildren().add(root);
				 	}
				 	*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
			LabelTargetName.setText(target.getLabel());
			LabelTaskCount.setText("Число задач = " + Integer.toString(target.numberDoneTasks()) 
			+ "/" + Integer.toString(target.numberAllTasks()));
			
			int Month = target.getEndDate().getMonth().getValue() - target.getStartDate().getMonth().getValue();
			int day = target.getEndDate().getDayOfMonth() - target.getEndDate().getDayOfMonth();
			//LocalDate day = target.getEndDate().minusDays(target.getStartDate().getDayOfMonth());
			//LabelDayCount.setText("Месяцев: " + Month + " Дней: " + day);
			LabelDayCount.setText("Осталось времени: " + target.getStartDate().until(target.getEndDate()).toString());
			
		}
		
 }
