package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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
	
	@FXML
	private ImageView targetImage;
	
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
		newTarget.setLabel(addNameTarget.getText());
		newTarget.setStartDate(addDataTargetStart.getValue());
		newTarget.setEndDate(addDataTargetFail.getValue());
		SetTarget(target);
		Main.mainUser.TargetList.add(newTarget);
		okClicked = true;
		dialogStage.close();
	}

	@FXML
	public void addTargetImage(ActionEvent actionEvent) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Выберите картинку");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		List<String> listString = Arrays.asList("*.jpg", "*.jpeg", "*.png", "*.bmp", "*.gif");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Picture", listString));
		
			File file = fileChooser.showOpenDialog(dialogStage);
			
			if(file != null) {
				String fileURL = file.toURI().toString();
				//Нужно перекидывать картинку в отдельную папку в проекте, а не просто брать откуда-нибудь
				Image imgLoad = new Image(fileURL);
				
				targetImage.setImage(imgLoad);
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
			target = Main.LocalTarget;
		//Target target = new Target();
		addDiscriptionTarget.setText(target.getLabel());
		//addNameTarget.setText(target.getLabel());
		addDataTargetStart.setValue(target.getStartDate());
		addDataTargetFail.setValue(target.getEndDate());
		
	}

}
