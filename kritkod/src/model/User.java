package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import javafx.scene.control.DatePicker;

//������������
public class User {

	private int id;
	private String name;
	private LocalDate birthDate = LocalDate.now();
	private char gender;
	private int rating;
	
	public Statistics statistics = new Statistics();
	public LinkedHashSet<Notice> NoticeList = new LinkedHashSet<Notice>();
	public LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	/*
	 * ����������� �� ���������
	 * ������ ������������ 
	 * ����� �������� ����� ����� ���������� �� ���� ������, ��� ������ ��������� ����
	 */
	public User() {
		Clear();
	}
	
	/*
	 * ����� ����������� ����� ���������� ������������
	 */
	public void Clear() {
		id = 0; // ��������� � ���� � 1
		name = "������������ �� ������"; // 
		birthDate = LocalDate.of(1000, 1, 1); // ����� ����� ����� ����
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //��������� �������� � ���� 300
	}
	
	/*
	 * ����� ���������� ������������ � ���� ������
	 */
	public void SaveUser(String login,
						 String password,
						 String secriteQuestion,
						 String answer) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDAddUser(login, password, secriteQuestion, answer, this);
		DataBaseManager.Disconnect();
	}
	/*
	 * ������ �������� ������������ �� ���� ������
	 */
	public void loadUser(String login, String password) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDGetUser(login, password, this);
		DataBaseManager.Disconnect();
	}
	public void loadUser(String login) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDGetUser(login, this);
		DataBaseManager.Disconnect();
	}
	/*
	 * ����� ��������� ������
	 */
	public void changePassword(String newPassword) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdatePassword(newPassword, id);
		DataBaseManager.Disconnect();	
	}
	//������� ����������� ��� ������������
	public void createNotice(Notice notice) {
		NoticeList.add(notice);
	}
	
	//������� ����� ���������� ��� ������������
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	//��������� ���� ������������
	public void setId(int idNumber) {	
		id = idNumber;
	}
	
	//��������� ��� ������������
	public void setName(String fullName) {
		name = fullName;
	}
	
	//��������� ���� �������� ������������
	public void setBirthDate(LocalDate dateOfBirth) {
		birthDate = dateOfBirth;	
	}
		
	//��������� ��� ������������
	public void setGender(char gen) {
		gender = gen;
	}
	
	//��������� ������� ������������
	public void setRating(int Rating) {
		rating = Rating;
	}
	
	//�������� ���� ������������
	public int getId() {	
		return id;	
	}
	
	//�������� ��� ������������
	public String getName() {	
		return name;	
	}
	
	//�������� ���� �������� ������������
	public LocalDate getBirthDate() {	
		return birthDate;	
	}
	
	//�������� ��� ������������
	public char getGender() {	
		return gender;
	}
	
	//�������� ������� ������������
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
