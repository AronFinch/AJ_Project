package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

//������������
public class User {
	
	static int idNew = 0;

	private int id;
	private String login = "";
	private String password = "";
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	
	//��������� ���� ������������
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//��������� ����� ������������
	public void setLogin(TextField log) {
		
		login = log.getText();
		
	}
	//��������� ������ ������������
	public void setPassword(TextField pass) {
		
		password = pass.getText();
		
	}
	//��������� ��� ������������
	public void setName(TextField fullName) {
		
		name = fullName.getText();
		
	}
	//��������� ��� ������������
	public void setGender(char gen) {
		
		gender = gen;
		
	}
	//��������� ���� �������� ������������
	public void setBirthDate(DatePicker addDataTarget) {
		
		birthDate = addDataTarget.getValue();
		
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
	
}
