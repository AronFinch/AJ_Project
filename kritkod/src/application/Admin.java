package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

//�������������
public class Admin {
	
	private int id;
	private String login = "";
	private String password = "";
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	
	//��������� ���� ���������������
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//��������� ����� ��������������
	public void setLogin(String log) {
		
		login = log;
		
	}
	//��������� ������ ��������������
	public void setPassword(String pass) {
		
		password = pass;
		
	}
	//��������� ��� ��������������
	public void setName(String fullName) {
		
		name = fullName;
		
	}
	//��������� ��� ��������������
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//��������� ���� �������� ��������������
	public void setBirthDate(DatePicker addDataTarget) {
		
		birthDate = addDataTarget.getValue();
		
	}
	//�������� ���� �������� ��������������
	public LocalDate getBirthDate() {
		
		return birthDate;
		
	}
	//�������� ��� ��������������
	public char getGender() {
		
		return gender;
		
	}
	//�������� ��� ��������������
	public String getName() {
		
		return name;
		
	}
	//�������� ���� ���������������
	public int getId(Admin admin) {
		
		return admin.id;
		
	}
}
