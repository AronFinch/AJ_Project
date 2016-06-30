package application;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import javafx.scene.control.DatePicker;

//Пользователь
public class User {

	private int id;
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	
	//Назначить айди пользователя
	public void setId(int idNumber) {
		
		id = idNumber;
		
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
	//Создать цель
	public void createTarget(Target target) {
		
		TargetList.add(target);
		
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
