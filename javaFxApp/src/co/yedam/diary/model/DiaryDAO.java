package co.yedam.diary.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection conn = null;

	// 저장 -> 데이터 추가
	public void insert(DiaryDO Dy) {
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "insert into diary (idx, d_date,weather, title, contents) "
					+ " values((select nvl(max(idx),0)+1 from diary),?,?,?,?)";

			PreparedStatement pstmt = conn.prepareStatement(sql);

			// 3. execute
			// pstmt.setDate(1, getCurrentDate());
			pstmt.setString(1, Dy.getdDate());
			pstmt.setString(2, Dy.getWeather());
			pstmt.setString(3, Dy.getTitle());
			pstmt.setString(4, Dy.getContents());

			pstmt.executeUpdate();
			// 4. 조회결과 (등록이라 필요없음)

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				// 5. close(연결해제)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

//	static java.sql.Date getCurrentDate() {
//	    java.util.Date today = new java.util.Date();
//	    return new java.sql.Date(today.getTime());
//	}
	public List<DiaryDO> getDiaryList() {

		String sql = "select idx, to_char(d_date, 'yyyy-mm-dd') d_date, title from diary order by idx";
		List<DiaryDO> list = new ArrayList<>();

		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(); // 데이터를 가져와서 ResultSet에 담는다.
			while (rs.next()) {
				DiaryDO dy = new DiaryDO();
				dy.setIdx(rs.getString("idx"));
				dy.setdDate(rs.getString("d_date"));
				dy.setTitle(rs.getString("title"));

				list.add(dy);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 단건조회 idx
	public DiaryDO select(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "select idx, to_char(d_date, 'yyyy-mm-dd')  d_date, title, weather, contents from diary where idx = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getIdx());
			ResultSet rs = pstmt.executeQuery();

			// 4. 조회결과
			if (rs.next()) {
				diaryDO.setIdx(rs.getString("idx"));
				diaryDO.setdDate(rs.getString("d_date"));
				diaryDO.setWeather(rs.getString("weather"));
				diaryDO.setTitle(rs.getString("title"));
				diaryDO.setContents(rs.getString("contents"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}

	// 단건조회 date
	public DiaryDO selectDate(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "select idx, to_char(d_date, 'yyyy-mm-dd')  d_date, title, weather, contents from diary where d_date = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getdDate());
			ResultSet rs = pstmt.executeQuery();

			// 4. 조회결과
			if (rs.next()) {
				diaryDO.setIdx(rs.getString("idx"));
				diaryDO.setdDate(rs.getString("d_date"));
				diaryDO.setWeather(rs.getString("weather"));
				diaryDO.setTitle(rs.getString("title"));
				diaryDO.setContents(rs.getString("contents"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}

	// 수정
	public DiaryDO update(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "update diary set title = ?, weather = ?, contents = ? where idx = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getTitle());
			pstmt.setString(2, dy.getWeather());
			pstmt.setString(3, dy.getContents());
			pstmt.setString(4, dy.getIdx());

			pstmt.executeUpdate();
			// 4. 조회결과

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}

	// 삭제
	public DiaryDO delete(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "delete from diary where idx = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getIdx());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 조회결과 (등록이라 필요없음)
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}

	// 수정
	public DiaryDO update2(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "update diary set title = ?, weather = ?, contents = ? where d_date = ?";

			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getTitle());
			pstmt.setString(2, dy.getWeather());
			pstmt.setString(3, dy.getContents());
			pstmt.setString(4, dy.getIdx());

			pstmt.executeUpdate();
			// 4. 조회결과

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}

	// 삭제
	public DiaryDO delete2(DiaryDO dy) {
		DiaryDO diaryDO = new DiaryDO();
		try {
			// 1. DB connect (DB연결)
			conn = DriverManager.getConnection(url, "hr", "hr");

			// 2. statement (SQL 구문준비)
			String sql = "delete from diary where d_date = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 3. execute
			pstmt.setString(1, dy.getIdx());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 조회결과 (등록이라 필요없음)
			// 5. close(연결해제)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diaryDO;

	}
}
