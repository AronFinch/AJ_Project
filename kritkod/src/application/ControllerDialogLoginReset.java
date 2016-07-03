package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerDialogLoginReset implements Initializable {
	
	@FXML
    private TextField Login;
    
    @FXML
    private Button buttonNewPassword;
    
    @FXML
    private Label labelDisable;
            
    @FXML
    private PasswordField passwordField;
    
    private Stage dialogStage;
    
    @FXML
    public void btnChek(){
        
    	buttonNewPassword.setDisable(false);
        labelDisable.setDisable(false);
        passwordField.setDisable(false);
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
