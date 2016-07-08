package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane; // ������ ��� ����������� �������� �������� �����.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane1; // ������ ��� ����������� �������� ����������� �����.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane2; // ������ ��� ����������� �������� ����������� �����.
	 
	 @FXML
	 	private FlowPane ListTopUser; // ���� ��� ����������� �������� ���� ������������� �� ��������.
	 
	 @FXML
	 	private FlowPane ActiveAchivePane; //������ ��� ����������� �������� ����������.
	 @FXML
	 	private FlowPane ActiveClosestTaskPane; //������ ��� ����������� �������� ��������� �����.
	 
	 @FXML
	    private Label NameUser; //���������� ������������ ��� ������������ �� ������� ������
	 @FXML
	 	private ComboBox NameStyle;
	 @FXML
		private BarChart<String, Integer> mainStat; //����������

	 
		ObservableList<String> styleList = FXCollections.observableArrayList("������", "�������", "���������", "�����");
	 
	 /**
	  * ����� �������� ����� ����. ���������� ��� ������� �� ������ "����� ����".
	  * @param actionEvent
	  * @throws IOException
	  */
	 @FXML
	    public void ShowDialogTargetNew(ActionEvent actionEvent) throws IOException{
		 
			Target tempTarget = new Target();
			ControllerDialogTarget.target = tempTarget;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ControllerDialogTarget.class.getResource("dialogTarget.fxml"));
		 	Parent root = loader.load();
		 	root.getStylesheets().clear();
		 	root.getStylesheets().add(Main.style);
		 	
		 	Stage stage = new Stage();
		 	Scene scene = new Scene(root);
		 	
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.primaryStage);
			stage.setTitle("����:");
			
		 	ControllerDialogTarget controller = loader.getController();
	        controller.SetDialogStage(stage);
	        controller.SetTarget(tempTarget);
	        
			stage.showAndWait();
			
			initializeTargetPane();
			}

	 /**
	  * ����� ������������� ����� ����.
	  * ��� ��������� ������������� ��� ���.
	  */
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		NameUser.setText(Main.mainUser.getName()); // ������ ��� ������������
		initializeListTopUser();  // ������������� ����
		initializeStatisticPane();// ����� //����� ������������� ����������
		initializeTargetPane();	//������������� �����.
		initializeClosestTaskPane();// ����� //������������� ��������� �����
		initializeAchivePane();// ����� // ������������� ����������
		NameStyle.setItems(styleList); //������ ������ ��������� � ��������� NameStyle
	}
	
	@FXML
    public void ShowDialogUserReset() throws IOException{
		
		ControllerUserInfo.newUser = Main.mainUser;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogUserInfo.fxml"));
	 	Parent root = loader.load();
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);

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
		 ActiveTargetFlowPane.getChildren().clear();
		 ActiveTargetFlowPane1.getChildren().clear();
		 ActiveTargetFlowPane2.getChildren().clear();
		 
		 Iterator<Target> itr = Main.mainUser.TargetList.iterator();
			while (itr.hasNext()) {
				ControllerTargetPane.newTarget = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("miniTarget.fxml"));
					 	Parent root = loader.load();
					 	root.getStylesheets().clear();
					 	root.getStylesheets().add(Main.style);
					 	ControllerTargetPane controller = loader.getController();
					 	controller.root = root;
					 	controller.pane = ActiveTargetFlowPane;
					 	controller.pane1 = ActiveTargetFlowPane1;
					 	controller.pane2 = ActiveTargetFlowPane2;
					 	
					 	if(ControllerTargetPane.newTarget.getApproved()){
					 		ActiveTargetFlowPane1.getChildren().add(root);
					 	}else if(ControllerTargetPane.newTarget.getEndDate().isAfter(LocalDate.now())){
					 		
					 		ActiveTargetFlowPane.getChildren().add(root);
					 	}
					 	else{
					 		
					 		ActiveTargetFlowPane2.getChildren().add(root);
					 	}
					 	
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
		 
		 ListTopUser.getChildren().clear();
		 
		 Iterator<User> itr = Main.otherUsers.iterator();
			while (itr.hasNext()) {
				ControllerMiniUser.newUser = itr.next();
					try {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(Controller.class.getResource("miniUser.fxml"));
					 	Parent root = loader.load();
					 	root.getStylesheets().clear();
					 	root.getStylesheets().add(Main.style);
					 	ListTopUser.getChildren().add(root);
					 	
					 	
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
		
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		series.setName("��� ����������");
		series.getData().add(new XYChart.Data<>("����� �����", Main.mainUser.TargetList.size())); //����� �����
		series.getData().add(new XYChart.Data<>("���������", Main.mainUser.getStatistics().getNumberDoneTarget())); //����� ���������
		series.getData().add(new XYChart.Data<>("����������", Main.mainUser.getStatistics().getNumberFaildTarget())); //����� ����������
		series.getData().add(new XYChart.Data<>("�����", Main.mainUser.getStatistics().getAllBalls())); //�����
		series.getData().add(new XYChart.Data<>("����� � ����", Main.mainUser.getRating())); //����� � ����
		
		mainStat.getData().add(series);
		
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
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
		stage.setScene(new Scene(root));
		stage.setTitle("�����������:");
		stage.show();
	}
	 
	 @FXML
	 public void chooseStyle() throws IOException
	 {
		 if(NameStyle.getValue().equals("������")) {
			 Main.style = this.getClass().getResource("CSS_BlackStyle.css").toString();
			 
		 } else if(NameStyle.getValue().equals("�������")) {
			 Main.style = this.getClass().getResource("CSS_PinkStyle.css").toString();
		 } else if(NameStyle.getValue().equals("�����")){
			 Main.style = this.getClass().getResource("CSS_Vkstyle.css").toString();
		 }
		 else{
			 Main.style = this.getClass().getResource("CSS_PurpureStyle.css").toString();
		 }
		 
		 Main.primaryStage.close();
			
			Stage stage = new Stage();
			Main.primaryStage = stage;
	 		Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
	 		root.getStylesheets().clear();
		 	root.getStylesheets().add(Main.style);
	 		stage.setScene(new Scene(root));
			stage.setTitle("������� �����:");
			stage.show();
			
	 }
	
}
