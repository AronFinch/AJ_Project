package model;

import java.util.LinkedHashSet;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//Цель
public class Target {
	
	private int id;
	private String label;
	private String description;
	private String IMG;
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	public static LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
	//Назначить айди цели
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//Создать задачу для данной цели
	public void createTask(Task task) {
		TaskList.add(task);
	}
	//Назначить заголовок цели
	public void setLabel(String Label) {
		label = Label;
	}
	//Назначить описание цели
	public void setDescription(String Description) {
		description = Description;
	}
	//Назначить начальную дату цели
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//Назначить конечную дату цели
	public void setEndDate(LocalDate EndDate) {	
		endDate = EndDate;
	}
	//Получить айди цели
	public int getId() {
		return id;	
	}
	//Получить заголовок цели
	public String getLabel() {
		return label;	
	}
	//Получить описание цели
	public String getDescription() {
		return description;	
	}
	//Получить начальную дату цели
	public LocalDate getStartDate() {
		return startDate;
	}
	//Получить конечную дату цели
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
