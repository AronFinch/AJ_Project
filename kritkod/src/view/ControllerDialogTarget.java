package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Target;

public class ControllerDialogTarget implements Initializable {
	
	@FXML
    private DatePicker DataStart;
	
	@FXML
    private DatePicker DataFail;
	
	@FXML
    private TextField Name;
	
	@FXML
    private TextField Reward;
	
	@FXML
	private ImageView targetImage;
	
	private Stage dialogStage;
    public static Target target = new Target();

    
    public void SetDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void SetTarget(Target target) {
    	
        this.target = target;
    }
	
	@FXML
    public void addNewTarget(ActionEvent actionEvent) throws SQLException{
		Main.mainUser.TargetList.remove(target);
		target.delete();
		Target newTarget = new Target();
		newTarget.setLabel(Name.getText());
		newTarget.setStartDate(DataStart.getValue());
		newTarget.setEndDate(DataFail.getValue());
		newTarget.setIMG(targetImage.getImage().impl_getUrl());
		try {
			if(Main.mainUser.creatTarget(newTarget)) {
				// цель успешно создалась
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
	    	    alert.setHeaderText("Почему-то не удалось создать цель!");
	    	    alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
    	    alert.setHeaderText("SQLERROR!");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
		}
		SetTarget(target);
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
        dialogStage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Name.setText(target.getLabel());
		DataStart.setValue(target.getStartDate());
		DataFail.setValue(target.getEndDate());
		Reward.setText(target.getReward());
		//targetImage.setImage(target.getIMG());;
		
	}

}
