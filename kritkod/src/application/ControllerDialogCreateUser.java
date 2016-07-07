package application;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.User;
import view.Controller;

public class ControllerDialogCreateUser {
	
	@FXML
    private DatePicker addDataBirthDate;
	
	@FXML
    private TextField addUserLogin;
	
	@FXML
    private PasswordField addUserPassword;
	
	@FXML
    private TextField addNameUser;
	
	@FXML
	private RadioButton addGenderMale;
	
	@FXML
	private RadioButton addGenderFemale;
	
	@FXML
	private TextField SQ;
	
	@FXML
	private TextField answer;
	
    private Stage dialogStage;
    
	@FXML
	private ToggleGroup gender;
	
	@FXML
	private Label LoginHelp;
	@FXML
	private Label PasswordHelp;
	@FXML
	private Label NameHelp;
	@FXML
	private Label GenderHelp;
	@FXML
	private Label DateHelp;
	
	private boolean LoginOk;
	private boolean PasswordOk;
	private boolean NameOk;
	private boolean GenderOk;
	private boolean DateOk;
	
	@FXML
	private void LoginKeyPress(KeyEvent kf) {
		String key = kf.getCode().getName();
		String login = addUserLogin.getText();
		if(key.equals("Enter") && LoginOk) {
			if(login.length() > 0)
				addUserPassword.requestFocus();
			else {
				LoginOk = false;
				LoginHelp.setText("Логин не должен быть пустым!");
				LoginHelp.setOpacity(1);
				LoginHelp.setDisable(false);
			}
		} else {
			if(login.length() <= 20) {
				LoginOk = true;
				LoginHelp.setOpacity(0);
				LoginHelp.setDisable(true);
				for(int i = 0; i < login.length(); i++) {
					if(login.charAt(i) == ' ') {
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
	private void PasswordKeyPress(KeyEvent kf) {
		String key = kf.getCode().getName();
		String password = addUserPassword.getText();
		if(key.equals("Enter") && PasswordOk) {
			if(password.length() > 0)
				addNameUser.requestFocus();
			else {
				PasswordOk = false;
				PasswordHelp.setText("Пароль не должен быть пустыи!");
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
	private void NameKeyPress(KeyEvent kf) {
		String key = kf.getCode().getName();
		String name = addNameUser.getText();
		if(key.equals("Enter") && NameOk) {
			if(name.length() > 0)
				addDataBirthDate.requestFocus();
			else {
				NameOk = false;
				NameHelp.setText("Имя не должно быть пустым!");
				NameHelp.setOpacity(1);
				NameHelp.setDisable(false);
			}
		} else {
			if(name.length() <= 50) {
				NameOk = false;
				NameHelp.setText("Имя не должно состоять из пробелов!");
				NameHelp.setOpacity(1);
				NameHelp.setDisable(false);
				for(int i = 0; i < name.length(); i++) {
					if(name.charAt(i) != ' ') {
						NameOk = true;
						NameHelp.setOpacity(0);
						NameHelp.setDisable(true);
					}
				}
			} else {
				NameOk = false;
				NameHelp.setText("Имя не должно первышать 50 символов!");
				NameHelp.setOpacity(1);
				NameHelp.setDisable(false);
			}
		}
	}
	@FXML
	private void DateKeyPress(KeyEvent kf) throws IOException {
		String key = kf.getCode().getName();
		LocalDate date = addDataBirthDate.getValue();
		if(key.equals("Enter")) {
			addNewUser();
		} 
	}
	@FXML
    public void addNewUser() throws IOException {
		String login = addUserLogin.getText();
		String password = addUserPassword.getText();
		String name = addNameUser.getText();
		LocalDate birthDate = addDataBirthDate.getValue();
		char userGender = 'h';
		
		if(gender.getSelectedToggle() != null) {
			GenderOk = true;
			if(addGenderMale.equals(true))
				userGender = 'm';
			else
				userGender = 'f';
		} else {
			GenderOk = false;
			GenderHelp.setText("Выберите пол!");
			GenderHelp.setOpacity(1);
			GenderHelp.setDisable(false);
		}
		if(birthDate != null) {
			if(birthDate.isBefore(LocalDate.now())) {
				DateOk = true;
			} else {
				DateOk = false;
				DateHelp.setText("Вы не могли родиться в будущем!");
				DateHelp.setOpacity(1);
				DateHelp.setDisable(false);
			}
		} else {
			DateOk = false;
			DateHelp.setText("Введите дату рождения!");
			DateHelp.setOpacity(1);
			DateHelp.setDisable(false);
			addDataBirthDate.requestFocus();
		}
		if(!name.equals("")) {
			NameOk = true;
		} else {
			NameOk = false;
			NameHelp.setText("Имя не должно быть пустым!");
			NameHelp.setOpacity(1);
			NameHelp.setDisable(false);
			addNameUser.requestFocus();
		}
		if(!password.equals("")) {
			PasswordOk = true;
		} else {
			PasswordOk = false;
			PasswordHelp.setText("Пароль не должен быть пустым!");
			PasswordHelp.setOpacity(1);
			PasswordHelp.setDisable(false);
			addUserPassword.requestFocus();
		}
		if(!login.equals("")) {
			LoginOk = true;
		} else {
			LoginOk = false;
			LoginHelp.setText("Логин не должен быть пустым!");
			LoginHelp.setOpacity(1);
			LoginHelp.setDisable(false);
			addUserLogin.requestFocus();
		}
		if(LoginOk && PasswordOk && NameOk && GenderOk && DateOk) {
			Main.mainUser.setName(name);
			Main.mainUser.setGender(userGender);
			Main.mainUser.setBirthDate(birthDate);
			Main.mainUser.setRating(100);
			try {
				if(Main.mainUser.SaveUser(login, password, SQ.getText(), answer.getText())) {
					// переход на главный экран
					// прогрузить остальных пользователей
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
					stage.setScene(new Scene(root));
					stage.setTitle("Главный экран:");
					stage.show();
				}
			} catch (SQLException e) {
				Main.mainUser.clear();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Information");
	    	    alert.setHeaderText("Error!");
	    	    alert.setContentText(e.getMessage());
	    	    alert.showAndWait();
			}
		}
	}
	
	@FXML
    private void handleCancel() throws IOException {
		Stage stage = Main.primaryStage;
    	Parent root = FXMLLoader.load(Main.class.getResource("dialogLogin.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Авторизация");	
    }
}
	
