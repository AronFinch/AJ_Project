package model;

import java.time.LocalDate;
import java.util.Iterator;

//—татистика
public class Statistics {

	private int[] graph;
	private int allBalls;
	private int numberDoneTarget;
	private int numberFaildTarget;
	
	public Statistics() {
		clear();
	}
	public void update(User user) {
		Iterator<Target> itar;
		Iterator<Task> itas;
//		LocalDate today = LocalDate.now();
		LocalDate day;
		
		itar = user.TargetList.iterator();
		
		while(itar.hasNext()) {
			itas = itar.next().TaskList.iterator();
			while(itas.hasNext()) {
				Task tas = itas.next();
				if(tas.isDone()) {
					for(int i = 0; i < 7; i++) {
						day = LocalDate.now().minusDays(7+i);
						if(tas.getEndDate().equals(day))
							graph[i] += tas.getLevel();
					}
				}
			}
			for(int i = 1; i < 7; i++)
				graph[i] += graph[i - 1];
			// тут известен график
			itar = user.TargetList.iterator();
			while(itar.hasNext()) {
				itas = itar.next().TaskList.iterator();
				while(itas.hasNext()) {
					Task tas = itas.next();
					if(tas.isDone())
						allBalls += tas.getLevel();
				}
			}
			// тут подсчитали количество всех болов
			itar = user.TargetList.iterator();
			while(itar.hasNext()) {
				Target tar = itar.next();
				if(tar.numberAllTasks() == tar.numberDoneTasks())
					numberDoneTarget++;
			}
			while(itar.hasNext()) {
				Target tar = itar.next();
				if(tar.numberFaildTask() > 0)
					numberFaildTarget++;
			}
		}
	}
	public void clear() {
		graph = new int[7];
		for(int i = 0; i < 7; i++)
			graph[i] = 0;
		allBalls = 0;
		numberDoneTarget = 0;
		numberFaildTarget = 0;
	}
	public int[] getGraph() {
		return graph;
	}
	public int getAllBalls() {
		return allBalls;
	}
	public int getNumberDoneTarget() {
		return numberDoneTarget;
	}
	public int getNumberFaildTarget() {
		return numberFaildTarget;
	}
}
