package model;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

//������
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
		description = "��������";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		level = 0;
		isDone = false;
		approved = false;
		CommentList = new LinkedHashSet<Comment>();
	}
	public void clear() {
		id = 0;
		description = "��������";
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
	//��������� ���� ������
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//��������� ��������� ������
	public void setDescription(String Description) { 
		description = Description; 
	} 
	//��������� ��������� ���� ������
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//��������� �������� ���� ������
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
	//�������� ���� ������
	public int getId() {	
		return id;	
	}
	//�������� ��������� ������
	public String getDescription() {
		return description;
	}
	//�������� ��������� ���� ������
	public LocalDate getStartDate() {	
		return startDate;	
	}
	//�������� �������� ���� ������
	public LocalDate getEndDate() {	
		return endDate;
	}
	public int getLevel() {
		return level;
	}
	public boolean isDone() {
		return isDone;
	}
	// �������� ��������� �� ������
	public boolean isFaild() {
		boolean res = false;
		if(!isDone && endDate.isAfter(LocalDate.now()))
			res = true;
		return res;
	}
	public boolean getApproved() {
		return approved;
	}
	//������� ����������� ��� ������ ������
	public void createComment(Comment comment) {	
		CommentList.add(comment);	
	}
		
}