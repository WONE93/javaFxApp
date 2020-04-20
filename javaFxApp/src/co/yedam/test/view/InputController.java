package co.yedam.test.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.yedam.test.model.DailyDAO;
import co.yedam.test.model.DailyDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InputController implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	DatePicker txtDate;
	
	@FXML
	TextField txtTitle;
	
	@FXML
	TextField txtContents;
	
	@FXML
	TextField txtWeather;
	
	@FXML //저장버튼 click
	public void dyInsert(ActionEvent actionEvent) {
		LocalDate localDate =txtDate.getValue();
        String strDate ="";
        if(localDate != null) {
            strDate = localDate.toString();
        }
		//DAO
		//텍스트 필드값을 읽어서 DO 객체 담기
		DailyDO dy = new DailyDO();
		
		dy.setdDate(strDate);
		dy.setTitle(txtTitle.getText());
		dy.setContents(txtContents.getText());
		dy.setWeather(txtWeather.getText());
		
		//DAO 등록
		DailyDAO dao = new DailyDAO();
		dao.insert(dy);
		System.out.println("저장처리됨");
	}
	
	@FXML //취소버튼 click -> 리스트로
	public void dyDelete(ActionEvent actionEvent) {
		System.out.println("취소처리됨");
	}
	
	
}
