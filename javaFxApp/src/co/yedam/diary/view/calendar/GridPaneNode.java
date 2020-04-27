package co.yedam.diary.view.calendar;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import co.yedam.diary.model.DiaryDAO;
import co.yedam.diary.model.DiaryDO;
import co.yedam.diary.view.DataModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class GridPaneNode extends GridPane {

	private LocalDate date;

	public GridPaneNode(Node... children) {
		super();
		this.setOnMouseClicked(// e -> System.out.println("This pane's date is: " + date)

				(MouseEvent) -> {

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					String strDate = formatter.format(date);
					DataModel.check.add(0, "inDate");
					DataModel.inDate.add(strDate);
					System.out.println(date);

					ArrayList<String> dateAl = new ArrayList<String>();
					dateAl.add(strDate);

					DiaryDO dy = new DiaryDO();
					for (String item : dateAl) {
						dy.setdDate(item);
					}

					DiaryDAO dao = new DiaryDAO();
					DiaryDO result = dao.selectDate(dy);

					if (result.getTitle() == null) {
						goInsert();
					} else if (result.getTitle() != null) {
						goPrint();
					}

				}

		);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	private void goPrint() {
		try {
			// 새로운 창 띄우기 (새 스테이지 생성 -> 씬 추가 -> 레이아웃 추가)
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../../view/diaryPrint.fxml"));
			Scene sc = new Scene(root);
			stage.setScene(sc);
			stage.show();

			// 메인 창 닫아주기
			Stage main = (Stage) getScene().getWindow();
			main.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void goInsert() {
		try {
			// 새로운 창 띄우기 (새 스테이지 생성 -> 씬 추가 -> 레이아웃 추가)
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("../../view/calendarInput.fxml"));
			Scene sc = new Scene(root);
			stage.setScene(sc);
			stage.show();

			// 메인 창 닫아주기
			Stage main = (Stage) getScene().getWindow();
			main.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
