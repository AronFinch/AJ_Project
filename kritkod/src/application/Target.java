package application;

import java.util.LinkedHashSet;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//Цель
public class Target {
	
	public static LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
	private String label = "";
	private String description = "";
	LocalDate limitation1 = LocalDate.now();
	LocalDate limitation2 = LocalDate.now();
	
	public void createTask(Task task) {
		
		TaskList.add(task);
		
	}
	
	public void setLabel(TextField addNameTarget) throws IOException {
		
		label = addNameTarget.getText();
	}
	
	public void setDescription(TextArea addDiscriptionTarget) throws IOException {
		
		description = addDiscriptionTarget.getText();
	}
	
	public void setLimitation1(DatePicker addDataTarget) {
		
		limitation1 = addDataTarget.getValue();
	}
	
	public void setLimitation2(DatePicker addDataTarget) {
		
		limitation2 = addDataTarget.getValue();
	}
	
	public String getLabel() {
		
		return label;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public LocalDate getLimitation1() {
		
		return limitation1;
	}
	
	public LocalDate getLimitation2() {
		
		return limitation2;
	}
		
	@Override
    public String toString() {
        return "label=" + label 
        		+ " Description=" + description 
        		+ " Time_Start_Target =" + limitation1.getDayOfMonth() + "." + limitation1.getMonthValue() 
        		+ " Time_Fail_Target =" + limitation2.getDayOfMonth()+ "." + limitation2.getMonthValue();
        		
    }
}
