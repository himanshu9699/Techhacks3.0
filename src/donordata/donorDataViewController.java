package donordata;

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
import donordata.DonorBean;


public class donorDataViewController 
{
    @FXML
    private ComboBox<String> combobgroup;
    
	@FXML
    private TableView<DonorBean> tblGrid;

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
    void doSetValues()
    {
    	ArrayList<String> blood = new ArrayList<String>(Arrays.asList("Select","A+","B+","AB+","O+","A-","B-","AB-","O-"));
    	combobgroup.getItems().setAll(blood);
    }
    
    @FXML
    void doFetchAll(ActionEvent event) 
    {
    	tblGrid.setItems(null);
    	TableColumn<DonorBean, String> mobile=new TableColumn<DonorBean, String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> name=new TableColumn<DonorBean, String>("name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> gender=new TableColumn<DonorBean, String>("gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> address=new TableColumn<DonorBean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> city=new TableColumn<DonorBean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//same as bean property
    	city.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> bgroup=new TableColumn<DonorBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, Integer> age=new TableColumn<DonorBean, Integer>("age");
    	age.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	age.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> disease=new TableColumn<DonorBean, String>("disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));//same as bean property
    	disease.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> dor=new TableColumn<DonorBean, String>("dor");
    	dor.setCellValueFactory(new PropertyValueFactory<>("dor"));//same as bean property
    	dor.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mobile, name, gender, address, city, bgroup, age, disease, dor);
    	
    	tblGrid.setItems(null);
    	
    	ObservableList<DonorBean>allRecords=getSomeObjects();
    	tblGrid.setItems(allRecords);
    	//tblGrid.setItems(getAllObjects());
    }
    
    ObservableList<DonorBean> getSomeObjects()
	{	
		ObservableList<DonorBean> ary1 = FXCollections.observableArrayList();
		
		PreparedStatement pst;
		try {
			pst=con.prepareStatement("select * from donors where bgroup=?");
			pst.setString(1, combobgroup.getValue());
			table=pst.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				String name=table.getString("name");
				String gender=table.getString("gender");
				String address=table.getString("address");
				String city=table.getString("city");
				String bgroup=table.getString("bgroup");
				int age=table.getInt("age");
				String disease=table.getString("disease");
				String dor=table.getString("dor");
				DonorBean obj = new DonorBean(mobile, name, gender, address, city, bgroup, age, disease, dor);
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
    	TableColumn<DonorBean, String> mobile=new TableColumn<DonorBean, String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> name=new TableColumn<DonorBean, String>("name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> gender=new TableColumn<DonorBean, String>("gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> address=new TableColumn<DonorBean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> city=new TableColumn<DonorBean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//same as bean property
    	city.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> bgroup=new TableColumn<DonorBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, Integer> age=new TableColumn<DonorBean, Integer>("age");
    	age.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	age.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> disease=new TableColumn<DonorBean, String>("disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));//same as bean property
    	disease.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> dor=new TableColumn<DonorBean, String>("dor");
    	dor.setCellValueFactory(new PropertyValueFactory<>("dor"));//same as bean property
    	dor.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(mobile, name, gender, address, city, bgroup, age, disease, dor);
    	
    	tblGrid.setItems(null);
    	
    	ObservableList<DonorBean>allRecords=getAllObjects();
    	tblGrid.setItems(allRecords);
    	//tblGrid.setItems(getAllObjects());

    	
    }
    
    ResultSet table;
	Connection con;
	
	ObservableList<DonorBean> getAllObjects()
	{	
		ObservableList<DonorBean> ary = FXCollections.observableArrayList();
		
		PreparedStatement pst;
		try {
			pst=con.prepareStatement("select * from donors order by mobile");
			table=pst.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				String name=table.getString("name");
				String gender=table.getString("gender");
				String address=table.getString("address");
				String city=table.getString("city");
				String bgroup=table.getString("bgroup");
				int age=table.getInt("age");
				String disease=table.getString("disease");
				String dor=table.getString("dor");
				DonorBean obj = new DonorBean(mobile, name, gender, address, city, bgroup, age, disease, dor);
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
        doSetValues();
        
        
    }

}
