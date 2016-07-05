package model;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

//Задача
public class Task {
	
	private int id;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int level;
	private boolean isDone;
	private boolean approved;
	
	public LinkedHashSet<Comment> CommentList;
	
	public Task() {
		id = 0;
		description = "описание";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		level = 0;
		isDone = false;
		approved = false;
		CommentList = new LinkedHashSet<Comment>();
	}
	public void clear() {
		id = 0;
		description = "описание";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		level = 0;
		isDone = false;
		approved = false;
		CommentList.clear();
	}
	public void saveChange() throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdateTask(this);
		DataBaseManager.Disconnect();
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
	public void Done(boolean done) {
		isDone = done;
	}
	public void setApproved(boolean Approved) {
		approved = Approved;
	}
	//Получить айди задачи
	public int getId() {	
		return id;	
	}
	//Получить заголовок задачи
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
	// сообщает провалена ли задача
	public boolean isFaild() {
		boolean res = false;
		if(!isDone && endDate.isAfter(LocalDate.now()))
			res = true;
		return res;
	}
	public boolean getApproved() {
		return approved;
	}
	//Создать комментарий для данной задачи
	public void createComment(Comment comment) {	
		CommentList.add(comment);	
	}
		
}