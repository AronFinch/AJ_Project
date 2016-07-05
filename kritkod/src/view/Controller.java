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
	
	public Controller children;  // Ссылка на контроллер поражаемой формы.
	public Controller parent;     // Ссылка на родительский контроллер (если таковой есть для данной формы).
    public static boolean newTargetOk = false; //Принимает значение совершённого в дочернем окне действия. 1-принять, 0-отмена.
	
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane; // Панель для отображения фабрикой активных целей.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane1; // Панель для отображения фабрикой выполненных целей.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane2; // Панель для отображения фабрикой проваленных задач.
	 
	 @FXML
	 	private ListView ListTopUser; // Лист для отображения фабрикой Всех пользователей по рейтингу.
	 
	 @FXML
	 	private FlowPane ActiveAchivePane; //Панель для отображения фабрикой Достижений.
	 @FXML
	 	private FlowPane ActiveClosestTaskPane; //Панель для отображения фабрикой ближайших задач.
	 
	 @FXML
	    private Label NameUser; //Переменная отображающая имя пользователя на главном экране
	 
	 /**
	  * Метод создания новой цели. Вызывается при нажатии на кнопку "Новая цель".
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
				stage.setTitle("Главный экран:");
				stage.show();
				
				}
				newTargetOk = false;
			}

	 /**
	  * Метод инициализации всего окна.
	  * Вся первичная инициализация идёт тут.
	  */
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		NameUser.setText(Main.mainUser.getName()); // Задали имя пользователя
		//initializeListTopUser(); Переписать. // Инициализация топа
		initializeStatisticPane();// Пусто //Метод инициализации Статистики
		initializeTargetPane();	//Инициализация целей.
		initializeClosestTaskPane();// Пусто //Инициализация Ближайших целей
		initializeAchivePane();// Пусто // Инициализация Достижений
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
		stage.setTitle("Пользователь");
		stage.show();
	}
	
	 /**
	  * Инициализация вкладок с целями. Фабрика с условиями.
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
	  * Инициализация Листа с топом. Фабрика.
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
	  * Инициализация вкладки с ближайшими задачами.
	  */
	private void initializeClosestTaskPane() {
		// TODO Auto-generated method stub
			
	}
	
	/**
	 * Инициализация вкладки с Достижениями.
	 */
	private void initializeAchivePane() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Инициализация Статистики на главной вкладке.
	 */
	private void initializeStatisticPane() {
		// TODO Auto-generated method stub
		
	}
	
	 @FXML
	public void exit() throws IOException
	{
		Main.mainUser.clear();
		Main.otherUsers.clear();
		//отчистили главного пользователя и списки других пользователей
		Main.primaryStage.close();
		Stage stage = new Stage();
		Main.primaryStage = stage;
	 	Parent root = FXMLLoader.load(getClass().getResource("../application/dialogLogin.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Авторизация:");
		stage.show();
	}
	
}
