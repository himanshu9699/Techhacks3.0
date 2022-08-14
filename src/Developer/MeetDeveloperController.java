package Developer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MeetDeveloperController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button goBack;

    @FXML
    void goPanel(ActionEvent event) {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/panel/PanelView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)goBack.getScene();
		   scene1.getWindow().hide();

    }

    @FXML
    void initialize() {
        assert goBack != null : "fx:id=\"goBack\" was not injected: check your FXML file 'MeetDeveloper.fxml'.";

    }

}
