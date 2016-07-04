package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import model.Notice;
import model.Target;
import model.User;

public class Controller implements Initializable {
	
	public Controller children;  // ������ �� ���������� ���������� �����
	public Controller parent;     // ������ �� ������������ ���������� (���� ������� ���� ��� ������ �����)
    public static boolean newTargetOk = false;
	
	 @FXML
	    private Button buttonNewTask;
	 
	 @FXML
	    private Button buttonTest;
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane;
	 @FXML
	 	private FlowPane ActiveTargetFlowPane1;
	 @FXML
	 	private FlowPane ActiveTargetFlowPane2;
	 @FXML
	 	private FlowPane ActiveUserFlowPane;
	 
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
				stage.setTitle("������� �����:");
				stage.show();
				
				}
				newTargetOk = false;
			}
	 
	  public void ShowTarget() throws IOException{
		 
		  Iterator<Target> itr = Main.mainUser.TargetList.iterator();
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
			stage.setTitle("������� ������:");
			stage.show();
	    }
	 
	 @FXML
	    public void ShowDialogCreateUser(ActionEvent actionEvent) throws IOException{
		 	
		 	Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogCreateUser.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
			stage.setTitle("������� ������������:");
			stage.show();
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		Iterator<Target> itr = Main.mainUser.TargetList.iterator();
		while (itr.hasNext()) {
			ControllerTargetPane.target = itr.next();
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("newTarget.fxml"));
				 	Parent root = loader.load();
				 	ActiveTargetFlowPane.getChildren().add(root);
				 	/*
				 	if(itr.next().TaskList.getEndDate().isAfter(LocalDate.now())&& itr.next().){
				 		ActiveTargetFlowPane.getChildren().add(root);
				 	}else if(false){
				 		
				 		ActiveTargetFlowPane1.getChildren().add(root);
				 	}
				 	else{
				 		
				 		ActiveTargetFlowPane2.getChildren().add(root);
				 	}
				 	*/
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	@FXML
    public void ShowDialogUserReset() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogUserInfo.fxml"));
	 	Parent root = loader.load();

	 	Stage stage = new Stage();
	 	
	 	ControllerUserInfo controller = loader.getController();
        controller.SetDialogStage(stage);
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(Main.primaryStage);
		stage.setTitle("������������");
		stage.show();
	}
	
	 @FXML
	    public void ShowTabUser() throws IOException{
		 	
		 //��� ����� ����� ������ �������������
		 
		 Iterator<User> itr = Main.otherUsers.iterator();
			while (itr.hasNext()) {
				ControllerUserPane.user = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("FormUser.fxml"));
					 	Parent root = loader.load();
					 		ActiveUserFlowPane.getChildren().add(root);
					 	
					 	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			
		 
	    }
}
