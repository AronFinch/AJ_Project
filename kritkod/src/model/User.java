package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

//Пользователь
public class User {
	
//	static int idNew = 0;

	private int id;
//	private String login;
//	private String password;
	private String name;
	private char gender;
	private LocalDate birthDate;
	
	//конструктор по умаолчанию 
	// заносит в параметры нулевые значания
	public User() {
		id = 0; 
//		login = "";
//		password = "";
		name = "";
		gender = 'n';
		birthDate = null;
	}
	
	//Назначить айди пользователя
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//Назначить логин пользователя
//	public void setLogin(TextField log) {
		
//		login = log.getText();
		
//	}
	//Назначить пароль пользователя
//	public void setPassword(TextField pass) {
		
//		password = pass.getText();
		
//	}
	//Назначить имя пользователя
	public void setName(String Name) {
		
		name = Name;
		
	}
	//Назначить пол пользователя
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//Назначить дату рождения пользователя
	public void setBirthDate(LocalDate date) {
		
		birthDate = date;
		
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
		
		// загрузить пользователя из БД
	public void LoadUser(String login, String password) throws ClassNotFoundException, SQLException {
		DataBaseManager.Connect();
		ResultSet rs = DataBaseManager.BDGetUser(login, password);
		if(rs.next()) {
			id = rs.getInt("id_user");
			name = rs.getString("name_user");
			gender = rs.getString("gender").charAt(0);
			birthDate = rs.getDate("date_of_birth").toLocalDate();
		} else {
			System.err.println("пользователь не найден");
		}
		DataBaseManager.Disconnect();
	}
	//сохранение пользователя в базе данных
	public void SeveUser(String login, String password, String secritQuestion, String answer) throws ClassNotFoundException, SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDAddUser(login, password, secritQuestion, answer, this);
		DataBaseManager.Disconnect();
	}
}
