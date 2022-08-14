package availableBloodUnit;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import donor.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BloodStockViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtABn;

    @FXML
    private TextField txtABp;

    @FXML
    private TextField txtAn;

    @FXML
    private TextField txtAp;

    @FXML
    private TextField txtBn;

    @FXML
    private TextField txtBp;

    @FXML
    private TextField txtOn;

    @FXML
    private TextField txtOp;
    
    @FXML
    private Button goBack;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    void doSetValues()
    {
    	try {
			pst=con.prepareStatement("select * from total_blood_record");
			table=pst.executeQuery();
			System.out.println("hello");
			while(table.next())
			{
				txtAp.setText(table.getString("Ap"));
				txtBp.setText(table.getString("Bp"));
				txtABp.setText(table.getString("ABp"));
				txtOp.setText(table.getString("Op"));
				txtAn.setText(table.getString("An"));
				txtBn.setText(table.getString("Bn"));
				txtABn.setText(table.getString("ABn"));
				txtOn.setText(table.getString("On"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void goPanel(ActionEvent event) 
    {
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
        assert txtABn != null : "fx:id=\"txtABn\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtABp != null : "fx:id=\"txtABp\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtAn != null : "fx:id=\"txtAn\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtAp != null : "fx:id=\"txtAp\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtBn != null : "fx:id=\"txtBn\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtBp != null : "fx:id=\"txtBp\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtOn != null : "fx:id=\"txtOn\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        assert txtOp != null : "fx:id=\"txtOp\" was not injected: check your FXML file 'BloodStockView.fxml'.";
        
        con =	DatabaseConnection.doConnect();

        doSetValues();
    }

}
