package application;

import java.time.LocalDate;

import javafx.scene.control.DatePicker;

//������������
public class User {

	private int id;
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	
	//��������� ���� ������������
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//��������� ��� ������������
	public void setName(String fullName) {
		
		name = fullName;
		
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
