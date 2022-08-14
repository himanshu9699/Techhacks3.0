package bloodUnit;

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
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser.ExtensionFilter;
import donor.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.control.Button;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BloodUnitViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker datepick;

    @FXML
    private Button doNew;

    @FXML
    private Button doSearch;

    @FXML
    private Button doUpload;

    @FXML
    private TextField txtbgroup;


    @FXML
    private ComboBox<String> combomobile;
    
    @FXML
    private ImageView imgdonor;

    @FXML
    private ImageView imglogo;
    
    @FXML
    private Label txtname;
    
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
    
    boolean chkDonor(String mobileno)
    {
    	boolean jasoos=false;
    	try {
			pst=con.prepareStatement("select * from donors where mobile=?");
			pst.setString(1, combomobile.getValue());
			
			table=pst.executeQuery();
			
			while(table.next())
			{
				jasoos=true;
			}
    		}
    		catch (SQLException e) 
    	{
			
			e.printStackTrace();
		}
    	return jasoos;
    }
    
    @FXML
    void doNew(ActionEvent event) 
    {
    	combomobile.setValue("");
    	txtbgroup.setText("");
    	imgdonor.setImage(null);
    	datepick.setValue(null);
    }

    @FXML
    void doSearch(ActionEvent event) 
    {
    	String mb=combomobile.getValue();
    	if(chkDonor(mb)==false)
    	{
    		showMsg("Donor not found");
    		return;
    	}
    	
    	try {
		pst=con.prepareStatement("select bgroup,pkpath,name from donors where mobile=?");
		pst.setString(1, combomobile.getValue());
		table=pst.executeQuery();
		while(table.next())
		{
			txtbgroup.setText(table.getString("bgroup"));
			Image img = new Image(new FileInputStream(table.getString("pkpath")));
			imgdonor.setImage(img);
			txtname.setText(table.getString("name"));
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
    
    @FXML
    void doUpload(ActionEvent event) 
    {
    	LocalDate local=datepick.getValue();
    	
    	String mb=combomobile.getValue();
    	if(chkDonor(mb)==false)
    	{
    		showMsg("Donor not found");
    		return;
    	}
    	
    	try {
			pst=con.prepareStatement("insert into donations values(?,?,?)");
			pst.setString(1, combomobile.getValue());
			pst.setString(2, txtbgroup.getText());
			java.sql.Date date=java.sql.Date.valueOf(local);
			pst.setDate(3, date);
			pst.executeUpdate();
			
			String bg=txtbgroup.getText();
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
			String s="update total_blood_record set "+bg1+"="+bg1+"+1";

			pst=con.prepareStatement(s);

			pst.executeUpdate();
			
			
			showMsg("Record Uploaded Successfulyyy");
			
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
		
    }
    
    void setValues()
    {
    	try {
			Image img = new Image(new FileInputStream("E:\\Coding\\JavaProject\\Blood_donation.jpg"));
			 imglogo.setImage(img);
			 
		    	pst=con.prepareStatement("select distinct mobile from donors");
		    	table=pst.executeQuery();
		    	ArrayList<String> mno = new ArrayList<String>();
		    	while(table.next())
		    	{
		    		mno.add(table.getString("mobile"));
		    	}
		    	combomobile.getItems().setAll(mno);
		    	
		} catch (FileNotFoundException | SQLException e) {
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
    	assert combomobile != null : "fx:id=\"combomobile\" was not injected: check your FXML file 'BloodUnitView.fxml'.";
        assert datepick != null : "fx:id=\"datepick\" was not injected: check your FXML file 'BloodUnitView.fxml'.";
        assert imgdonor != null : "fx:id=\"imgdonor\" was not injected: check your FXML file 'BloodUnitView.fxml'.";
        assert imglogo != null : "fx:id=\"imglogo\" was not injected: check your FXML file 'BloodUnitView.fxml'.";
        assert txtbgroup != null : "fx:id=\"txtbgroup\" was not injected: check your FXML file 'BloodUnitView.fxml'.";

        con =	DatabaseConnection.doConnect();
        
        setValues();
       
    }

}
