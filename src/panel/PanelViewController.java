package panel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;
import donor.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class PanelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imgDonate;

    @FXML
    private ImageView imgHistory;

    @FXML
    private ImageView imgIssue;

    @FXML
    private ImageView imgRegister;

    @FXML
    private ImageView imgUnits;
    
    @FXML
    private ImageView imgDeveloper;
    
    @FXML
    private Button btnDeveloper;

    @FXML
    private Button btnDonate;

    @FXML
    private Button btnHistory;

    @FXML
    private Button btnIssue;

    @FXML
    private Button btnRegister;

    @FXML
    private Button btnUnits;

    @FXML
    private Button btnlogout;
    
    Connection con;


    @FXML
    void goRegister(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/donor/DonorMasterView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
			 
		   Scene scene1=(Scene)btnRegister.getScene();
		   scene1.getWindow().hide();
    }
    
    @FXML
    void goDonate(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/bloodUnit/BloodUnitView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnDonate.getScene();
		   scene1.getWindow().hide();
    }
    
    @FXML
    void goAvailable(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/availableBloodUnit/BloodStockView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnUnits.getScene();
		   scene1.getWindow().hide();
    }

    @FXML
    void goHistory(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/history/HistoryDataView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnUnits.getScene();
		   scene1.getWindow().hide();
    }

    @FXML
    void goIssue(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/issueBlood/IssueBloodView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnIssue.getScene();
		   scene1.getWindow().hide();
    }

    @FXML
    void goDeveloper(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Developer/MeetDeveloper.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnlogout.getScene();
		   scene1.getWindow().hide();
    }
    
    @FXML
    void doLogOut(ActionEvent event) 
    {
    	try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/LoginView.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            
            
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// to hide the opened window
		 
		   Scene scene1=(Scene)btnlogout.getScene();
		   scene1.getWindow().hide();
    }


    void doSet()
    {
    	Image img,img1,img2,img3,img4,img5;
		try {
			img = new Image(new FileInputStream("E:\\Coding\\JavaProject\\register.png"));
			imgRegister.setImage(img);
			
			img1 = new Image(new FileInputStream("E:\\Coding\\JavaProject\\donate.png"));
			imgDonate.setImage(img1);
			
			img2 = new Image(new FileInputStream("E:\\Coding\\JavaProject\\history.png"));
			imgHistory.setImage(img2);
			
			img3 = new Image(new FileInputStream("E:\\Coding\\JavaProject\\issue.png"));
			imgIssue.setImage(img3);
			
			img4 = new Image(new FileInputStream("E:\\Coding\\JavaProject\\availableunit.png"));
			imgUnits.setImage(img4);
			
			img5 = new Image(new FileInputStream("E:\\Coding\\JavaProject\\developer.png"));
			imgDeveloper.setImage(img5);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @FXML
    void initialize() {
        assert imgDonate != null : "fx:id=\"imgDonate\" was not injected: check your FXML file 'PanelView.fxml'.";
        assert imgHistory != null : "fx:id=\"imgHistory\" was not injected: check your FXML file 'PanelView.fxml'.";
        assert imgIssue != null : "fx:id=\"imgIssue\" was not injected: check your FXML file 'PanelView.fxml'.";
        assert imgRegister != null : "fx:id=\"imgRegister\" was not injected: check your FXML file 'PanelView.fxml'.";
        assert imgUnits != null : "fx:id=\"imgUnits\" was not injected: check your FXML file 'PanelView.fxml'.";

        con =	DatabaseConnection.doConnect();
        doSet();
    }

}
