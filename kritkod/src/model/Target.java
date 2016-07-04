package model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.sql.SQLException;
import java.time.LocalDate;

//����
public class Target {
	
	private int id;
	private String label;
	private String description;
	private String IMG;
	private LocalDate startDate;
	private LocalDate endDate;
	private int level;
	
	public LinkedHashSet<Task> TaskList;
	
	public Target() {
		id = 0;
		label = "��������";
		description = "��������";
		IMG = "���� � ������������";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		TaskList = new LinkedHashSet<Task>();
	}
	public void clear() {
		id = 0;
		label = "��������";
		description = "��������";
		IMG = "���� � ������������";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		TaskList.clear();
	}
	//����� ������� ���������� ���� �����
	public int numberAllTasks() {
		return TaskList.size();
	}
	//����� ��������� ���������� ����������� �����
	public int numberDoteTasks() {
		int res = 0;
		Iterator<Task> itas = TaskList.iterator();
		while(itas.hasNext()) {
			Task tas = itas.next();
			if(tas.isDone())
				res++;
		}
		return res;
	}
	// ���������� ���� � ����������
	public boolean SaveTarget(int id_user) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDAddTarget(id_user, this);
		DataBaseManager.Connect();
		return res;
	}
	public boolean creatTask(Task task) throws SQLException {
		if(task.SaveTask(id)) {
			TaskList.add(task);
			return true;
		} else {
			task.clear();
			return false;
		}
	}
	//��������� ���� ����
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//������� ������ ��� ������ ����
	public void createTask(Task task) {
		TaskList.add(task);
	}
	//��������� ��������� ����
	public void setLabel(String Label) {
		label = Label;
	}
	//��������� �������� ����
	public void setDescription(String Description) {
		description = Description;
	}
	public void setIMG(String IMGTrack) {
		IMG = IMGTrack;
	}
	//��������� ��������� ���� ����
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//��������� �������� ���� ����
	public void setEndDate(LocalDate EndDate) {	
		endDate = EndDate;
	}
	public void setLevel(int Level) {
		level = Level;
	}
	//�������� ���� ����
	public int getId() {
		return id;	
	}
	//�������� ��������� ����
	public String getLabel() {
		return label;	
	}
	//�������� �������� ����
	public String getDescription() {
		return description;	
	}
	public String getIMG() {
		return IMG;
	}
	//�������� ��������� ���� ����
	public LocalDate getStartDate() {
		return startDate;
	}
	//�������� �������� ���� ����
	public LocalDate getEndDate() {
		return endDate;	
	}
	public int getLevel() {
		return level;
	}
	@Override
    public String toString() {
        return "label=" + label 
        		+ " Description=" + description 
        		+ " Time_Start_Target =" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() 
        		+ " Time_Fail_Target =" + endDate.getDayOfMonth()+ "." + endDate.getMonthValue();
        		
    }
}
