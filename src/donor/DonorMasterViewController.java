package donor;

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
import javafx.scene.control.Button;
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

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private ComboBox<String> combogender;

    @FXML
    private ImageView imag;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtage;

    @FXML
    private TextField txtcity;

    @FXML
    private TextField txtdisease;

    @FXML
    private TextField txtmobile;

    @FXML
    private TextField txtname;
    
    @FXML
    private ImageView imgBlood;
    
    @FXML
    private Button goBack;
    
    Connection con;
    PreparedStatement pst;
    File file;
    ResultSet table;

    void doSetValues()
    {
    	
    	ArrayList<String> gen = new ArrayList<String>(Arrays.asList("Select","Male","Female","Others"));
    	combogender.getItems().setAll(gen);
    	
    	ArrayList<String> blood = new ArrayList<String>(Arrays.asList("Select","A+","B+","AB+","O+","A-","B-","AB-","O-"));
    	combobgroup.getItems().setAll(blood);
    	
    	Image img;
    	try {
			img = new Image(new FileInputStream("E:\\Coding\\JavaProject\\blood.jpg"));
			imgBlood.setImage(img);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
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
			pst.setString(1, txtmobile.getText());
			
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
    void doBrowse(ActionEvent event) throws FileNotFoundException 
    {
    	FileChooser filechooser = new FileChooser();
    	filechooser.setTitle("Select image");
    	filechooser.getExtensionFilters().addAll(new ExtensionFilter("JPG Files","*.jpg"), new ExtensionFilter("PNG Files", "*.png"));
    	file = filechooser.showOpenDialog(null);
    	if(file!=null)
    	{
    		Image img = new Image(new FileInputStream(file.getAbsoluteFile()));
    		imag.setImage(img);
    	}
    }

    @FXML
    void doDelete(ActionEvent event) 
    {
    	String mb=txtmobile.getText();
    	if(chkDonor(mb)==false)
    	{
    		showMsg("Donor not found");
    		return;
    	}
    	
    	try {
    		pst=con.prepareStatement("delete from donors where mobile=?");
			pst.setString(1, txtmobile.getText());
			pst.executeUpdate();
			showMsg("Donor deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void doFind(ActionEvent event) 
    {
    	String mb=txtmobile.getText();
    	if(chkDonor(mb)==false)
    	{
    		showMsg("Donor not found");
    		return;
    	}
    	
    	try {
			pst=con.prepareStatement("select * from donors where mobile=?");
			pst.setString(1, txtmobile.getText());
			table=pst.executeQuery();
			while(table.next())
			{
				txtname.setText(table.getString("name"));
				txtaddress.setText(table.getString("address"));
				txtcity.setText(table.getString("city"));
				txtage.setText(table.getString("age"));
				txtdisease.setText(table.getString("disease"));
				combogender.setValue(table.getString("gender"));
				combobgroup.setValue(table.getString("bgroup"));
				
				Image img;
				try {
					img = new Image(new FileInputStream(table.getString("pkpath")));
					imag.setImage(img);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doNew(ActionEvent event) 
    {
    	txtmobile.setText("");
    	txtname.setText("");
    	txtaddress.setText("");
    	txtcity.setText("");
    	txtage.setText("");
    	txtdisease.setText("");
    	combogender.setValue("");
    	combobgroup.setValue("");
    	imag.setImage(null);
    }

    @FXML
    void doRegister(ActionEvent event) 
    {
    	
    	String mb=txtmobile.getText();
    	if(chkDonor(mb)==true)
    	{
    		showMsg("Donor already existed");
    		return;
    	}
    	
    	try {
			pst=con.prepareStatement("insert into donors values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, txtmobile.getText());
			pst.setString(2, file.getAbsolutePath());
			pst.setString(3, txtname.getText());
			pst.setString(4, combogender.getValue().toString());
			pst.setString(5, txtaddress.getText());
			pst.setString(6, txtcity.getText());
			pst.setString(7, combobgroup.getValue().toString());
			pst.setInt(8, Integer.parseInt(txtage.getText()));
			pst.setString(9, txtdisease.getText());
			pst.executeUpdate();
			showMsg("Record Inserted Successfullyyyy");
			
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	String mb=txtmobile.getText();
    	if(chkDonor(mb)==false)
    	{
    		showMsg("Donor not found");
    		return;
    	}
    	
    	try {
			pst=con.prepareStatement("update donors set pkpath=?, name=?, gender=?, address=?, city=?, bgroup=?, age=?, disease=?, dor=current_date() where mobile=?");
			
			pst.setString(1, file.getAbsolutePath());
			pst.setString(2, txtname.getText());
			pst.setString(3, combogender.getValue().toString());
			pst.setString(4, txtaddress.getText());
			pst.setString(5, txtcity.getText());
			pst.setString(6, combobgroup.getValue().toString());
			pst.setInt(7, Integer.parseInt(txtage.getText()));
			pst.setString(8, txtdisease.getText());
			pst.setString(9, txtmobile.getText());
			pst.executeUpdate();
			showMsg("Record Updated Successfullyyyy");
			
			
		} 
    	catch (SQLException e) 
    	{
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
        assert combobgroup != null : "fx:id=\"combobgroup\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert combogender != null : "fx:id=\"combogender\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert imag != null : "fx:id=\"imag\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtaddress != null : "fx:id=\"txtaddress\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtage != null : "fx:id=\"txtage\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtcity != null : "fx:id=\"txtcity\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtdisease != null : "fx:id=\"txtdisease\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtmobile != null : "fx:id=\"txtmobile\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        assert txtname != null : "fx:id=\"txtname\" was not injected: check your FXML file 'DonorMasterView.fxml'.";
        
        con =	DatabaseConnection.doConnect();
        doSetValues();
    }

}
