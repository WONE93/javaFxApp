package co.yedam.app.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpDAO {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = null; // 블록밖에 변수선언해야 다른 블록에서도 사용가능

	// 등록
	public void insert(EmpDO emp) {
		// 1. connect (DB 연결)
		// 오류 발생할 수 있으니 예외처리해라 (트라이캐치로)
		try {
			conn = DriverManager.getConnection(url, "hr", "hr");
			// 2. statement(SQL 구문준비)
			String sql = "insert into employees (employee_id, last_name, email, hire_date, job_id)"
					+ " values (?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. execute
			pstmt.setString(1, emp.getEmployeeId());
			pstmt.setString(2, emp.getLastName());
			pstmt.setString(3, emp.getEmail());
			pstmt.setString(4, emp.getHireDate());
			pstmt.setString(5, emp.getJobId());
			pstmt.executeUpdate();

			// 4. 조회결과
		} catch (SQLException e) { // 에러나면 catch블록
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5. close(연결해제) : db의 커넥션은 유한적이라 해제해줘야함
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 수정

	// 한건조회
	public EmpDO selectOne(EmpDO emp) {
		EmpDO empDO = new EmpDO();
		// 1. connect (DB 연결)
		try {
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement(SQL 구문준비)
			String sql = "select * from employees where employee_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. execute
			pstmt.setString(1, emp.getEmployeeId());
			ResultSet rs = pstmt.executeQuery();
//			pstmt.executeQuery(); // DML이냐 DDL이냐에따라 이그제큐트랑 나뉨

			// 4. 결과처리
			if (rs.next()) {
				empDO.setEmployeeId(rs.getString("employee_id"));
				empDO.setLastName(rs.getString("last_name"));
				empDO.setEmail(rs.getString("email"));
				empDO.setHireDate(rs.getString("hire_date"));
				empDO.setJobId(rs.getString("job_id"));

			}
		} catch (SQLException e) { // 에러나면 catch블록
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5. close(연결해제) : db의 커넥션은 유한적이라 해제해줘야함
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return empDO;
	}

	// 전체조회
	public ArrayList<EmpDO> selectAll() {
		ArrayList<EmpDO> list = new ArrayList<EmpDO>();
//			EmpDO empDO = new EmpDO();
		try {
			// 1. connect (DB 연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement(SQL 구문준비)
			String sql = "select * from employees order by employee_id";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. execute
			ResultSet rs = pstmt.executeQuery();
//				pstmt.executeQuery(); // DML이냐 DDL이냐에따라 이그제큐트랑 나뉨

			// 4. 결과처리
			while (rs.next()) { // 여러건이라 if 아니라 while로 쓰기
				EmpDO empDO = new EmpDO();
				empDO.setEmployeeId(rs.getString("employee_id"));
				empDO.setLastName(rs.getString("last_name"));
				empDO.setEmail(rs.getString("email"));
				empDO.setHireDate(rs.getString("hire_date"));
				empDO.setJobId(rs.getString("job_id"));
				list.add(empDO);
			}
		} catch (SQLException e) { // 에러나면 catch블록
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5. close(연결해제) : db의 커넥션은 유한적이라 해제해줘야함
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 삭제
	public void delete(EmpDO emp) {

		// 1. connect (DB 연결)
		try {
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement(SQL 구문준비)
			String sql = "delete from employees where employee_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. execute
			pstmt.setString(1, emp.getEmployeeId());
			pstmt.executeUpdate();

			// 4. 조회결과
		} catch (SQLException e) { // 에러나면 catch블록
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 5. close(연결해제) : db의 커넥션은 유한적이라 해제해줘야함
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}// E OF D

}
