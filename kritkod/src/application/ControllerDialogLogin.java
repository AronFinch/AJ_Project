package application;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.User;
import view.Controller;

public class ControllerDialogLogin {
	
	@FXML
    private TextField Login;
	@FXML
    private TextField Password;
	
	public Controller children;  // ������ �� ���������� ���������� �����
	public Controller parent;     // ������ �� ������������ ���������� (���� ������� ���� ��� ������ �����)
	
	@FXML
	private void ClickedLogin() throws IOException{
		
		boolean loginNotNull, passwordNotNull;
		loginNotNull = false;
		passwordNotNull = !Password.getText().equals("");
		
		// �������� ����� �� �������
		if(!Login.getText().equals("")) {
			loginNotNull = true;
			if(!Password.getText().equals(""))
				passwordNotNull = true;
			else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information");
			    alert.setHeaderText("���� Password �� ������ ���� ������!");
			    alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
		    alert.setHeaderText("���� Login �� ������ ���� ������!");
		    alert.showAndWait();
		}// ����� �������� � ������� ���������
		
		
		//��� ��� ����� ��������� �� �������. ������ ����������� �����+������.
		if(loginNotNull && passwordNotNull){
			//###��� ������ ����� ����(����)
			try {
				if(Main.mainUser.loadUser(Login.getText(), Password.getText())) {//��������� ������������
					Main.primaryStage.close();
					
					Stage stage = new Stage();
					Main.primaryStage = stage;
				 	Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
					stage.setScene(new Scene(root));
					stage.setTitle("������� �����:");
					stage.show();
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information");
				    alert.setHeaderText("������ ������������ ��� ��� ������ ����� �� �����!");
				    alert.showAndWait();
				}	
			} catch (SQLException e) {
				Main.mainUser.Clear();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Information");
			    alert.setHeaderText("Error!");
			    alert.setContentText(e.getMessage());
			    alert.showAndWait();
			}
			//###��� ����� ����� ����(����)
		}
	}

	@FXML
	private void ClickedRegistry() throws IOException{
		
		//��� ��� ����� ��������� �� �������. 
		if(Login.getText() != null){
					
			Stage stage = Main.primaryStage;
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogCreateUser.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("����� ������������:");
					
		}
		
	}

	@FXML
	private void ClickedReset() throws IOException{
		//��� ��� ����� ��������� �� �������. 
		if(Login.getText() != null){
							
			Stage stage = Main.primaryStage;
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogLoginReset.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("�������������� ������:");			
							
		}
		
	}

}
