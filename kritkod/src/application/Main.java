package application;
	
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import view.ControllerDialogTarget;
import view.ControllerDialogTask;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.fxml.FXMLLoader;



public class Main extends Application {
	private static Stage primaryStage;
	private static Scene scene;
	public static LinkedHashSet<FlowPane> ListFlowPane= new LinkedHashSet<FlowPane>();
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	@Override
	public void start(Stage stage) {
		try {
			this.primaryStage = stage;
			initRootLayout();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
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
