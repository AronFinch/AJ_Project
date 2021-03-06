package view;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

public class ControllerUserInfo implements Initializable {
	
	@FXML
    private Label name;
	@FXML
    private DatePicker birthDate;
	@FXML
    private TextField userName;
	@FXML
    private SplitMenuButton gender;
	@FXML
	private Button ButtonSave;
	@FXML
	private Button ButtonCancel;
	@FXML
	private Button buttonResetPerson;
	@FXML
	private AreaChart<String, Integer> StatisticsChart;
	
	Stage stage;
	
	static User newUser = Main.mainUser;
	User user;
	
	
	@FXML
    public void ResetPerson(ActionEvent actionEvent) {
		if(user.getId()==Main.mainUser.getId()||Main.mainUser.getId()==1){
		ButtonCancel.setVisible(true);
		ButtonSave.setVisible(true);
		birthDate.setDisable(false);
		userName.setDisable(false);
		gender.setDisable(false);
		}
		
	}

	@FXML
    public void Save(ActionEvent actionEvent) {
		//���������...
		
		ButtonCancel.setVisible(false);
		ButtonSave.setVisible(false);
		birthDate.setDisable(true);
		userName.setDisable(true);
		gender.setDisable(true);
		
	}
	
	@FXML
    public void SetMan(ActionEvent actionEvent) {
		gender.setText("�������");
	}
	
	@FXML
    public void SetWoman(ActionEvent actionEvent) {
		gender.setText("�������");
	}
	
	@FXML
    public void Cancel(ActionEvent actionEvent) {
		//this.initialize(Controller.class.getResource("dialogUserInfo.fxml"), null);
		
		ButtonCancel.setVisible(false);
		ButtonSave.setVisible(false);
		birthDate.setDisable(true);
		userName.setDisable(true);
		gender.setDisable(true);
		
	}
	
	@FXML
    public void Back(ActionEvent actionEvent) {
	
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		if(newUser.getId()!=Main.mainUser.getId()&&Main.mainUser.getId()!=1){
			buttonResetPerson.setVisible(false);
			buttonResetPerson.setDisable(false);
		}
		
		userName.setText(newUser.getName());
		birthDate.setValue(newUser.getBirthDate());
		//gender.setText(value);
		name.setText(newUser.getName());
		
		user = newUser;
		
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		series.getData().add(new XYChart.Data<>("1", user.TargetList.size())); //����� �����
		series.getData().add(new XYChart.Data<>("2", user.getStatistics().getNumberDoneTarget())); //����� ���������
		series.getData().add(new XYChart.Data<>("3", user.getStatistics().getNumberFaildTarget())); //����� ����������
		series.getData().add(new XYChart.Data<>("4", user.getStatistics().getAllBalls())); //�����
		series.getData().add(new XYChart.Data<>("5", user.getRating())); //����� � ����
		
		StatisticsChart.getData().add(series);
	}

	public void SetDialogStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}
	
}
