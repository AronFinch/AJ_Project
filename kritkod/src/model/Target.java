package model;

import java.util.LinkedHashSet;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//����
public class Target {
	
	private int id;
	private String label;
	private String description;
	private String IMG;
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	public static LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
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
	//��������� ��������� ���� ����
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//��������� �������� ���� ����
	public void setEndDate(LocalDate EndDate) {	
		endDate = EndDate;
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
	//�������� ��������� ���� ����
	public LocalDate getStartDate() {
		return startDate;
	}
	//�������� �������� ���� ����
	public LocalDate getEndDate() {
		return endDate;	
	}
		
	@Override
    public String toString() {
        return "label=" + label 
        		+ " Description=" + description 
        		+ " Time_Start_Target =" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() 
        		+ " Time_Fail_Target =" + endDate.getDayOfMonth()+ "." + endDate.getMonthValue();
        		
    }
}
