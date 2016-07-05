package model;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

//Задача
public class Task {
	
	private int id;
	private String label;
	private LocalDate startDate;
	private LocalDate endDate;
	private int level;
	private boolean isDone;
	
	public LinkedHashSet<Comment> CommentList;
	
	public Task() {
		id = 0;
		label = "Название";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		isDone = false;
		CommentList = new LinkedHashSet<Comment>();
	}
	public void clear() {
		id = 0;
		label = "Название";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		isDone = false;
		CommentList.clear();
	}
	public boolean SaveTask(int id_target) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDAddTask(id_target, this);
		DataBaseManager.Disconnect();
		return res;
	}
	//Назначить айди задачи
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//Назначить заголовок задачи
	public void setLabel(String Label) { 
		label = Label; 
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
	public void Done(boolean done) {
		isDone = done;
	}
	
	//Получить айди задачи
	public int getId() {	
		return id;	
	}
	//Получить заголовок задачи
	public String getLabel() {
		return label;
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
	//Создать комментарий для данной задачи
	public void createComment(Comment comment) {	
		CommentList.add(comment);	
	}
		
}