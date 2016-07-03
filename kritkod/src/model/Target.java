package model;

import java.util.LinkedHashSet;
import java.time.LocalDate;

//Цель
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
		label = "название";
		description = "описание";
		IMG = "путь к изображениею";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		TaskList = new LinkedHashSet<Task>();
	}
	public void clear() {
		id = 0;
		label = "название";
		description = "описание";
		IMG = "путь к изображениею";
		startDate = LocalDate.of(1000, 1, 1);
		endDate = LocalDate.of(1000, 1, 1);
		level = 0;
		TaskList.clear();
	}
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
	public void setIMG(String IMGTrack) {
		IMG = IMGTrack;
	}
	//Назначить начальную дату цели
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//Назначить конечную дату цели
	public void setEndDate(LocalDate EndDate) {	
		endDate = EndDate;
	}
	public void setLevel(int Level) {
		level = Level;
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
	public String getIMG() {
		return IMG;
	}
	//Получить начальную дату цели
	public LocalDate getStartDate() {
		return startDate;
	}
	//Получить конечную дату цели
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
