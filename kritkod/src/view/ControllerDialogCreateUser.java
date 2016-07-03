package view;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private CheckBox addGenderMale;
	
	@FXML
	private CheckBox addGenderFemale;
	
	@FXML
    public void addNewUser(ActionEvent actionEvent) {
		User newUser = new User();
//		newUser.setId(User.idNew++); id ������ ��������� �� �����,
//		��� ���������� � �� id ������������� ��������� � ��������������
// 
//		newUser.setLogin(addUserLogin);
//		newUser.setPassword(addUserPassword);
		newUser.setName(addNameUser.getText());
		newUser.setBirthDate(addDataBirthDate.getValue());
		newUser.setGender('f');
		try {
			newUser.SaveUser(addUserLogin.getText(), addUserPassword.getText(), "��� �������� ����?", "988");
		} catch (SQLException e) {
			// ���� ���-�� ����� �� ��� � ������������ �� ���������� �� ���� ������
			e.printStackTrace();
		}

		}
}
	
