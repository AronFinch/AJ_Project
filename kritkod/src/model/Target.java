package model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.sql.SQLException;
import java.time.LocalDate;

//����
public class Target {
	
	private int id;
	private String label;
	private String IMG;
	private LocalDate startDate;
	private LocalDate endDate;
	private String reward;
	private boolean approved;
	public LinkedHashSet<Task> TaskList;
	
	public Target() {
		id = 0;
		label = "��������";
		IMG = "���� � ������������";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		reward = "�������";
		approved = false;
		TaskList = new LinkedHashSet<Task>();
	}
	public void clear() {
		id = 0;
		label = "��������";
		IMG = "���� � ������������";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		reward = "�������";
		approved = false;
		TaskList.clear();
	}
	//����� ������� ���������� ���� �����
	public int numberAllTasks() {
		return TaskList.size();
	}
	//����� ��������� ���������� ����������� �����
	public int numberDoneTasks() {
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
	//��� ����������
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
	//��������� �������� ����
	public void setLabel(String Label) {
		label = Label;
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
	//��������� �������
	public void setReward(String Reward) {
		reward = Reward;
	}
	//��������� ����
	public void setApproved(boolean Approved) {
		approved = Approved;
	}
	//�������� ���� ����
	public int getId() {
		return id;	
	}
	//�������� �������� ����
	public String getDescription() {
		return label;	
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
	//�������� �������
	public String getReward() {
		return reward;
	}
	//��������� � ������������ ������
	public boolean getApproved() {
		return approved;
	}
   
}
