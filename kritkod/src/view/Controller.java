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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Notice;
import model.Target;
import model.User;

public class Controller implements Initializable {
	
	public Controller children;  // ������ �� ���������� ���������� �����.
	public Controller parent;     // ������ �� ������������ ���������� (���� ������� ���� ��� ������ �����).
    public static boolean newTargetOk = false; //��������� �������� ������������ � �������� ���� ��������. 1-�������, 0-������.
	
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane; // ������ ��� ����������� �������� �������� �����.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane1; // ������ ��� ����������� �������� ����������� �����.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane2; // ������ ��� ����������� �������� ����������� �����.
	 
	 @FXML
	 	private ListView ListTopUser; // ���� ��� ����������� �������� ���� ������������� �� ��������.
	 
	 @FXML
	 	private FlowPane ActiveAchivePane; //������ ��� ����������� �������� ����������.
	 @FXML
	 	private FlowPane ActiveClosestTaskPane; //������ ��� ����������� �������� ��������� �����.
	 
	 @FXML
	    private Label NameUser; //���������� ������������ ��� ������������ �� ������� ������
	 
	 /**
	  * ����� �������� ����� ����. ���������� ��� ������� �� ������ "����� ����".
	  * @param actionEvent
	  * @throws IOException
	  */
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

	 /**
	  * ����� ������������� ����� ����.
	  * ��� ��������� ������������� ��� ���.
	  */
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		NameUser.setText(Main.mainUser.getName()); // ������ ��� ������������
		//initializeListTopUser(); ����������. // ������������� ����
		initializeStatisticPane();// ����� //����� ������������� ����������
		initializeTargetPane();	//������������� �����.
		initializeClosestTaskPane();// ����� //������������� ��������� �����
		initializeAchivePane();// ����� // ������������� ����������
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
	
	 /**
	  * ������������� ������� � ������. ������� � ���������.
	  */
	 public void initializeTargetPane(){
		 
		 Iterator<Target> itr = Main.mainUser.TargetList.iterator();
			while (itr.hasNext()) {
				ControllerTargetPane.target = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("newTarget.fxml"));
					 	Parent root = loader.load();
					 	//ControllerTargetPane controller = loader.getController();
					 	
					 	ActiveTargetFlowPane.getChildren().add(root);
					 	
//				        controller.target = itr.next();
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
	 
	 /**
	  * ������������� ����� � �����. �������.
	  */
	 public void initializeListTopUser(){
		 
		 Iterator<User> itr = Main.otherUsers.iterator();
			while (itr.hasNext()) {
				ControllerUserPane.user = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("FormUser.fxml"));
					 	Parent root = loader.load();
					 	//ListTopUser.getChildren().add(root);
					 	
					 	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		 
	 }

	 /**
	  * ������������� ������� � ���������� ��������.
	  */
	private void initializeClosestTaskPane() {
		// TODO Auto-generated method stub
			
	}
	
	/**
	 * ������������� ������� � ������������.
	 */
	private void initializeAchivePane() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * ������������� ���������� �� ������� �������.
	 */
	private void initializeStatisticPane() {
		// TODO Auto-generated method stub
		
	}
	
	 @FXML
	public void exit() throws IOException
	{
		Main.mainUser.clear();
		Main.otherUsers.clear();
		//��������� �������� ������������ � ������ ������ �������������
		Main.primaryStage.close();
		Stage stage = new Stage();
		Main.primaryStage = stage;
	 	Parent root = FXMLLoader.load(getClass().getResource("../application/dialogLogin.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("�����������:");
		stage.show();
	}
	
}
