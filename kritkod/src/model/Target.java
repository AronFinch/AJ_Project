package model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.sql.SQLException;
import java.time.LocalDate;

//÷ель
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
		label = "";
		IMG = "";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		reward = "";
		approved = false;
		TaskList = new LinkedHashSet<Task>();
	}
	public void clear() {
		id = 0;
		label = "";
		IMG = "";
		startDate = LocalDate.now();
		endDate = LocalDate.now();
		reward = "";
		approved = false;
		TaskList.clear();
	}
	//метод посчЄта количества всех задач
	public int numberAllTasks() {
		return TaskList.size();
	}
	//метод подсчЄтка количества выполненных задач
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
	//число проваленных задачь
	public int numberFaildTask() {
		int res = 0;
		Iterator<Task> itas = TaskList.iterator();
		while(itas.hasNext()) {
			Task tas = itas.next();
			if(tas.isFaild())
				res++;
		}
		return res;
	}
	public void saveChange() throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDUpdateTarget(this);
		DataBaseManager.Disconnect();
	}
	// сохранение новой цели в базеданных
	public boolean SaveTarget(int id_user) throws SQLException {
		boolean res;
		DataBaseManager.Connect();
		res = DataBaseManager.BDAddTarget(id_user, this);
		DataBaseManager.Disconnect();
		return res;
	}
	//это переделать
	public boolean createTask(Task task) throws SQLException {
		if(task.SaveTask(id)) {
			TaskList.add(task);
			return true;
		} else {
			task.clear();
			return false;
		}
	}
	//Ќазначить айди цели
	public void setId(int idNumber) {
		id = idNumber;	
	}
	//—оздать задачу дл€ данной цели
	//Ќазначить описание цели
	public void setLabel(String Label) {
		label = Label;
	}
	public void setIMG(String IMGTrack) {
		IMG = IMGTrack;
	}
	//Ќазначить начальную дату цели
	public void setStartDate(LocalDate StartDate) {
		startDate = StartDate;	
	}
	//Ќазначить конечную дату цели
	public void setEndDate(LocalDate EndDate) {	
		endDate = EndDate;
	}
	//назначить награду
	public void setReward(String Reward) {
		reward = Reward;
	}
	//одобрение цели
	public void setApproved(boolean Approved) {
		approved = Approved;
	}
	//ѕолучить айди цели
	public int getId() {
		return id;	
	}
	//ѕолучить описание цели
	public String getLabel() {
		return label;	
	}
	public String getIMG() {
		return IMG;
	}
	//ѕолучить начальную дату цели
	public LocalDate getStartDate() {
		return startDate;
	}
	//ѕолучить конечную дату цели
	public LocalDate getEndDate() {
		return endDate;	
	}
	//получить награду
	public String getReward() {
		return reward;
	}
	//запросить о одобренности задачи
	public boolean getApproved() {
		return approved;
	}
	public void delete() throws SQLException {
		DataBaseManager.Connect();
		DataBaseManager.BDDeleteTarget(id);
		DataBaseManager.Disconnect();
		clear();
	}
}
