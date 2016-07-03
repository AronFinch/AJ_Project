package model;

import java.time.LocalDate;

//Комментарий
public class Comment {

	private int id;
	private String text;
	private LocalDate date;
	private int userId;
	
	//Назначить айди комментария
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//Назначить текст комментария
	public void setText(String string) {	
		text = string;	
	}
	//Назначить айди пользователя, написавшего комментарий
	public void setUserId(int idUser) {
		userId = idUser;	
	}
	public void setDate(LocalDate Date) {
		date = Date;
	}
	//Получить айди комментария
	public int getId() {
		return id;	
	}
	//Получить текст комментария
	public String getText() {
		return text;	
	}
	public LocalDate getDate() {
		return date;
	}
	//Получить айди пользователя, написавшего комментарий
	public int getUserId(Comment comment) {
		
		return comment.userId;
		
	}
	
}
