package co.yedam.test.view;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class DailyController implements Initializable {
	
	
	@FXML
	TextField txtdDate;	
	@FXML
	TextField txtIdx;
	@FXML
	TextField txtWeather;
	@FXML
	TextField txtTitle;
	@FXML
	TextField txtContents;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}	
	


	
//	// 입력버튼 -> 입력화면으로
	@FXML
	public void dyWrite(ActionEvent actionEvent) {
		System.out.println("입력화면으로 이동");
	}
	
	// 수정버튼 
	@FXML
	public void dyEdit(ActionEvent actionEvent) {
		System.out.println("수정되었습니다.");
	}
	
	@FXML 
	public void dyDelete(ActionEvent actionEvent) {
		System.out.println("삭제되었습니다.");
	}

}
