package model;


//Комментарий
public class Comment {

	private int id;
	private String text = "";
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
	public void setUserId(int idNumber) {
		
		userId = idNumber;
		
	}
	//Получить айди комментария
	public int getId(Comment comment) {
		
		return comment.id;
		
	}
	//Получить текст комментария
	public String getText(Comment comment) {
		
		return comment.text;
		
	}
	//Получить айди пользователя, написавшего комментарий
	public int getUserId(Comment comment) {
		
		return comment.userId;
		
	}
	
}
