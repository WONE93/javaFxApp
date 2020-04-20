package co.yedam.app.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import co.yedam.app.model.EmpDAO;
import co.yedam.app.model.EmpDO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class EmpController implements Initializable {

	@FXML
	TextField txtEmployeeId;
	@FXML
	TextField txtLastName;
	@FXML
	TextField txtEmail;
	@FXML
	TextField txtHireDate;
	@FXML
	TextField txtJobId;
	
	@FXML
	TableView<EmpDO> tvEmp;

	@FXML	TableColumn<OpservEmpDO, String> colEmployeeId;
	@FXML	TableColumn<OpservEmpDO, String> colLastName;
	@FXML   TableColumn<OpservEmpDO, String> colEmail;
	@FXML	TableColumn<OpservEmpDO, String> colHireDate;
	@FXML	TableColumn<OpservEmpDO, String> colJobId;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { 
		// 콘트롤러가 임포트될때최소 로딩될때 초기화시켜주기위해 필요한 매소드
		//텍스트컴럼 값 불러오기
		colEmployeeId.setCellValueFactory(new PropertyValueFactory<OpservEmpDO, String>("employeeId"));
		colLastName.setCellValueFactory(new PropertyValueFactory<OpservEmpDO, String>("lastName"));
		colEmail.setCellValueFactory(new PropertyValueFactory<OpservEmpDO, String>("email"));
		colHireDate.setCellValueFactory(new PropertyValueFactory<OpservEmpDO, String>("hireDate"));
		colJobId.setCellValueFactory(new PropertyValueFactory<OpservEmpDO, String>("jobId"));
		

	}

	@FXML // 등록버튼 Click
	public void empInsert(ActionEvent actionEvent) {
		// DAO
		// 텍스트필드값을 읽어서 DO 객체 담기
		EmpDO emp = new EmpDO();
		emp.setEmployeeId(txtEmployeeId.getText());
		emp.setLastName(txtLastName.getText());
		emp.setEmail(txtEmail.getText());
		emp.setHireDate(txtHireDate.getText());
		emp.setJobId(txtJobId.getText());

		// DAO 등록
		EmpDAO dao = new EmpDAO();
		dao.insert(emp);

		System.out.println("등록처리됨");
	}

	@FXML
	public void empUpdate(ActionEvent actionEvent) {
		System.out.println("수정처리됨");
	}

	@FXML // 한건조회버튼
	public void empSelect(ActionEvent actionEvent) {
		// 조회할 사번을 DO 객체에 담기
		EmpDO emp = new EmpDO();
		emp.setEmployeeId(txtEmployeeId.getText());

		// 단건조회
		EmpDAO dao = new EmpDAO();
		EmpDO result = dao.selectOne(emp);

		// 텍스트필드에 표시
		txtEmployeeId.setText(result.getEmployeeId());
		txtEmail.setText(result.getEmail());
		txtHireDate.setText(result.getHireDate());
		txtJobId.setText(result.getJobId());
		txtLastName.setText(result.getLastName());

		System.out.println("조회처리됨");
	}

	@FXML // 전체조회버튼 클릭이벤트 핸들러
	public void empSelectAll(ActionEvent actionEvent) {
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpDO> list = dao.selectAll();
		tvEmp.setItems(FXCollections.observableArrayList(list));
	}
	
	@FXML // 테이블뷰 온클릭 마우스에 걸어줌(전체조회)
	public void empInfo(MouseEvent mouseEvent) {
		EmpDO empDO = tvEmp.getSelectionModel().getSelectedItem();
		txtEmployeeId.setText(empDO.getEmployeeId());
		txtLastName.setText(empDO.getLastName());
		txtEmail.setText(empDO.getEmail());
		txtHireDate.setText(empDO.getHireDate());
		txtJobId.setText(empDO.getJobId());
	}
	
	@FXML //삭제
	protected void delEmp(ActionEvent event) {
        EmpDO empDO = tvEmp.getSelectionModel().getSelectedItem();
        ObservableList<EmpDO> data = tvEmp.getItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("삭제 메시지");
        alert.setHeaderText("칼럼을 삭제하려합니다.");
        alert.setContentText("정말 삭제하시겠습니까?");

        Optional<ButtonType>result = alert.showAndWait();
        
        if(result.get() == ButtonType.OK) {
        	EmpDAO dao = new EmpDAO();
        	//DB에서 삭제
        	dao.delete(empDO);
        	//tableview 에서 삭제
        	data.remove(empDO);
        }
	}
}
