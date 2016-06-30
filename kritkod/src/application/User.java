package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

//Пользователь
public class User {
	
	static int idNew = 0;

	private int id;
	private String login = "";
	private String password = "";
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	
	//Назначить айди пользователя
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//Назначить логин пользователя
	public void setLogin(TextField log) {
		
		login = log.getText();
		
	}
	//Назначить пароль пользователя
	public void setPassword(TextField pass) {
		
		password = pass.getText();
		
	}
	//Назначить имя пользователя
	public void setName(TextField fullName) {
		
		name = fullName.getText();
		
	}
	//Назначить пол пользователя
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//Назначить дату рождения пользователя
	public void setBirthDate(DatePicker addDataTarget) {
		
		birthDate = addDataTarget.getValue();
		
	}
	//Получить дату рождения пользователя
	public LocalDate getBirthDate() {
		
		return birthDate;
		
	}
	//Получить пол пользователя
	public char getGender() {
		
		return gender;
		
	}
	//Получить имя пользователя
	public String getName() {
		
		return name;
		
	}
	//Получить айди пользователя
	public int getId(User user) {
		
		return user.id;
		
	}
	
}
