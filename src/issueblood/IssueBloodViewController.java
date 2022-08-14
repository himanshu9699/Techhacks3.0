package issueblood;

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
import javafx.scene.control.Button;
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

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
public class IssueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private DatePicker datepick;

    @FXML
    private TextField txthospital;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtpurpose;

    @FXML
    private TextField txtunits;
    
    @FXML
    private Button goBack;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doUpload(ActionEvent event) 
    {
    	String b1=combobgroup.getValue();
		String b=null;
		if(b1.equals("A+"))
			b="Ap";
		if(b1.equals("B+"))
			b="Bp";
		if(b1.equals("AB+"))
			b="ABp";
		if(b1.equals("O+"))
			b="Op";
		if(b1.equals("A-"))
			b="An";
		if(b1.equals("B-"))
			b="Bn";
		if(b1.equals("O-"))
			b="O-";
		
		String st="select * from total_blood_record ";
		
		try {
			int val = 0;
			pst=con.prepareStatement("select * from total_blood_record ");
			table = pst.executeQuery();
			while(table.next())
			{
				val=table.getInt(b);
			}
			
			if(val>=Integer.parseInt(txtunits.getText()))
			{
				pst=con.prepareStatement("insert into issued values(?,?,?,?,?,?)");
				pst.setString(1, txtname.getText());
				pst.setString(2, txtmobile.getText());
				pst.setString(3, combobgroup.getValue());
				pst.setString(4, txthospital.getText());
				pst.setString(5, txtpurpose.getText());
		    	LocalDate local=datepick.getValue();
		    	java.sql.Date date=java.sql.Date.valueOf(local);
				pst.setDate(6, date);
				
				pst.executeUpdate();
				
				
				String bg=combobgroup.getValue();
				String bg1=null;
				if(bg.equals("A+"))
					bg1="Ap";
				if(bg.equals("B+"))
					bg1="Bp";
				if(bg.equals("AB+"))
					bg1="ABp";
				if(bg.equals("O+"))
					bg1="Op";
				if(bg.equals("A-"))
					bg1="An";
				if(bg.equals("B-"))
					bg1="Bn";
				if(bg.equals("O-"))
					bg1="O-";
				String s="update total_blood_record set "+bg1+"="+bg1+"-1";

				pst=con.prepareStatement(s);

				pst.executeUpdate();
				showMsg("Uploaded Successfully");
			}
			else
				showMsg("Not enough blood");
				
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
    	
    	
    	
    	
    }
    
    void doSetValues()
    {
    	
//    	ArrayList<String> gen = new ArrayList<String>(Arrays.asList("Select","Male","Female","Others"));
//    	combogender.getItems().setAll(gen);
    	
    	ArrayList<String> blood = new ArrayList<String>(Arrays.asList("Select","A+","B+","AB+","O+","A-","B-","AB-","O-"));
    	combobgroup.getItems().setAll(blood);

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
        assert combobgroup != null : "fx:id=\"combobgroup\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert datepick != null : "fx:id=\"datepick\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert txthospital != null : "fx:id=\"txthospital\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert txtpurpose != null : "fx:id=\"txtpurpose\" was not injected: check your FXML file 'IssueBloodView.fxml'.";
        assert txtunits != null : "fx:id=\"txtunits\" was not injected: check your FXML file 'IssueBloodView.fxml'.";

        con=DatabaseConnection.doConnect();
        doSetValues();
    }

}
