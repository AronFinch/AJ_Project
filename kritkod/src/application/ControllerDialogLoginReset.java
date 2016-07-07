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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControllerDialogLoginReset implements Initializable {
	
	@FXML
    private TextField Login;
    
    @FXML
    private Button buttonNewPassword;
    
    @FXML
    private Button buttonGetQuestion;
    
    @FXML
    private Label labelDisable;
            
    @FXML
    private TextField answer;
    
    private Stage dialogStage;
    
    @FXML
    private Label LoginHelp;
    
    private boolean LoginOk;
    
    @FXML
    private void LoginKeyPress(KeyEvent kf) {
    	String key = kf.getCode().getName();
		String log = Login.getText();
		if(LoginOk && key.equals("Enter")) {
			if(log.length() > 0) {
				btnChek();
			} else {
				LoginOk = false;
	        	LoginHelp.setText("Логин не должен быть пустым!");
	        	LoginHelp.setOpacity(1);
	        	LoginHelp.setDisable(false);
			}
		} else {
			if(log.length() <= 20) {
				LoginOk = true;
				LoginHelp.setOpacity(0);
				LoginHelp.setDisable(true);
				for(int i = 0; i < log.length(); i++) {
					if(log.charAt(i) == ' ') {
						LoginOk = false;
						LoginHelp.setText("Логин не должен содержать пробелы!");
						LoginHelp.setOpacity(1);
						LoginHelp.setDisable(false);
						break;
					}
				}
			} else {
				LoginOk = false;
	        	LoginHelp.setText("Логин не должен превышать 20 символов!");
	        	LoginHelp.setOpacity(1);
	        	LoginHelp.setDisable(false);
			}
		}
    }
    
    @FXML
    private void AnswerKeyPress(KeyEvent kf) throws IOException {
    	String key = kf.getCode().getName();
		String ans = answer.getText();
		if(key.equals("Enter")) {
			СheckNewPassword();
		}
    }
    
    @FXML
    public void btnChek(){
        String login = Login.getText();
    	// загрузка из логина
        if(!login.equals("")) {
        	LoginOk = true;
        } else {
        	LoginOk = false;
        	LoginHelp.setText("Логин не должен быть пустым!");
        	LoginHelp.setOpacity(1);
        	LoginHelp.setDisable(false);
        }
        if(LoginOk) {
        	try {
				if(Main.mainUser.userIsExist(login)) {
					buttonNewPassword.setDisable(false);
					
		            answer.setDisable(false);
		            
		            labelDisable.setText(Main.mainUser.getQuestion(login));
		            labelDisable.setDisable(false);
		            labelDisable.setOpacity(1);
		            
		            buttonGetQuestion.setDisable(true);
		            Login.setDisable(true);
		            answer.requestFocus();
				} else {
					LoginOk = false;
					LoginHelp.setText("Такого пользователя не существует!");
					LoginHelp.setOpacity(1);
					LoginHelp.setDisable(false);
				}
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
	    		alert.setTitle("Information");
	    	    alert.setHeaderText("Error!");
	    	    alert.setContentText(e.getMessage());
	    	    alert.showAndWait();
			}
        }
    }
    
    @FXML
    public void СheckNewPassword() throws IOException{  
    	//тут запрашивается ответ по нажатию кнопки
    	try {
			if (Main.mainUser.checkAnswer(answer.getText(), Login.getText())) {
				if(Main.mainUser.loadUser(Login.getText())) {
					Stage stage = Main.primaryStage;
					Parent root = FXMLLoader.load(getClass().getResource("dialogLoginNewPassword.fxml"));
					root.getStylesheets().clear();
				 	root.getStylesheets().add(Main.style);
					stage.setScene(new Scene(root));
					stage.setTitle("Введите новый пароль:");
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    		alert.setTitle("Information");
		    	    alert.setHeaderText("Удивительно, вы правильно ответили, но пользователь не загрузился!");
		    	    alert.showAndWait();
				}
			} else {
				labelDisable.setText("Неверный ответ!");
				
				buttonNewPassword.setDisable(true);
	            answer.setDisable(true);
	            
	            
	            buttonGetQuestion.setDisable(false);
	            Login.setDisable(false);
	            answer.clear();
	            
	            Login.requestFocus();
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
    	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
		stage.setScene(new Scene(root));
		stage.setTitle("Авторизация");	
    }

}
