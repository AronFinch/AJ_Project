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
	
	public Controller children;  // ������ �� ���������� ���������� �����
	public Controller parent;     // ������ �� ������������ ���������� (���� ������� ���� ��� ������ �����)
	
	@FXML
	private void ClickedLogin() throws IOException{
		
		//��� ��� ����� ��������� �� �������. ������ ����������� �����+������.
		if(Login.getText() != null){
			
			Stage stage = Main.primaryStage;
		 	Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("������� �����:");
			
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
