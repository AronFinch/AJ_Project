package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import model.Notice;
import model.Target;
import model.User;

public class ControllerUserPane implements Initializable {
	
	@FXML
    private Label Name;
	@FXML
    private Label Rating;
	@FXML
    private Label CountTarget;
	@FXML
    private ProgressBar ProgressBar;
	
	static User user = null;
	
	@Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
			Name.setText(user.getName());
			Rating.setText(Integer.toString(user.getRating()));
			CountTarget.setText(Integer.toString(user.numberDoneTarget()) + "/" + Integer.toString(user.numberAllTarget()));
			
		}

}
