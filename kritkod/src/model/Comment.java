package model;

import java.time.LocalDate;

//�����������
public class Comment {

	private int id;
	private String text;
	private LocalDate date;
	private int userId;
	
	//��������� ���� �����������
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//��������� ����� �����������
	public void setText(String string) {	
		text = string;	
	}
	//��������� ���� ������������, ����������� �����������
	public void setUserId(int idUser) {
		userId = idUser;	
	}
	public void setDate(LocalDate Date) {
		date = Date;
	}
	//�������� ���� �����������
	public int getId() {
		return id;	
	}
	//�������� ����� �����������
	public String getText() {
		return text;	
	}
	public LocalDate getDate() {
		return date;
	}
	//�������� ���� ������������, ����������� �����������
	public int getUserId(Comment comment) {
		
		return comment.userId;
		
	}
	
}
