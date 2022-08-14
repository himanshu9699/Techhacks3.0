package issuebloodhistory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import issuebloodhistory.IssueBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import panel.DatabaseConnection;

public class IssueDataViewController {

   @FXML
    private ComboBox<String> combobgroup;

    @FXML
    private DatePicker datepick;

    @FXML
    private TableView<IssueBean> tblGrid;
    
    @FXML
    private Button goBack;

    Connection con;
    ResultSet table;
    
    @FXML
    void goHistory(ActionEvent event) {
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

    	TableColumn<IssueBean, String> name=new TableColumn<IssueBean, String>("name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> mobile=new TableColumn<IssueBean, String>("mobile");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> bgroup=new TableColumn<IssueBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> hospital=new TableColumn<IssueBean, String>("hospital");
    	hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));//same as bean property
    	hospital.setMinWidth(100);
    
    	TableColumn<IssueBean, String> reason=new TableColumn<IssueBean, String>("reason");
    	reason.setCellValueFactory(new PropertyValueFactory<>("reason"));//same as bean property
    	reason.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> doi=new TableColumn<IssueBean, String>("doi");
    	doi.setCellValueFactory(new PropertyValueFactory<>("doi"));//same as bean property
    	doi.setMinWidth(100);
    	
    	tblGrid.getColumns().clear();
    	tblGrid.getColumns().addAll(name, mobile, bgroup, hospital, reason, doi);
    	
    	tblGrid.setItems(null);
    	
    	ObservableList<IssueBean>allRecords=getAllObjects();
    	tblGrid.setItems(allRecords);
    	//tblGrid.setItems(getAllObjects());
    }
    ObservableList<IssueBean> getAllObjects()
	{	
		ObservableList<IssueBean> ary = FXCollections.observableArrayList();
		
		PreparedStatement pst;
		try {
			pst=con.prepareStatement("select * from issued where doi=? and bgroup=?");
			pst.setString(1, datepick.getValue().toString());
			pst.setString(2, combobgroup.getValue().toString());
			table=pst.executeQuery();
			while(table.next())
			{
				String name=table.getString("name");
				String mobile=table.getString("mobile");
				String bgroup=table.getString("bgroup");
				String hospital=table.getString("hospital");
				String reason=table.getString("reason");
				String doi=table.getString("doi");
				IssueBean obj = new IssueBean(name, mobile, bgroup, hospital, reason, doi);
				ary.add(obj);
				
				//System.out.println(mobile+" "+bgroup+" "+date);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return ary;
	}

    public void writeExcel( ObservableList<IssueBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("BloodIssuedinfo.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="Needy Name,Mobile No,Blood Group, Hospital Name,Reason,Date of Issue,\n";
            writer.write(text);
            for (IssueBean p : list)
            {
				text = p.getName() + "," + p.getMobile()+ "," + p.getBgroup() + "," + p.getHospital()+"," + p.getReason()+ "," +  p.getDoi()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    @FXML
    void doexport(ActionEvent event) {
    	try {
			writeExcel(getAllObjects());
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
   
    @FXML
    void initialize() {
        assert combobgroup != null : "fx:id=\"combobgroup\" was not injected: check your FXML file 'IssueDataView.fxml'.";
        assert datepick != null : "fx:id=\"datepick\" was not injected: check your FXML file 'IssueDataView.fxml'.";
        assert tblGrid != null : "fx:id=\"yblGrid\" was not injected: check your FXML file 'IssueDataView.fxml'.";
        con=DatabaseConnection.doConnect();
        
        doSetValues();
    }

}
