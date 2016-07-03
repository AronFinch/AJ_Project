package view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;

public class Controller implements Initializable {
	
	public Controller children;  // Ссылка на контроллер поражаемой формы
	public Controller parent;     // Ссылка на родительский контроллер (если таковой есть для данной формы)
    public static boolean newTargetOk = false;
	
	 @FXML
	    private Button buttonNewTask;
	 
	 @FXML
	    private Button buttonTest;
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane;
	 
	 @FXML
	    public void ShowDialogTargetNew(ActionEvent actionEvent) throws IOException{
		 
		 newTargetOk = true;
			Target tempTarget = new Target();
			boolean okClicked = Main.showTargetEditDialog(tempTarget);
			if (okClicked) {
				//Main.getTargetData().add(tempTarget);
				Stage stage = Main.primaryStage;
			 	Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
				stage.setScene(new Scene(root));
				stage.setTitle("Главный экран:");
				stage.show();
				
				}
				newTargetOk = false;
			}
	 
	  public void ShowTarget() throws IOException{
		 
		  Iterator<Target> itr = Main.TargetList.iterator();
			while (itr.hasNext()) {
				ControllerTargetPane.target = itr.next();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("newTarget.fxml"));
				 	Parent root = loader.load();
					ActiveTargetFlowPane.getChildren().add(root);
			}
					
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

	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		Iterator<Target> itr = Main.TargetList.iterator();
		while (itr.hasNext()) {
			ControllerTargetPane.target = itr.next();
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("newTarget.fxml"));
				 	Parent root = loader.load();
					ActiveTargetFlowPane.getChildren().add(root);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
