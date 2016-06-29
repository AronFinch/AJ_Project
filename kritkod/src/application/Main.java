package application;
	
import java.util.LinkedHashSet;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	private static Stage stage;
	private static Scene scene;
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	@Override
	public void start(Stage stage) {
		try {
			this.stage = stage;
			Parent root = FXMLLoader.load(getClass().getResource("demo.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("demo");
			stage.show();
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
