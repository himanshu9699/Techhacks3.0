package donationhistory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import donor.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import donationhistory.DonationBean;


public class donationViewController 
{
	
	@FXML
    private TableView<DonationBean> tblGrid;

    @FXML
    private TextField txtmobile;
    
    @FXML
    private Button goBack;
    
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
		 
		   Scene scene1=(Scene)goBack.getScene();
		   scene1.getWindow().hide();
    }
    
    @FXML
    void doShowRecent(ActionEvent event) 
    {
    	tblGrid.setItems(null);
    	TableColumn<DonationBean, String> mobile=new TableColumn<DonationBean, String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonationBean, String> bgroup=new TableColumn<DonationBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonationBean, String> date=new TableColumn<DonationBean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	date.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mobile, bgroup,date);
    	
    	tblGrid.setItems(null);
    	
    	ObservableList<DonationBean>allRecords=getRecentObjects();
    	tblGrid.setItems(allRecords);
    	//tblGrid.setItems(getRecentObjects());
    }
    
    ObservableList<DonationBean> getRecentObjects()
	{	
		ObservableList<DonationBean> ary1 = FXCollections.observableArrayList();
		
		PreparedStatement pst1;
		try {
			pst1=con.prepareStatement("select * from donations where mobile=?");
			pst1.setString(1, txtmobile.getText());
			table=pst1.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				String bgroup=table.getString("bgroup");
				String date=table.getString("dateofdonation");
				DonationBean obj = new DonationBean(mobile, bgroup, date);
				ary1.add(obj);
				
				//System.out.println(mobile+" "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return ary1;
	}

    @FXML
    void doShowAll(ActionEvent event) 
    {
    	tblGrid.setItems(null);
    	TableColumn<DonationBean, String> mobile=new TableColumn<DonationBean, String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonationBean, String> bgroup=new TableColumn<DonationBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonationBean, String> date=new TableColumn<DonationBean, String>("date");
    	date.setCellValueFactory(new PropertyValueFactory<>("date"));//same as bean property
    	date.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mobile, bgroup,date);
    	
    	tblGrid.setItems(null);
    	
    	ObservableList<DonationBean>allRecords=getAllObjects();
    	tblGrid.setItems(allRecords);
    	//tblGrid.setItems(getAllObjects());

    	
    }
    
    ResultSet table;
	Connection con;
	
	ObservableList<DonationBean> getAllObjects()
	{	
		ObservableList<DonationBean> ary = FXCollections.observableArrayList();
		
		PreparedStatement pst;
		try {
			pst=con.prepareStatement("select * from donations order by mobile");
			table=pst.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				String bgroup=table.getString("bgroup");
				String date=table.getString("dateofdonation");
				DonationBean obj = new DonationBean(mobile, bgroup, date);
				ary.add(obj);
				
				//System.out.println(mobile+" "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return ary;
	}

	@FXML
    void initialize() {
        con =	DatabaseConnection.doConnect();
        
        
    }

}
