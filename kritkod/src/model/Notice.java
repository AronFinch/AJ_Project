package model;

import java.time.LocalDate;

//�����������
public class Notice {

	private int id;
	private String text;
	private LocalDate date;
	
	//��������� ����� �����������
	public void setId(int ID) {
		id = ID;
	}
	public void setText(String Text) {
		text = Text;	
	}
	public void setDate(LocalDate Date) {
		date = Date;
	}
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
	
}
