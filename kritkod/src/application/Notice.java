package application;


//Уведомление
public class Notice {

	private String text = "";
	
	//Назначить текст уведомления
	public void setText(String string) {
		
		text = string;
		
	}
	//Получить текст уведомления
	public String getText(Notice notice) {
		
		return notice.text;
		
	}
	
}
