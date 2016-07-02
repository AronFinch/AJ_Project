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
	public static LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
	private String label = "";
	private String description = "";
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	//Назначить айди цели
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//Создать задачу для данной цели
	public void createTask(Task task) {
		
		TaskList.add(task);
		
	}
	//Назначить заголовок цели
	public void setLabel(TextField addNameTarget) throws IOException {
		
		label = addNameTarget.getText();
		
	}
	//Назначить описание цели
	public void setDescription(TextArea addDiscriptionTarget) throws IOException {
		
		description = addDiscriptionTarget.getText();
		
	}
	//Назначить начальную дату цели
	public void setStartDate(DatePicker addDataTarget) {
		
		startDate = addDataTarget.getValue();
		
	}
	//Назначить конечную дату цели
	public void setEndDate(DatePicker addDataTarget) {
		
		endDate = addDataTarget.getValue();
		
	}
	//Получить айди цели
	public int getId(Target target) {
		
		return target.id;
		
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
