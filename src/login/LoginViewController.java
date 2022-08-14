package login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imglogin;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtuser;
    
    @FXML
    private Button btnlogin;
    
    Connection con;


    void showMsg(String msg)
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Login");
    	alert.setContentText(msg);
    	alert.show();
    }
    void doSet()
    {
    	try {
			Image img=new Image(new FileInputStream("E:\\Coding\\JavaProject\\login.jpg"));
			imglogin.setImage(img);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    @FXML
    void doLogin(ActionEvent event) 
    {
    	if(txtuser.getText().equals("admin") && txtpass.getText().equals("password"))
    		{
    		
			try {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/panel/PanelView.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
                
                Stage stag;
               stag = (Stage)btnlogin.getScene().getWindow();
               stag.close();
                
               // to hide the opened window
   			 
  			   Scene scene1=(Scene)btnlogin.getScene();
  			   scene1.getWindow().hide();
  			 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
    		}
    	else if(txtuser.getText().equals("") || txtpass.getText().equals(""))
    	{
    		showMsg("Please enter username/password");
    	}	
    	
    	else
    		showMsg("Incorrect Username/Password");
    }

    @FXML
    void initialize() {
        assert imglogin != null : "fx:id=\"imglogin\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert txtpass != null : "fx:id=\"txtpass\" was not injected: check your FXML file 'LoginView.fxml'.";
        assert txtuser != null : "fx:id=\"txtuser\" was not injected: check your FXML file 'LoginView.fxml'.";
        
        con =	DatabaseConnection.doConnect();

        doSet();

    }

}
