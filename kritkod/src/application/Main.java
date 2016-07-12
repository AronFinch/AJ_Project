package application;
	
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedHashSet;
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Target;
import model.User;
import view.Controller;
import view.ControllerDialogTarget;
import view.ControllerTargetPane;
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
	public static String style = Controller.class.getResource("CSS_Vkstyle.css").toString();

	public static LinkedHashSet<FlowPane> ListFlowPane= new LinkedHashSet<FlowPane>();
	public static LinkedHashSet<Target> TargetList = new LinkedHashSet<Target>();
	
	public static FlowPane pane;
	public static FlowPane pane1;
	public static FlowPane pane2;
	
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
	 	root.getStylesheets().clear();
	 	root.getStylesheets().add(Main.style);
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
	
	public static void MiniTargetInic(){
		
		pane.getChildren().clear();
		pane1.getChildren().clear();
		pane2.getChildren().clear();
		
		Iterator<Target> itr = Main.mainUser.TargetList.iterator();
		while (itr.hasNext()) {
			ControllerTargetPane.newTarget = itr.next();
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Controller.class.getResource("miniTarget.fxml"));
				 	Parent root = loader.load();
				 	root.getStylesheets().clear();
				 	root.getStylesheets().add(Main.style);
				 	ControllerTargetPane controller1 = loader.getController();
				 	controller1.root = root;
				 	controller1.pane = pane;
				 	controller1.pane1 = pane1;
				 	controller1.pane2 = pane2;
				 	
				 	if(ControllerTargetPane.newTarget.numberDoneTasks() == ControllerTargetPane.newTarget.numberAllTasks()&&ControllerTargetPane.newTarget.numberAllTasks()!=0){
				 		pane1.getChildren().add(root);
				 	}else if(ControllerTargetPane.newTarget.getEndDate().isAfter(LocalDate.now())){
				 		
				 		pane.getChildren().add(root);
				 	}
				 	else{
				 		
				 		pane2.getChildren().add(root);
				 	}
				 	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}	
		
	}
}
