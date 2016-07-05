package model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashSet;

//������������
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
	 * ����������� �� ���������
	 * ������ ������������ 
	 * ����� �������� ����� ����� ���������� �� ���� ������, ��� ������ ��������� ����
	 */
	public User() {
		id = 0; // ��������� � ���� � 1
		name = "������������ �� ������"; // 
		birthDate = LocalDate.of(1000, 1, 1); // ����� ����� ����� ����
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //��������� �������� � ���� 300		
		TargetList = new LinkedHashSet<Target>();
		NoticeList = new LinkedHashSet<Notice>();
		statistics = new Statistics();
	}
	
	/*
	 * ����� ����������� ����� ���������� ������������
	 */
	public void clear() {
		id = 0; // ��������� � ���� � 1
		name = "������������ �� ������"; // 
		birthDate = LocalDate.of(1000, 1, 1); // ����� ����� ����� ����
		gender = 'h'; //Genderfluid Helisexual
		rating = 0; //��������� �������� � ���� 300		
		TargetList.clear();
		NoticeList.clear();
		Statistics.clear();
	}
	
	/*
	 * ����� ���������� ������������ � ���� ������
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
	 * ������ �������� ������������ �� ���� ������
	 */
	public boolean loadUser(String login, String password) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDGetUser(login, password, this);
		if (res) {
			//��� ����������� ����
			DataBaseManager.BDLoadAllTargets(id, TargetList);
			//����� ���������� ������
			Iterator<Target> itar = TargetList.iterator();
			while (itar.hasNext()) {
				Target tar = itar.next();
				DataBaseManager.BDloadAllTasks(tar.getId(), tar.TaskList);
			}
			// � ��� ������ ���� �����������
			DataBaseManager.BDLoadAllNotice(id, NoticeList);
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
			//��� ����������� ����
			DataBaseManager.BDLoadAllTargets(id, TargetList);
			//����� ���������� ������
			Iterator<Target> itar = TargetList.iterator();
			while (itar.hasNext()) {
				Target tar = itar.next();
				DataBaseManager.BDloadAllTasks(tar.getId(), tar.TaskList);
			}
			// � ��� ������ ���� �����������
			DataBaseManager.BDLoadAllNotice(id, NoticeList);
			DataBaseManager.Disconnect();
			return res;
		} else {
			DataBaseManager.Disconnect();
			return res;
		}
	}
	/*
	 * ����� ��������� ������
	 */
	public void changePassword(String newPassword) throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdatePassword(newPassword, id);
		DataBaseManager.Disconnect();	
	}
	
	public boolean creatTarget(Target target) throws SQLException {
		if(target.SaveTarget(id)) {
			TargetList.add(target);
			return true;
		} else {
			target.clear();
			return false;
		}
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
	//�������� ��������� ������
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
	//���������� ����������� �����
	public int numberDoneTarget() {
		int res = 0;
		Iterator<Target> itar = TargetList.iterator();
		while(itar.hasNext()) {
			Target tar = itar.next();
			if(tar.numberAllTasks() == tar.numberDoneTasks())
				res++;
		}
		return res;
	}
	//���������� ���� �����
	public int numberAllTarget() {
		return TargetList.size();
	}
}
