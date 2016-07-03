package model;


import java.time.LocalDate;
import java.util.LinkedHashSet;

//Задача
public class Task {
	
	private int id;
	private String label;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int level;
	private boolean isDone;
	public static LinkedHashSet<Comment> CommentList = new LinkedHashSet<Comment>();
	//Назначить айди задачи
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//Назначить заголовок задачи
	public void setLabel(String Label) { 
		label = Label; 
	} 
	//Назначить описание задачи
	public void setDescription(String Description) { 
		description = Description; 
	}
	//Назначить начальную дату задачи
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//Назначить конечную дату задачи
	public void setEndDate(LocalDate EndDate) {
		endDate = EndDate;
	}
	public void setLevel(int Level) {
		level = Level;
	}
	public void Done() {
		isDone = true;
	}
	
	//Получить айди задачи
	public int getId() {	
		return id;	
	}
	//Получить заголовок задачи
	public String getLabel() {
		return label;
	}
	//Получить описание задачи
	public String getDescription() {
		return description;	
	}
	//Получить начальную дату задачи
	public LocalDate getStartDate() {	
		return startDate;	
	}
	//Получить конечную дату задачи
	public LocalDate getEndDate() {	
		return endDate;
	}
	public int getLevel() {
		return level;
	}
	public boolean isDone() {
		return isDone;
	}
	@Override
    public String toString() {
        return "label=" + label 
        		+ " Description=" + description 
        		+ " Time_Start_Task =" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() 
        		+ " Time_Fail_Task =" + endDate.getDayOfMonth()+ "." + endDate.getMonthValue();
        		
    }
	//Создать комментарий для данной задачи
	public void createComment(Comment comment) {	
		CommentList.add(comment);	
	}
		
}