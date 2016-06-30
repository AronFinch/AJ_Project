package application;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

//Задача
public class Task {
	
	private String label = "";
	private String description = "";
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	
	public void setLabel(TextField addNameTask) throws IOException { 

		label = addNameTask.getText(); 

	} 

	public void setDescription(TextArea addDiscriptionTask) throws IOException { 

		description = addDiscriptionTask.getText(); 

	}
	
	public void setStartDate(DatePicker addDataTask) {
		
		startDate = addDataTask.getValue();
		
	}
	
	public void setEndDate(DatePicker addDataTask) {
		
		endDate = addDataTask.getValue();
		
	}
	
	public String getLabel() {
		
		return label;
	}
	
	public String getDescription() {
		
		return description;
		
	}
	
	public LocalDate getStartDate() {
		
		return startDate;
		
	}
	
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