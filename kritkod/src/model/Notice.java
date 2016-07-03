package model;

import java.time.LocalDate;

//Уведомление
public class Notice {

	private int id;
	private String text;
	private LocalDate date;
	
	//Назначить текст уведомления
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
	//Получить текст уведомления
	public String getText() {
		return text;	
	}
	public LocalDate getDate() {
		return date;
	}
	
}
