package view;

import java.io.IOException;
import java.util.Iterator;

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
		  try {
			  newTask.setDescription(addDiscriptionTask);
			  newTask.setLabel(addNameTask);
			  newTask.setStartDate(addDataTaskStart);
			  newTask.setEndDate(addDataTaskEnd);
			  
			  Target.TaskList.add(newTask);
	
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		  }
		  
		  Iterator<Task> itr = Target.TaskList.iterator();
			while (itr.hasNext()) {
				System.out.println(itr.next().toString()
						+ ",");
			}
			System.out.println("\n");
	  }
	

	

}
