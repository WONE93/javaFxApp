package co.yedam.diary.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.yedam.diary.model.DiaryDAO;
import co.yedam.diary.model.DiaryDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class InputContraller implements Initializable{

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
	@FXML
	BorderPane rootLayout;
	@FXML
	Button button;
	@FXML
	HBox hbox;
	
	@FXML //저장버튼 click
	public void dyInsert(ActionEvent actionEvent) {
		
		LocalDate localDate =txtDate.getValue();
        String strDate ="";
        if(localDate != null) {
            strDate = localDate.toString();
        }
		//DAO
		//텍스트 필드값을 읽어서 DO 객체 담기
		DiaryDO dy = new DiaryDO();
		
		dy.setdDate(strDate);
		dy.setTitle(txtTitle.getText());
		dy.setContents(txtContents.getText());
		dy.setWeather(txtWeather.getText());
		
		//DAO 등록
		DiaryDAO dao = new DiaryDAO();
		dao.insert(dy);
		System.out.println("저장처리됨");
	}
	
	@FXML //취소버튼 click -> 리스트로
	public void dyDelete(ActionEvent actionEvent) {
		System.out.println("취소처리됨");
	}
	
	@FXML
	public void diaryListView(ActionEvent event) {
		try {
			BorderPane diaryListView = FXMLLoader.load(getClass().getResource("diaryList.fxml"));

          Scene scene = new Scene(diaryListView);

          Stage window = new Stage();
          window.setScene(scene);

          // make window visible
          window.show();
          
          // 메인 창 닫아주기
          Stage main = (Stage) rootLayout.getScene().getWindow();
          main.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
