package view;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;

public class Controller {
	
	public Controller children;  // Ссылка на контроллер поражаемой формы
	public Controller parent;     // Ссылка на родительский контроллер (если таковой есть для данной формы)
    public static boolean newTargetOk = false;
	
	 @FXML
	    private Button buttonNewTask;
	 
	 @FXML
	    private Button buttonTest;
	 
	 @FXML
	 	private FlowPane addTarget;
	 
	 @FXML
	    private Label LabelTargetName;
	 @FXML
	    private Label LabelTaskCount;
	 @FXML
	    private Label LabelDayCount;
	 @FXML
	    private ProgressBar TargetProgressBar;
	 
	 @FXML
	    public void ShowDialogTargetNew(ActionEvent actionEvent) throws IOException{
		 
		 newTargetOk = true;
			Target tempTarget = new Target();
			boolean okClicked = Main.showTargetEditDialog(tempTarget);
			if (okClicked) {
				Main.getTargetData().add(tempTarget);
				if(newTargetOk){
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource("newTarget.fxml"));
				 	Parent root = loader.load();
					addTarget.getChildren().add(root);
					//SetTargetSource(tempTarget);
				}
				newTargetOk = false;
				//SetTargetSource(tempTarget);
			}
		 
	    }
	 
	 private void SetTargetSource(Target tempTarget) {
		// TODO Auto-generated method stub
		 
		 	LabelTargetName.setText("Hello Hell!");
		 	LabelTargetName.setText(tempTarget.getLabel());
			LabelTaskCount.setText("Число задач... Заглушка!");
			int Month = tempTarget.getEndDate().getMonth().getValue() - tempTarget.getStartDate().getMonth().getValue();
			int day = tempTarget.getEndDate().getDayOfMonth() - tempTarget.getEndDate().getDayOfMonth();
			LabelDayCount.setText("Месяцев: " + Month + " Дней: " + day);
		
	}

	@FXML
	    public void ShowDialogTask(ActionEvent actionEvent) throws IOException{
		 	
		 	Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogTask.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
			stage.setTitle("Создать задачу:");
			stage.show();
	    }
	 
	 @FXML
	    public void ShowDialogCreateUser(ActionEvent actionEvent) throws IOException{
		 	
		 	Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogCreateUser.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
			stage.setTitle("Создать пользователя:");
			stage.show();
	    }
}
