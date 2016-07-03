package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private TextField answer;
    
    private Stage dialogStage;
    
    @FXML
    public void btnChek(){
        
    	// загрузка из логина
    	if(!Login.getText().equals("")) {
    		try {
				if(Main.mainUser.loadUser(Login.getText())) {
					buttonNewPassword.setDisable(false);
		            labelDisable.setDisable(false);
		            answer.setDisable(false);
		            // загрузить вопрос
		            labelDisable.setText(Main.mainUser.getQuestion());
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    		alert.setTitle("Information");
		    	    alert.setHeaderText("Пользователя с таким логином не существует!");
		    	    alert.showAndWait();
				}
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setTitle("Information");
	    	    alert.setHeaderText("Error!");
	    	    alert.setContentText(e.getMessage());
	    	    alert.showAndWait();
			}
    		
    	} else {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setTitle("Information");
    	    alert.setHeaderText("Поле Login не должно быть пустым!");
    	    alert.showAndWait();
    	}
    }
    
    @FXML
    public void СheckNewPassword() throws IOException{  
    	//тут запрашивается ответ по нажатию кнопки
    	try {
			if (Main.mainUser.checkAnswer(answer.getText())) {
				Stage stage = Main.primaryStage;
				Parent root = FXMLLoader.load(getClass().getResource("dialogLoginNewPassword.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("Введите новый пароль:");	
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
	    		alert.setTitle("Information");
	    	    alert.setHeaderText("Неверный ответ!");
	    	    alert.showAndWait();
			}
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setTitle("Information");
    	    alert.setHeaderText("Error!");
    	    alert.setContentText(e.getMessage());
    	    alert.showAndWait();
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
