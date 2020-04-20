package co.yedam.app.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController  implements Initializable {

	@FXML Button btnCheck;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@FXML
	public void loginCheck(ActionEvent event) {
		try {
			
			Stage primaryStage = (Stage) btnCheck.getScene().getWindow();

            // 새 레이아웃 추가
			BorderPane empView = FXMLLoader.load(getClass().getResource("emp.fxml"));
 
            // 씬에 레이아웃 추가
            Scene sc = new Scene(empView);
 
            // 씬을 스테이지에서 상영
            primaryStage.setScene(sc);
            primaryStage.show();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
