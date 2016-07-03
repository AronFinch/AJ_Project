package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class ControllerDialogLoginReset implements Initializable {
    
    @FXML
    private Button buttonDisable;
    
    @FXML
    private Label labelDisable;
            
    @FXML
    private PasswordField passwordFieldDisable;
    
    @FXML
    public void btnChek(){
        
        buttonDisable.setDisable(false);
        labelDisable.setDisable(false);
        passwordFieldDisable.setDisable(false);
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // TODO
    }

}
