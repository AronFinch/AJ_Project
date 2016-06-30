package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

//Пользователь
public class User {

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
	public void setLogin(String log) {
		
		login = log;
		
	}
	//Назначить пароль пользователя
	public void setPassword(String pass) {
		
		password = pass;
		
	}
	//Назначить имя пользователя
	public void setName(String fullName) {
		
		name = fullName;
		
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
