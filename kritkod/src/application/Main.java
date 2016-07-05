package application;
	
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.User;
import view.ControllerDialogTarget;
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
	public static Target LocalTarget = null;
	@Override
	public void start(Stage stage) {
		try {
			this.primaryStage = stage;
			initLoginDialog();
			//initRootLayout();
			
		} catch(Exception e) {
			e.printStackTrace();
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
		LocalTarget = tempTarget;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ControllerDialogTarget.class.getResource("dialogTarget.fxml"));
	 	Parent root = loader.load();
	 	
	 	Stage stage = new Stage();
	 	
		stage.setScene(new Scene(root));
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		stage.setTitle("Цель:");
		
	 	ControllerDialogTarget controller = loader.getController();
        controller.SetDialogStage(stage);
        controller.SetTarget(tempTarget);
        
		stage.showAndWait();
		
		return controller.isOkClicked();
	}
}
