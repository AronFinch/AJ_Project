package model;

import java.util.LinkedHashSet;
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
