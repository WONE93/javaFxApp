package co.yedam.diary.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.yedam.diary.model.DiaryDAO;
import co.yedam.diary.model.DiaryDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PrintController implements Initializable {

	@FXML
	TextField txtIdx;
	@FXML
	Label txtDate;
	@FXML
	TextField txtTitle;
	@FXML
	TextArea txtContents;
	@FXML
	TextField txtWeather;
	@FXML
	BorderPane rootLayout;
	@FXML
	Button button;
	@FXML
	HBox hbox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 리스트에서 프린트가는거
		if (DataModel.check.indexOf("num") == 0) {
			DiaryDO dy = new DiaryDO();
			for (String item : DataModel.num) {
				dy.setIdx(item);
//				txtIdx.setText(item);
//				System.out.println(item);
			}

			DiaryDAO dao = new DiaryDAO();
			DiaryDO result = dao.select(dy);

//			txtIdx.setText(result.getIdx());
			txtDate.setText(result.getdDate());
			txtTitle.setText(result.getTitle());
			txtWeather.setText(result.getWeather());
			txtContents.setText(result.getContents());
			// 캘린더에서 프린트가는거
		} else if (DataModel.check.indexOf("inDate") == 0) {
			// selectDate
			DiaryDO dy1 = new DiaryDO();
			for (String item : DataModel.inDate) {
				dy1.setdDate(item);
				txtDate.setText(item);
			}

			DiaryDAO dao1 = new DiaryDAO();
			DiaryDO result1 = dao1.selectDate(dy1);

//			txtIdx.setText(result.getIdx());
//			txtDate.setText(result1.getdDate());
			// 캘린터 ->세부사항화면에서도 날짜값 라벨로 표시
			txtDate.setText(result1.getdDate());
			txtTitle.setText(result1.getTitle());
			txtWeather.setText(result1.getWeather());
			txtContents.setText(result1.getContents());
		}

	}

	@FXML // 수정버튼 click
	public void dyChange(ActionEvent actionEvent) {
		// DAO
		// 텍스트 필드값을 읽어서 DO 객체 담기
		DiaryDO dy = new DiaryDO();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("수정 메시지");
		alert.setHeaderText("일기를 수정하려합니다.");
		alert.setContentText("정말 수정하시겠습니까?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {

			for (String item : DataModel.num) {
				dy.setIdx(item);
				dy.setdDate(item);
			}

			dy.setTitle(txtTitle.getText());
			dy.setContents(txtContents.getText());
			dy.setWeather(txtWeather.getText());

			// DAO 등록
			DiaryDAO dao = new DiaryDAO();
			dao.update(dy);
			System.out.println("수정처리됨");

			// 수정하고 리스트로 넘어가기
	

		} // end of if

		if (result.get() == ButtonType.OK) {

			for (String item : DataModel.inDate) {
				dy.setIdx(item);
				dy.setdDate(item);
			}

			dy.setTitle(txtTitle.getText());
			dy.setContents(txtContents.getText());
			dy.setWeather(txtWeather.getText());

			// DAO 등록
			DiaryDAO dao = new DiaryDAO();
			dao.update2(dy);
			System.out.println("수정처리됨");

			// 수정하고 리스트로 넘어가기
		} // end of if
		goHome();

	}

	@FXML // 삭제버튼 click
	public void dyDelete(ActionEvent actionEvent) {

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("삭제 메시지");
		alert.setHeaderText("칼럼을 삭제하려합니다.");
		alert.setContentText("정말 삭제하시겠습니까?");

		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			// 죄회할 사번을 DO객체에 담기
			DiaryDO dy = new DiaryDO();
//		dy.setTitle(txtTitle.getText());

			for (String item : DataModel.num) {
				dy.setIdx(item);
			}

			// 단건조회
			DiaryDAO dao = new DiaryDAO();
			dao.delete(dy);

			System.out.println("삭제처리됨");

			// 삭제하고 리스트로 넘어가기

		}

		if (result.get() == ButtonType.OK) {
			DiaryDO dy = new DiaryDO();
			// 캘린더용
			for (String item : DataModel.inDate) {
				dy.setIdx(item);
			}

			// 단건조회
			DiaryDAO dao = new DiaryDAO();
			dao.delete2(dy);

			System.out.println("삭제처리됨");

			// 삭제하고 리스트로 넘어가기
		}
		goHome();
	}

	public void goHomeView(ActionEvent actionEvent) {
		goHome();
	}

	// 리스트화면으로 가는 매소드
	public void goHome() {
		try {
			Stage stage = new Stage();

			Parent root = FXMLLoader.load(getClass().getResource("./calendar/calendar.fxml"));
			Scene scene = new Scene(root, 600, 430);
			stage.setScene(scene);
			stage.show();

			// 메인 창 닫아주기
			Stage main = (Stage) rootLayout.getScene().getWindow();
			main.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	} // e of goList

}
