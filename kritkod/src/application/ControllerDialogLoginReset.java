package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    
    @FXML
    public void СheckNewPassword() throws IOException{
        
    	if(Login.getText() != null){
			
    		Stage stage = Main.primaryStage;
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogLoginNewPassword.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Введите новый пароль:");				
							
		}
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
    }
    
    @FXML
    private void handleCancel() throws IOException {
    	Stage stage = Main.primaryStage;
    	Parent root = FXMLLoader.load(Main.class.getResource("dialogLogin.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Авторизация");	
    }

}
