package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerUserInfo implements Initializable {
	
	@FXML
    private DatePicker birthDate;
	@FXML
    private TextField user;
	@FXML
    private SplitMenuButton gender;
	@FXML
	private Button ButtonSave;
	@FXML
	private Button ButtonCancel;
	@FXML
	private AreaChart<String, Integer> StatisticsChart;
	
	Stage stage;
	
	
	@FXML
    public void ResetPerson(ActionEvent actionEvent) {
		ButtonCancel.setVisible(true);
		ButtonSave.setVisible(true);
		birthDate.setDisable(false);
		user.setDisable(false);
		
	}

	@FXML
    public void Save(ActionEvent actionEvent) {
		//���������...
		
		ButtonCancel.setVisible(false);
		ButtonSave.setVisible(false);
		birthDate.setDisable(true);
		user.setDisable(true);
		
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
		user.setDisable(true);
		
	}
	
	@FXML
    public void Back(ActionEvent actionEvent) {
	
		stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
		series.getData().add(new XYChart.Data<>("1", 15)); //������ == ����� ������������ ���������� (��� ����, �����������, �� ����������� ��� ���-�� ���). ����� == � �������� ��������
		series.getData().add(new XYChart.Data<>("2", 23));
		series.getData().add(new XYChart.Data<>("3", 34));
		series.getData().add(new XYChart.Data<>("4", 45));
		series.getData().add(new XYChart.Data<>("5", 19));
		
		StatisticsChart.getData().add(series);
	}

	public void SetDialogStage(Stage stage) {
		// TODO Auto-generated method stub
		this.stage = stage;
	}
	
}
