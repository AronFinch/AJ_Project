package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Target;

public class ControllerAchievementsPane implements Initializable {
	
	@FXML
    private DatePicker Date;
	@FXML
	private ImageView Image;
	@FXML
    private Label Name;
	@FXML
    private Label Bonus;
	
	static Target target = new Target();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println(3);
		Image.setImage(new javafx.scene.image.Image(target.getIMG()));;
		Name.setText(target.getLabel());
		Bonus.setText(target.getReward());
		Date.setValue(target.getEndDate());
	}
	

}
