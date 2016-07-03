package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.User;

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
	
    private Stage dialogStage;
    
	@FXML
	private ToggleGroup gender;
	
	@FXML
    public void addNewUser(ActionEvent actionEvent) {
		User newUser = new User();
//		newUser.setId(User.idNew++); id самому добавл€ть не нужно,
//		при добавление в Ѕƒ id автоматически генеритс€ и инкеменируетс€
// 
//		newUser.setLogin(addUserLogin);
//		newUser.setPassword(addUserPassword);
		newUser.setName(addNameUser.getText());
		newUser.setBirthDate(addDataBirthDate.getValue());

		if(gender.getSelectedToggle() == addGenderFemale)
			newUser.setGender('f');
		else newUser.setGender('m');
		
	
		try {
			newUser.SaveUser(addUserLogin.getText(), addUserPassword.getText(), "√од крещени€ руси?", "988");
		} catch (SQLException e) {
			// если что-то пошло не так и пользователь не загрузилс€ из базы данных
			e.printStackTrace();
		}

		}
	
	@FXML
    private void handleCancel() throws IOException {
		Stage stage = Main.primaryStage;
    	Parent root = FXMLLoader.load(Main.class.getResource("dialogLogin.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("јвторизаци€");	
    }
}
	
