package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

//Пользователь
public class User {

	private int id;
	private String name;
	private LocalDate birthDate;
	private char gender;
	private int rating;
	
	public LinkedHashSet<Target> TargetList;
	public LinkedHashSet<Notice> NoticeList;
	public Statistics statistics;
	/*
	 * конструктор по умолчанию
	 * создаёт пользователя 
	 * после зоздания нужно будет прогрузить из базы данных, или самому заполнить поля
	 */
	public User() {
		id = 0; // нумерация в базе с 1
		name = "пользователь не найден"; // 
		birthDate = LocalDate.of(1000, 1, 1); // пусть будет такая дата
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //дефолтное значение в базе 300		
		TargetList = new LinkedHashSet<Target>();
		NoticeList = new LinkedHashSet<Notice>();
		statistics = new Statistics();
	}
	
	/*
	 * метод заполняющий класс дефолтными результатами
	 */
	public void clear() {
		id = 0; // нумерация в базе с 1
		name = "пользователь не найден"; // 
		birthDate = LocalDate.of(1000, 1, 1); // пусть будет такая дата
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //дефолтное значение в базе 300		
		TargetList.clear();
		NoticeList.clear();
		Statistics.clear();
	}
	
	/*
	 * метод сохранения пользователя в базе данных
	 */
	public boolean SaveUser(String login,
						 String password,
						 String secriteQuestion,
						 String answer) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDAddUser(login, password, secriteQuestion, answer, this);
		DataBaseManager.Disconnect();
		return res;
	}
	/*
	 * методы загрузки пользователя из базы данных
	 */
	public boolean loadUser(String login, String password) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDGetUser(login, password, this);
		if (res) {
			//тут подгружвать цели
			DataBaseManager.BDLoadAllTargets(id, TargetList);
			DataBaseManager.Disconnect();
			return res;
		} else {
			DataBaseManager.Disconnect();
			return res;
		}
	}
	public boolean loadUser(String login) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDGetUser(login, this);
		if (res) {
			//тут подгружвать цели
//			DataBaseManager.BDLoadAllTargets(id, TargetList);
			DataBaseManager.Disconnect();
			return res;
		} else {
			DataBaseManager.Disconnect();
			return res;
		}
	}
	/*
	 * метод изменения пароля
	 */
	public void changePassword(String newPassword) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdatePassword(newPassword, id);
		DataBaseManager.Disconnect();	
	}
	//Создать уведомление для пользователя
	public void createNotice(Notice notice) {
		NoticeList.add(notice);
	}
	
	//Создать пункт статистики для пользователя
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	//Назначить айди пользователя
	public void setId(int idNumber) {	
		id = idNumber;
	}
	
	//Назначить имя пользователя
	public void setName(String fullName) {
		name = fullName;
	}
	
	//Назначить дату рождения пользователя
	public void setBirthDate(LocalDate dateOfBirth) {
		birthDate = dateOfBirth;	
	}
		
	//Назначить пол пользователя
	public void setGender(char gen) {
		gender = gen;
	}
	
	//Назначиьт рейтинг пользователя
	public void setRating(int Rating) {
		rating = Rating;
	}
	
	//Получить айди пользователя
	public int getId() {	
		return id;	
	}
	
	//Получить имя пользователя
	public String getName() {	
		return name;	
	}
	
	//Получить дату рождения пользователя
	public LocalDate getBirthDate() {	
		return birthDate;	
	}
	
	//Получить пол пользователя
	public char getGender() {	
		return gender;
	}
	
	//получить рейтинг пользователя
	public int getRating() {
		return rating;
	}
	public String getQuestion () throws SQLException {
		DataBaseManager.Connect();
		String res = null;
		res = DataBaseManager.BDGetQuestion(id);
		DataBaseManager.Disconnect();
		return res;
	}
	public boolean checkAnswer(String answer) throws SQLException {
		DataBaseManager.Connect();
		String answerBD = null;
		answerBD = DataBaseManager.BDGetAnswer(id);
		
		DataBaseManager.Disconnect();
		if(answerBD.equals(answer)) {
			return true;
		} else
			return false;
	}
}
