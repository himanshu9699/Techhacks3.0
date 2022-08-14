module DonorProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens donor to javafx.graphics, javafx.fxml;
	opens bloodUnit to javafx.graphics, javafx.fxml;
	opens availableBloodUnit to javafx.graphics, javafx.fxml;
	opens issueblood to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens panel to javafx.graphics, javafx.fxml;
	opens tableviewww to javafx.graphics, javafx.fxml;
	opens donationhistory to javafx.graphics, javafx.fxml, javafx.base;
	opens donordata to javafx.graphics, javafx.fxml, javafx.base;
	opens issuebloodhistory to javafx.graphics, javafx.fxml, javafx.base;
	opens history to javafx.graphics, javafx.fxml, javafx.base;
}
