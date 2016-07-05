package view;
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import model.Target;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;



public class ControllerDialogTargetInfo {
	
	Target targetInfo = new Target();
	
	@FXML
	private Label infoLabelTarget;
	
	@FXML
	private DatePicker infoDataTargetStart;
	
	@FXML
	private DatePicker infoDataTargetEnd;
	
	@FXML
	private TextArea infoDiscriptionTarget;
	
	@FXML
	private ImageView infoImageTarget;
	
	

	public void initialize(URL location, ResourceBundle resources){
        // TODO
		
		//тестовый таргер.
		//targetInfo.setLabel("test");
		targetInfo.setDescription("bom");
		targetInfo.setStartDate(LocalDate.now());
		targetInfo.setEndDate(LocalDate.now());
		
		//infoLabelTarget.setText(targetInfo.getLabel());
		infoDiscriptionTarget.setText(targetInfo.getDescription());
		infoDataTargetStart.setValue(targetInfo.getStartDate());
		infoDataTargetEnd.setValue(targetInfo.getEndDate());
	//	infoImageTarget.set
	}
	
	@FXML
	public void infoEditImage()
	{
		
	}
	
	@FXML 
	public void infoDeleteImage()
	{
		
	}
	
}
