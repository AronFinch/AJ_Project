package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.User;
import view.Controller;

public class ControllerDialogLoginNewPassword {
	
	@FXML
	private PasswordField NewPassword;
	@FXML
	private PasswordField NewPassword2;
	@FXML
	private Label PasswordHelp;
	private boolean PasswordOk;

	@FXML
	public void PasswordKeyPress(KeyEvent fk) throws IOException {
		String key = fk.getCode().getName();
		String password;
		
		if(NewPassword.isFocused())
			password = NewPassword.getText();
		else 
			password = NewPassword2.getText();
		
		
		if(key.equals("Enter") && PasswordOk) {
			if(password.length() > 0) {
				if(NewPassword.isFocused())
					NewPassword2.requestFocus();
				else
					ButtonAction();
			} else {
				PasswordHelp.setText("Пароль не должен быть пустым!");
				PasswordHelp.setOpacity(1);
				PasswordHelp.setDisable(false);
			}
		} else {
			if(password.length() <= 20) {
				PasswordOk = true;
				PasswordHelp.setOpacity(0);
				PasswordHelp.setDisable(true);
				for(int i = 0; i < password.length(); i++) {
					if(password.charAt(i) == ' ') {
						PasswordOk = false;
						PasswordHelp.setText("Пароль не должен содержать пробелы!");
						PasswordHelp.setOpacity(1);
						PasswordHelp.setDisable(false);
						break;
					}
				}
			} else {
				PasswordOk = false;
				PasswordHelp.setText("Пароль не должен превышать 20 символов!");
				PasswordHelp.setOpacity(1);
				PasswordHelp.setDisable(false);
			}
		}
	}
	
	@FXML
    public void ButtonAction() throws IOException{
		String password1, password2;
		password1 = NewPassword.getText();
		password2 = NewPassword2.getText();
		
		if(password2.equals("")) {
			PasswordOk = false;
			PasswordHelp.setText("Пароль не должен быть пустым!");
			PasswordHelp.setOpacity(1);
			PasswordHelp.setDisable(false);
			NewPassword.requestFocus();
		}
		if(password1.equals("")) {
			PasswordOk = false;
			PasswordHelp.setText("Пароль не должен быть пустым!");
			PasswordHelp.setOpacity(1);
			PasswordHelp.setDisable(false);
			NewPassword2.requestFocus();
		}
		if(PasswordOk) {
			//начинаем проверку двух паролей
			if(password1.equals(password2)) {
				try {
					Main.mainUser.changePassword(password1);
					ArrayList<String> logins;
					
					DataBaseManager.Connect();
					logins = DataBaseManager.BDGetOtherLogins(Main.mainUser.getId());
					DataBaseManager.Disconnect();
					
					for(int i = 0; i < logins.size(); i++) {
						User user = new User();
						user.loadUser(logins.get(i));
						Main.otherUsers.add(user);
					}
					Main.primaryStage.close();
					
					Stage stage = new Stage();
					Main.primaryStage = stage;
				 	Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
				 	root.getStylesheets().clear();
				 	root.getStylesheets().add(Main.style);
					stage.setScene(new Scene(root));
					stage.setTitle("Главный экран:");
					stage.show();
				} catch (SQLException e) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Information");
		    	    alert.setHeaderText("Error!");
		    	    alert.setContentText(e.getMessage());
		    	    alert.showAndWait();
				}
			} else {
				PasswordOk = false;
				PasswordHelp.setText("Вы ошиблись при второй попытке ввода!");
				PasswordHelp.setOpacity(1);
				PasswordHelp.setDisable(false);
				NewPassword.clear();
				NewPassword2.clear();
				NewPassword.requestFocus();
			}
		}
	}
}
