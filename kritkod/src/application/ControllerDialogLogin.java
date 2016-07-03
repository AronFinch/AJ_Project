package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.Controller;

public class ControllerDialogLogin {
	
	@FXML
    private TextField Login;
	@FXML
    private TextField Password;
	
	public Controller children;  // Ссылка на контроллер поражаемой формы
	public Controller parent;     // Ссылка на родительский контроллер (если таковой есть для данной формы)
	
	@FXML
	private void ClickedLogin(){
		
		//Это для теста переходов по экранам. Аналог правильному Логин+пароль.
		if((Login.getText()=="001")&&(Password.getText()=="001")){
			
			
			
		}
		
	}

	@FXML
	private void ClickedRegistry() throws IOException{
		
		//Это для теста переходов по экранам. 
		if(Login.getText() != null){
					
			Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogCreateUser.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.primaryStage);
			stage.setTitle("Новый пользователь:");
			stage.show();
					
		}
		
	}

	@FXML
	private void ClickedReset() throws IOException{
		
		//Это для теста переходов по экранам. 
		if(Login.getText() != null){
							
			Stage stage = new Stage();
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogLoginReset.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(Main.primaryStage);
			stage.setTitle("Новый пользователь:");
			stage.show();				
							
		}
		
	}

}
