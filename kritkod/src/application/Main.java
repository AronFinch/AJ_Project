package application;
	
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.Task;
import model.User;
import view.ControllerDialogTarget;
import view.ControllerDialogTask;
import view.ControllerTargetPane;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	public static Stage primaryStage;
	public static User mainUser;
	public static LinkedHashSet<User> otherUsers;

	public static LinkedHashSet<FlowPane> ListFlowPane= new LinkedHashSet<FlowPane>();
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	@Override
	public void start(Stage stage) {
		try {
			TestData();
			this.primaryStage = stage;
			initLoginDialog();
			//initRootLayout();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void TestData() throws IOException {
		// TODO Auto-generated method stub
		int i = 3;
		Target target = new Target();
		TextField text = new TextField();
		TextArea text1 = new TextArea();
		DatePicker date = new DatePicker();
		LocalDate specificDate = LocalDate.now();
		LocalDate specificDate1 = LocalDate.of(2016, 8, 15 + i);
		while(i!=0){
		target = new Target();
		text.setText("Имя цели " + i + " создано!");
		target.setLabel(text.getText());
		text1.setText("Описание цели " + i + " создано!");
		target.setDescription(text1.getText());
		date.setValue(specificDate);
		target.setStartDate(date.getValue());
		date.setValue(specificDate1.of(2016, 8, 15 + i));
		target.setEndDate(date.getValue());
		TargetList.add(target);
		i--;
		}
		
	}

	private void initLoginDialog() throws IOException {
		// TODO Auto-generated method stub
		
	 	Parent root = FXMLLoader.load(Main.class.getResource("dialogLogin.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Авторизация");
		primaryStage.show();
	}

	private void initRootLayout() throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
	 	Parent root = loader.load(Main.class.getResource("rootStage1.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("demo");
		primaryStage.show();
		
	}
	
    public Stage getPrimaryStage() {
    	return primaryStage;
    }

	public static void main(String[] args) {
		mainUser = new User();
		otherUsers = new LinkedHashSet<User>();
		launch(args);
	}

	public static LinkedHashSet<Target> getTargetData() {
		// TODO Auto-generated method stub
		return TargetList;
	}

	public static boolean showTargetEditDialog(Target tempTarget) throws IOException {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogTarget.fxml"));
	 	Parent root = loader.load();
	 	
	 	
	 	Stage stage = new Stage();
	 	
	 	ControllerDialogTarget controller = loader.getController();
        controller.SetDialogStage(stage);
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		stage.setTitle("Цель:");
		stage.showAndWait();
		
		return controller.isOkClicked();
	}
}
