package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.User;

public class ControllerMiniUser implements Initializable {
	
	@FXML
    private Label Name;
	@FXML
    private Label Level;
	
	static User user = null;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		Name.setText(user.getName());
		Level.setText(Integer.toString(user.getRating()));
		
	}

}
