package co.yedam.diary.view.calendar;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import co.yedam.diary.model.DiaryDAO;
import co.yedam.diary.model.DiaryDO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalendarController implements Initializable {

	@FXML
	Button btnLeft;
	
	@FXML
	Button btnRight;
	
	@FXML
	Label yearTop;
	
	@FXML
	GridPane calDate;
	
	@FXML
	GridPane calDay;
	
    @FXML
    Button btnList;
    
    @FXML
    BorderPane rootLayout;
	
	ArrayList<GridPaneNode> allCalendarDays = new ArrayList<>(35);	
	
	Calendar cal = Calendar.getInstance(); 
	Date date = cal.getTime(); 
	int year = cal.get(Calendar.YEAR); 
	int month = cal.get(Calendar.MONTH);
	int day = cal.get(Calendar.DATE);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		date();
		
	} // end of initialize
	
	@FXML
	public void goList(ActionEvent actionEvent) {
		goList();
	}
	
	@FXML // next버튼 click
	public void leftClick(ActionEvent actionEvent) {
		calDate.getChildren().remove(1, calDate.getChildren().size());  
		
		if(month <= 0) {
			month = 12;
			--year;
		};
		
		--month;
		cal.set(year,month, day);
		date();
		
	}//end of leftClick
	
	@FXML // next버튼 click
	public void rightClick(ActionEvent actionEvent) {
		calDate.getChildren().remove(1, calDate.getChildren().size());  

		if(month >= 11) {
			month = 1;
			++year;
		};
		
		++month;
		cal.set(year,month, day);
		date();
	}//end of rightClick
	
	//필요없음
//	@FXML
//	public void calInputView(ActionEvent event) {
//		try {
//			BorderPane diaryInputView = FXMLLoader.load(getClass().getResource("./src/view/diaryInput.fxml"));
//			Scene scene = new Scene(diaryInputView);
//
//			Stage window = new Stage();
//			window.setScene(scene);
//
//			// make window visible
//			window.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}//end of calInputView
	
	public void date(){

		yearTop.setText(year + "년 " + (month + 1)+ "월");
		
		//cal.set(year,month, day); //해당월
		int totaldate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //해당월의 최대 일수 
		
		cal.set(Calendar.DAY_OF_MONTH,1); //DAY_OF_MONTH를 1로 설정 (월의 첫날)
		int week = cal.get(Calendar.DAY_OF_WEEK); //그 주의 요일 반환 (일:1 ~ 토:7)
		
		Label[] a = new Label[35];
//		Label[] b = new Label[35];
		int i = 0;
		int k = week-1;
		

		for(int j = 1; j < (totaldate+1); j++) {
			
			GridPaneNode ap = new GridPaneNode();
			LocalDate calendarDate = LocalDate.of(year, month+1, j);
			ap.setDate(calendarDate);
			calDate.add(ap,k,i);
			allCalendarDays.add(ap);
			
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        String strDate = formatter.format(calendarDate);
//	        System.out.println(strDate);
	        
	        ArrayList<String> dateAl = new ArrayList<String>();
	        dateAl.add(strDate);
	        
	        DiaryDO dy = new DiaryDO();
			for(String item: dateAl) {
				dy.setdDate(item);
//				txtIdx.setText(item);
	        }
			
			DiaryDAO dao = new DiaryDAO();
			DiaryDO result = dao.selectDate(dy);
//			String gTit = result.getTitle();
			
			
			if(result.getIdx() == null) {
				a[i] = new Label(j + " \r\r");
			}else { // date의 갑이 null이 아니면 weather를 get하게
				a[i] = new Label(j + " \r\r" + result.getWeather());
			}
			
			calDate.add(a[i], k, i);
			
			a[i].setStyle("-fx-padding : 8px; -fx-max-height:1px;");
//			b[i].setStyle("-fx-padding : 25px; -fx-max-height:1px;");

			
			k++;
			if(k%7 ==0) {
				i++;
				k =0;
			}
			
		}		
		
		System.out.println("년월일" + year + "," + month + "," + day);
		System.out.println("week" + week);
		
	}//end of date
	

	
	public void goList() {
		try {
			Stage stage = new Stage();

			Parent root = FXMLLoader.load(getClass().getResource("../../view/diaryList.fxml"));
			Scene scene = new Scene(root, 600, 430);
			stage.setScene(scene);
			stage.show();
			
			// 메인 창 닫아주기
	          Stage main = (Stage) rootLayout.getScene().getWindow();
	          main.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} //goList
		
}
