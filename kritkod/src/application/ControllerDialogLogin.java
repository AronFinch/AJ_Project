package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.stream.EventFilter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataBaseManager;
import model.User;
import view.Controller;

public class ControllerDialogLogin {
	
	@FXML
    private TextField Login;
	@FXML
    private TextField Password;
	@FXML
	private Label LoginHelp;
	@FXML
	private Label PasswordHelp;
	
	public Controller children;  // ������ �� ���������� ���������� �����
	public Controller parent;     // ������ �� ������������ ���������� (���� ������� ���� ��� ������ �����)
	
	private boolean loginOk;
	private boolean passwordOk;
	
	@FXML
	private void LoginKeyPress(KeyEvent kf) {
		String key = kf.getCode().getName();
		String log = Login.getText();
		if(key.equals("Enter") && loginOk) {
			if(log.length() > 0) {
				Password.requestFocus();
			} else {
				loginOk = false;
				LoginHelp.setText("����� �� ����� ���� ������!");
				LoginHelp.setOpacity(1);
				LoginHelp.setDisable(false);
			}
		} else {
			if(log.length() <= 20) {
				loginOk = true;
				LoginHelp.setOpacity(0);
				LoginHelp.setDisable(true);
				for(int i = 0; i < log.length(); i++) {
					if(log.charAt(i) == ' ') {
						loginOk = false;
						LoginHelp.setText("����� �� ������ ��������� �������!");
						LoginHelp.setOpacity(1);
						LoginHelp.setDisable(false);
						break;
					} else {
						LoginHelp.setOpacity(0);
						LoginHelp.setDisable(true);
						loginOk = true;
					}
				}
			} else {
				loginOk = false;
				LoginHelp.setText("����� �� ��������� 20 ��������!");
				LoginHelp.setOpacity(1);
				LoginHelp.setDisable(false);
			}
		}		
	}
	@FXML
	private void PasswordKeyPress(KeyEvent kf) throws IOException {
		String key = kf.getCode().getName();
		String pass = Password.getText();
		System.out.println(pass);
		if(key.equals("Enter") && passwordOk) {
			if(pass.length() > 0) {
				ClickedLogin();
			} else {
				passwordOk = false;
				PasswordHelp.setText("������ �� ����� ���� ������!");
				PasswordHelp.setOpacity(1);
				PasswordHelp.setDisable(false);
			}
		} else {
			if(pass.length() <= 20) {
				passwordOk = true;
				PasswordHelp.setOpacity(0);
				PasswordHelp.setDisable(true);
				for(int i = 0; i < pass.length(); i++) {
					if(pass.charAt(i) == ' ') {
						passwordOk = false;
						PasswordHelp.setText("������ �� ������ ��������� �������!");
						PasswordHelp.setOpacity(1);
						PasswordHelp.setDisable(false);
						break;
					} else {
						PasswordHelp.setOpacity(0);
						PasswordHelp.setDisable(true);
						passwordOk = true;
					}
				}
			} else {
				loginOk = false;
				LoginHelp.setText("������ �� ��������� 20 ��������!");
				LoginHelp.setOpacity(1);
				LoginHelp.setDisable(false);
			}
		}		
	}
	@FXML
	private void ClickedLogin() throws IOException{
		
		// �������� ����� �� �������
		if(!Login.getText().equals("")) {	
			Password.requestFocus();
			if(!Password.getText().equals("")) {
				if(loginOk && passwordOk) {
					LoginHelp.setOpacity(0);
					LoginHelp.setDisable(true);
					PasswordHelp.setOpacity(0);
					PasswordHelp.setDisable(true);
					try {
						if(Main.mainUser.loadUser(Login.getText(), Password.getText())) {//��������� ������������
						//��� ��������� ������ �������������
							ArrayList<String> logins;
						
							DataBaseManager.Connect();
							logins = DataBaseManager.BDGetOtherLogins(Main.mainUser.getId());
							DataBaseManager.Disconnect();
						
							for(int i = 0; i < logins.size(); i++) {
								User user = new User();
								user.loadUser(logins.get(i));
								Main.otherUsers.add(user);
							}
						//��������� ������ �������������
							Main.primaryStage.close();
						
							Stage stage = new Stage();
							Main.primaryStage = stage;
					 		Parent root = FXMLLoader.load(Controller.class.getResource("FXMLDocument.fxml"));
					 		root.getStylesheets().clear();
						 	root.getStylesheets().add(Main.style);
					 		stage.setScene(new Scene(root));
							stage.setTitle("������� �����:");
							stage.show();
						} else {
							LoginHelp.setText("������ ������������ �� ����������!");
							LoginHelp.setOpacity(1);
							LoginHelp.setDisable(false);
						
							PasswordHelp.setText("��� ������ ����� �������!");
							PasswordHelp.setOpacity(1);
							PasswordHelp.setDisable(false);
						
							Login.requestFocus();
						}	
					} catch (SQLException e) {
						Main.mainUser.clear();
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Information");
				    	alert.setHeaderText("Error!");
				    	alert.setContentText(e.getMessage());
				    	alert.showAndWait();
					}
				}
			} else {
				PasswordHelp.setText("������ �� ����� ���� ������!");
				PasswordHelp.setOpacity(1);
				PasswordHelp.setDisable(false);
				Password.requestFocus();
			}
		} else {
			LoginHelp.setText("����� �� ����� ���� ������!");
			LoginHelp.setOpacity(1);
			LoginHelp.setDisable(false);
			Login.requestFocus();
/*			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information");
		    alert.setHeaderText("���� Login �� ������ ���� ������!");
		    alert.showAndWait();*/
		}// ����� �������� � ������� ���������
		
	}

	@FXML
	private void ClickedRegistry() throws IOException{
		
		//��� ��� ����� ��������� �� �������. 
		if(Login.getText() != null){
					
			Stage stage = Main.primaryStage;
		 	Parent root = FXMLLoader.load(getClass().getResource("dialogCreateUser.fxml"));
		 	root.getStylesheets().clear();
		 	root.getStylesheets().add(Main.style);
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
		 	root.getStylesheets().clear();
		 	root.getStylesheets().add(Main.style);
			stage.setScene(new Scene(root));
			stage.setTitle("�������������� ������:");			
							
		}
		
	}

}
