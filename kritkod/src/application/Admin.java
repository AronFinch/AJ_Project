package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

//Администратор
public class Admin {
	
	private int id;
	private String login = "";
	private String password = "";
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	
	//Назначить айди администаратора
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//Назначить логин администратора
	public void setLogin(String log) {
		
		login = log;
		
	}
	//Назначить пароль администратора
	public void setPassword(String pass) {
		
		password = pass;
		
	}
	//Назначить имя администратора
	public void setName(String fullName) {
		
		name = fullName;
		
	}
	//Назначить пол администратора
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//Назначить дату рождения администратора
	public void setBirthDate(DatePicker addDataTarget) {
		
		birthDate = addDataTarget.getValue();
		
	}
	//Получить дату рождения администратора
	public LocalDate getBirthDate() {
		
		return birthDate;
		
	}
	//Получить пол администратора
	public char getGender() {
		
		return gender;
		
	}
	//Получить имя администратора
	public String getName() {
		
		return name;
		
	}
	//Получить айди администаратора
	public int getId(Admin admin) {
		
		return admin.id;
		
	}
}
