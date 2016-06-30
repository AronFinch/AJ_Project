package application;

import java.time.LocalDate;
import java.util.LinkedHashSet;

import javafx.scene.control.DatePicker;

//�������������
public class Admin {
	
	private int id;
	private String name = "";
	private char gender = 'f';
	private LocalDate birthDate = LocalDate.now();
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	
	//��������� ���� ���������������
	public void setId(int idNumber) {
		
		id = idNumber;
		
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
	//������� ����
	public void createTarget(Target target) {
		
		TargetList.add(target);
		
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
