package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

//������������
public class User {
	
//	static int idNew = 0;

	private int id;
//	private String login;
//	private String password;
	private String name;
	private char gender;
	private LocalDate birthDate;
	
	//����������� �� ���������� 
	// ������� � ��������� ������� ��������
	public User() {
		id = 0; 
//		login = "";
//		password = "";
		name = "";
		gender = 'n';
		birthDate = null;
	}
	
	//��������� ���� ������������
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//��������� ����� ������������
//	public void setLogin(TextField log) {
		
//		login = log.getText();
		
//	}
	//��������� ������ ������������
//	public void setPassword(TextField pass) {
		
//		password = pass.getText();
		
//	}
	//��������� ��� ������������
	public void setName(String Name) {
		
		name = Name;
		
	}
	//��������� ��� ������������
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//��������� ���� �������� ������������
	public void setBirthDate(LocalDate date) {
		
		birthDate = date;
		
	}
	//�������� ���� �������� ������������
	public LocalDate getBirthDate() {
		
		return birthDate;
		
	}
	//�������� ��� ������������
	public char getGender() {
		
		return gender;
		
	}
	//�������� ��� ������������
	public String getName() {
		
		return name;
		
	}
	//�������� ���� ������������
	public int getId(User user) {
		
		return user.id;
		
	}
		
		// ��������� ������������ �� ��
	public void LoadUser(String login, String password) throws ClassNotFoundException, SQLException {
		DataBaseManager.Connect();
		ResultSet rs = DataBaseManager.BDGetUser(login, password);
		if(rs.next()) {
			id = rs.getInt("id_user");
			name = rs.getString("name_user");
			gender = rs.getString("gender").charAt(0);
			birthDate = rs.getDate("date_of_birth").toLocalDate();
		} else {
			System.err.println("������������ �� ������");
		}
		DataBaseManager.Disconnect();
	}
	//���������� ������������ � ���� ������
	public void SeveUser(String login, String password, String secritQuestion, String answer) throws ClassNotFoundException, SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDAddUser(login, password, secritQuestion, answer, this);
		DataBaseManager.Disconnect();
	}
}
