package co.yedam.test.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DailyDAO {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = null;

	// 입력(등록)
	public void insert(DailyDO Dy) {

		try {
			//1. DB connect (DB연결)
			conn = DriverManager.getConnection(url , "hr", "hr");

			
			//2. statement (SQL 구문준비)
			String sql = "insert into diary (idx, d_date,weather, title, contents) "
					+ " values((select nvl(max(idx),0)+1 from diary),?,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			//3. execute
			//pstmt.setDate(1, getCurrentDate());
			pstmt.setString(1, Dy.getdDate());
			pstmt.setString(2, Dy.getWeather());
			pstmt.setString(3, Dy.getTitle());
			pstmt.setString(4, Dy.getContents());
			
			pstmt.executeUpdate();
			//4. 조회결과 (등록이라 필요없음)
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				//5. close(연결해제)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
		// 수정

	public void update(Connection conn, DailyDO Dy) throws SQLException {
		String sql = "update diary set d_date = ? , weather = ?, title = ?, contents= ? " + "   where idx = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Dy.getdDate());
		pstmt.setString(2, Dy.getWeather());
		pstmt.setString(3, Dy.getTitle());
		pstmt.setString(4, Dy.getContents());

		int r = pstmt.executeUpdate();
		System.out.println(r + "건 수정완료");
	}

	// 삭제
	public void delete(DailyDO Dy) {

		try {
			conn = DriverManager.getConnection(url, "hr", "hr");

			String sql = "delete from diary where idx=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, Dy.getIdx());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	// 취소

	// 한건조회(리스트화면에서)
}
