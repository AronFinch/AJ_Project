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
	
	public Controller children;  // Ссылка на контроллер поражаемой формы.
	public Controller parent;     // Ссылка на родительский контроллер (если таковой есть для данной формы).
	
	 
	 @FXML
	 	private FlowPane ActiveTargetFlowPane; // Панель для отображения фабрикой активных целей.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane1; // Панель для отображения фабрикой выполненных целей.
	 @FXML
	 	private FlowPane ActiveTargetFlowPane2; // Панель для отображения фабрикой проваленных задач.
	 
	 @FXML
	 	private FlowPane ListTopUser; // Лист для отображения фабрикой Всех пользователей по рейтингу.
	 
	 @FXML
	 	private FlowPane ActiveAchivePane; //Панель для отображения фабрикой Достижений.
	 @FXML
	 	private FlowPane ActiveClosestTaskPane; //Панель для отображения фабрикой ближайших задач.
	 
	 @FXML
	    private Label NameUser; //Переменная отображающая имя пользователя на главном экране
	 @FXML
	 	private ComboBox NameStyle;
	 @FXML
		private BarChart<String, Integer> mainStat; //Статистика

	 
		ObservableList<String> styleList = FXCollections.observableArrayList("Черный", "Розовый", "Пурпурный", "Синий");
	 
	 /**
	  * Метод создания новой цели. Вызывается при нажатии на кнопку "Новая цель".
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
			stage.setTitle("Цель:");
			
		 	ControllerDialogTarget controller = loader.getController();
	        controller.SetDialogStage(stage);
	        controller.SetTarget(tempTarget);
	        
			stage.showAndWait();
			
			initializeTargetPane();
			}

	 /**
	  * Метод инициализации всего окна.
	  * Вся первичная инициализация идёт тут.
	  */
	@Override
	public void initialize(URL location, ResourceBundle resources)  {
		// TODO Auto-generated method stub
		NameUser.setText(Main.mainUser.getName()); // Задали имя пользователя
		initializeListTopUser();  // Инициализация топа
		initializeStatisticPane();// Пусто //Метод инициализации Статистики
		initializeTargetPane();	//Инициализация целей.
		initializeClosestTaskPane();// Пусто //Инициализация Ближайших целей
		initializeAchivePane();// Пусто // Инициализация Достижений
		NameStyle.setItems(styleList); //Задали список вариантов в комбоБокс NameStyle
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
		stage.setTitle("Пользователь");
		stage.show();
	}
	
	 /**
	  * Инициализация вкладок с целями. Фабрика с условиями.
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
	  * Инициализация Листа с топом. Фабрика.
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
		
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		series.setName("Моя статистика");
		series.getData().add(new XYChart.Data<>("Целей всего", Main.mainUser.TargetList.size())); //Целей всего
		series.getData().add(new XYChart.Data<>("Выполнено", Main.mainUser.getStatistics().getNumberDoneTarget())); //Целей выполнено
		series.getData().add(new XYChart.Data<>("Просрочено", Main.mainUser.getStatistics().getNumberFaildTarget())); //Целей просрочено
		series.getData().add(new XYChart.Data<>("Баллы", Main.mainUser.getStatistics().getAllBalls())); //Баллы
		series.getData().add(new XYChart.Data<>("Место в топе", Main.mainUser.getRating())); //Место в топе
		
		mainStat.getData().add(series);
		
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
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
		stage.setScene(new Scene(root));
		stage.setTitle("Авторизация:");
		stage.show();
	}
	 
	 @FXML
	 public void chooseStyle() throws IOException
	 {
		 if(NameStyle.getValue().equals("Черный")) {
			 Main.style = this.getClass().getResource("CSS_BlackStyle.css").toString();
			 
		 } else if(NameStyle.getValue().equals("Розовый")) {
			 Main.style = this.getClass().getResource("CSS_PinkStyle.css").toString();
		 } else if(NameStyle.getValue().equals("Синий")){
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
			stage.setTitle("Главный экран:");
			stage.show();
			
	 }
	
}
