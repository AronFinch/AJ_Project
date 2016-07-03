package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

//ѕользователь
public class User {

	private int id;
	private String name;
	private LocalDate birthDate;
	private char gender;
	private int rating;
	
	public Statistics statistics = new Statistics();
	public LinkedHashSet<Notice> NoticeList = new LinkedHashSet<Notice>();
	public LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	/*
	 * конструктор по умолчанию
	 * создаЄт пользовател€ 
	 * после зоздани€ нужно будет прогрузить из базы данных, или самому заполнить пол€
	 */
	public User() {
		Clear();
	}
	
	/*
	 * метод заполн€ющий класс дефолтными результатами
	 */
	public void Clear() {
		id = 0; // нумераци€ в базе с 1
		name = "пользователь не найден"; // 
		birthDate = LocalDate.of(1000, 1, 1); // пусть будет така€ дата
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //дефолтное значение в базе 300
	}
	
	/*
	 * метод сохранени€ пользовател€ в базе данных
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
	 * методы загрузки пользовател€ из базы данных
	 */
	public boolean loadUser(String login, String password) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDGetUser(login, password, this);
		DataBaseManager.Disconnect();
		return res;
	}
	public boolean loadUser(String login) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDGetUser(login, this);
		DataBaseManager.Disconnect();
		return res;
	}
	/*
	 * метод изменени€ парол€
	 */
	public void changePassword(String newPassword) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdatePassword(newPassword, id);
		DataBaseManager.Disconnect();	
	}
	//—оздать уведомление дл€ пользовател€
	public void createNotice(Notice notice) {
		NoticeList.add(notice);
	}
	
	//—оздать пункт статистики дл€ пользовател€
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	//Ќазначить айди пользовател€
	public void setId(int idNumber) {	
		id = idNumber;
	}
	
	//Ќазначить им€ пользовател€
	public void setName(String fullName) {
		name = fullName;
	}
	
	//Ќазначить дату рождени€ пользовател€
	public void setBirthDate(LocalDate dateOfBirth) {
		birthDate = dateOfBirth;	
	}
		
	//Ќазначить пол пользовател€
	public void setGender(char gen) {
		gender = gen;
	}
	
	//Ќазначиьт рейтинг пользовател€
	public void setRating(int Rating) {
		rating = Rating;
	}
	
	//ѕолучить айди пользовател€
	public int getId() {	
		return id;	
	}
	
	//ѕолучить им€ пользовател€
	public String getName() {	
		return name;	
	}
	
	//ѕолучить дату рождени€ пользовател€
	public LocalDate getBirthDate() {	
		return birthDate;	
	}
	
	//ѕолучить пол пользовател€
	public char getGender() {	
		return gender;
	}
	
	//получить рейтинг пользовател€
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
