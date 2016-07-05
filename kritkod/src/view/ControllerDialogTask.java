package view;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Target;
import model.Task;

public class ControllerDialogTask {
	
	public LinkedHashSet<Task> TaskList = new LinkedHashSet<Task>();
	
	@FXML
	 private DatePicker addDataTaskStart;
	
	@FXML
	 private DatePicker addDataTaskEnd;
	
	@FXML
	private TextField addNameTask;
	
	@FXML
	private TextArea addDiscriptionTask;
	
	@FXML
	private Label labelAddTarget;
	
	  public void addNewTask(ActionEvent actionEvent){
		  Task newTask = new Task();
		  //newTask.setDescription(addDiscriptionTask.getText());
		  newTask.setLabel(addNameTask.getText());
		  newTask.setStartDate(addDataTaskStart.getValue());
		  newTask.setEndDate(addDataTaskEnd.getValue());
		  
		  TaskList.add(newTask);
		  
		  Iterator<Task> itr = TaskList.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next().toString()
						+ ",");
			}
			System.out.println("\n");
	  }
	

	

}
