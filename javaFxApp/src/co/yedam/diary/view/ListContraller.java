package co.yedam.diary.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.yedam.diary.model.DiaryDAO;
import co.yedam.diary.model.DiaryDO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ListContraller implements Initializable {

	@FXML
	ListView<DiaryDO> listView;
	@FXML
	BorderPane rootLayout;
	@FXML
	Button button;
	@FXML
	HBox hbox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listView.setItems(FXCollections.observableArrayList());

		DiaryDAO d = new DiaryDAO();
		List<DiaryDO> dlist = d.getDiaryList();

		for (DiaryDO by : dlist) {
			listView.getItems().add(by);
		}

		listView.setOnMouseClicked((MouseEvent) -> {
			try {
				checkValue();
				// 새로운 창 띄우기 (새 스테이지 생성 -> 씬 추가 -> 레이아웃 추가)
				Stage stage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("../view/diaryPrint.fxml"));
				Scene sc = new Scene(root);
				stage.setScene(sc);
				stage.show();
				// 메인 창 닫아주기
				Stage main = (Stage) listView.getScene().getWindow();
				main.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});// setOnMouseClicked

	}

	private void checkValue() {
		String obj = listView.getSelectionModel().getSelectedItem().getIdx();
		System.out.println(obj);
		DataModel.num.add(obj);

	}

	@FXML
	public void diaryInputView(ActionEvent event) {
		try {
			BorderPane diaryInputView = FXMLLoader.load(getClass().getResource("diaryInput.fxml"));
//			rootLayout.setCenter(diaryInputView);
			// create scene containing the content
//            Parent root = FXMLLoader.load(getClass().getResource("../view/diaryPrint.fxml"));
			Scene scene = new Scene(diaryInputView);

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
