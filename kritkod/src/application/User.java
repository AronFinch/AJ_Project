package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	//прогружает пользователя из бд с конкретным логином и паролем(пока нет обработки исключения)
		public User() throws ClassNotFoundException, SQLException {
			loadUser("admin", "admin");
		}
		
		// загрузить пользователя из БД
		private void loadUser(String login, String password) throws ClassNotFoundException, SQLException {
			DataBaseManager.Connect();
			ResultSet rs = DataBaseManager.GetUser(login, password);
			if(rs.next()) {
				id = rs.getInt("id_user");
				name = rs.getString("name_user");
				gender = rs.getString("gender").charAt(0);
				birthDate = rs.getDate("date_of_birth").toLocalDate();
			} else {
				System.err.println("пользователь не найден");
			}
		}
}
