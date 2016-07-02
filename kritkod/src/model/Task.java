package model;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

//Задача
public class Task {
	
	private int id;
	private String label = "";
	private String description = "";
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	//Назначить айди задачи
	public void setId(int idNumber) {
		
		id = idNumber;
		
	}
	//Назначить заголовок задачи
	public void setLabel(TextField addNameTask) throws IOException { 

		label = addNameTask.getText(); 

	} 
	//Назначить описание задачи
	public void setDescription(TextArea addDiscriptionTask) throws IOException { 

		description = addDiscriptionTask.getText(); 

	}
	//Назначить начальную дату задачи
	public void setStartDate(DatePicker addDataTask) {
		
		startDate = addDataTask.getValue();
		
	}
	//Назначить конечную дату задачи
	public void setEndDate(DatePicker addDataTask) {
		
		endDate = addDataTask.getValue();
		
	}
	//Получить айди задачи
	public int getId(Task task) {
		
		return task.id;
		
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
	
	@Override
    public String toString() {
        return "label=" + label 
        		+ " Description=" + description 
        		+ " Time_Start_Task =" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() 
        		+ " Time_Fail_Task =" + endDate.getDayOfMonth()+ "." + endDate.getMonthValue();
        		
    }
		
}