package application;

import java.util.LinkedHashSet;
import java.io.IOException;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//Цель
public class Target {
	
	public static LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
	private String label = "";
	private String description = "";
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = LocalDate.now();
	
	public void createTask(Task task) {
		
		TaskList.add(task);
		
	}
	
	public void setLabel(TextField addNameTarget) throws IOException {
		
		label = addNameTarget.getText();
		
	}
	
	public void setDescription(TextArea addDiscriptionTarget) throws IOException {
		
		description = addDiscriptionTarget.getText();
		
	}
	
	public void setStartDate(DatePicker addDataTarget) {
		
		startDate = addDataTarget.getValue();
		
	}
	
	public void setEndDate(DatePicker addDataTarget) {
		
		endDate = addDataTarget.getValue();
		
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
        		+ " Time_Start_Target =" + startDate.getDayOfMonth() + "." + startDate.getMonthValue() 
        		+ " Time_Fail_Target =" + endDate.getDayOfMonth()+ "." + endDate.getMonthValue();
        		
    }
}
