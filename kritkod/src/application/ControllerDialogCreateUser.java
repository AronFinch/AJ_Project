package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private CheckBox addGenderMale;
	
	@FXML
	private CheckBox addGenderFemale;
	
	@FXML
    public void addNewUser(ActionEvent actionEvent) throws ClassNotFoundException, SQLException{
		User newUser = new User();
		newUser.setId(User.idNew++);
		newUser.setLogin(addUserLogin);
		newUser.setPassword(addUserPassword);
		newUser.setName(addNameUser);
		newUser.setBirthDate(addDataBirthDate);
		newUser.setGender('f');
		

		}
}
	
