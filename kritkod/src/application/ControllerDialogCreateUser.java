package application;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
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
    public void addNewUser(ActionEvent actionEvent) throws IOException {
		String login = addUserLogin.getText();
		String password = addUserPassword.getText();
		String name = addNameUser.getText();
		LocalDate birthDate = addDataBirthDate.getValue();
		char userGender = 'h';
		// тут будет многоуровневая конструкций условий
		if(!login.equals("") && !password.equals("")) { 
			// логин и пароль введены
			if(!name.equals("")) {
				if(gender.getSelectedToggle() != null) {
					if(birthDate != null) {
						if(gender.getSelectedToggle() == addGenderMale)
							userGender = 'm';
						else
							userGender = 'f';
						Main.mainUser.setBirthDate(birthDate);
						Main.mainUser.setName(name);
						Main.mainUser.setGender(userGender);
						try {
							if(Main.mainUser.SaveUser(login, password, SQ.getText(), answer.getText())) {
								// переход на главный экран
								// прогрузить остальных пользователей
								Main.primaryStage.close();
								
								Stage stage = new Stage();
								Main.primaryStage = stage;
							 	Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
								stage.setScene(new Scene(root));
								stage.setTitle("Главный экран:");
								stage.show();
							} else {
								Main.mainUser.clear();
								Alert alert = new Alert(Alert.AlertType.INFORMATION);
								alert.setTitle("Information");
					    	    alert.setHeaderText("Пользователь с таким логином уже существует!");
					    	    alert.showAndWait();
							}
						} catch (SQLException e) {
							Main.mainUser.clear();
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Information");
				    	    alert.setHeaderText("Error!");
				    	    alert.setContentText(e.getMessage());
				    	    alert.showAndWait();
						}
					} else {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Information");
			    	    alert.setHeaderText("Вы не ввели дату рождения!");
			    	    alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information");
		    	    alert.setHeaderText("Вы не выбрали пол!");
		    	    alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
	    	    alert.setHeaderText("Пустое поле имени!");
	    	    alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
    	    alert.setHeaderText("Пустое поле логина или пароля!");
    	    alert.showAndWait();
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
	
