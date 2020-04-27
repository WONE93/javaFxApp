package co.yedam.diary.view;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	Label lblStatus;
	@FXML
	TextField txtUserName;
	@FXML
	TextField txtPassword;
	@FXML
	AnchorPane rootLayout;

	public void Login(ActionEvent event) throws Exception {
		if (txtUserName.getText().contentEquals("user") && txtPassword.getText().contentEquals("pass")) {
			 goHome();

		} else {
			 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		        alert.setTitle("Warning");
		        alert.setHeaderText("Login failed");
		        alert.setContentText("다시 로그인하세요.");

		        Optional<ButtonType>result = alert.showAndWait();
		        
		        if(result.get() == ButtonType.OK) {
		        	txtUserName.setText("");
		        	txtPassword.setText("");
		        }
			lblStatus.setText("다시 로그인하세요.");
		}

	}
	
	public void goHome() {
		try {
			Stage stage = new Stage();

			Parent root = FXMLLoader.load(getClass().getResource("calendar/calendar.fxml"));
			Scene scene = new Scene(root, 600, 430);
			stage.setScene(scene);
			stage.show();
			
			// 메인 창 닫아주기
	          Stage main = (Stage) rootLayout.getScene().getWindow();
	          main.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // e of goHome
}
