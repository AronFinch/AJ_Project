package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.User;
import view.Controller;

public class ControllerDialogLoginNewPassword {
	
	@FXML
	private PasswordField NewPassword;
	@FXML
	private PasswordField NewPassword2;
	

	@FXML
    public void ButtonAction(ActionEvent actionEvent) throws IOException{
		if(NewPassword.getText().equals(NewPassword2.getText())) {
			try {
				Main.mainUser.changePassword(NewPassword.getText());
				//на этом моменте выполнить переход
				//прогрузил других пользователей
				ArrayList<String> logins;
				
				DataBaseManager.Connect();
				logins = DataBaseManager.BDGetOtherLogins(Main.mainUser.getId());
				DataBaseManager.Disconnect();
				
				for(int i = 0; i < logins.size(); i++) {
					User user = new User();
					user.loadUser(logins.get(i));
					Main.otherUsers.add(user);
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
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Information");
	    	    alert.setHeaderText("Error!");
	    	    alert.setContentText(e.getMessage());
	    	    alert.showAndWait();
			}
		} else {
			NewPassword.clear();
			NewPassword2.clear();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
    	    alert.setHeaderText("Неверныйе пароли!");
    	    alert.setContentText("Попробуйте ещё раз.");
    	    alert.showAndWait();
		}
	}

}
