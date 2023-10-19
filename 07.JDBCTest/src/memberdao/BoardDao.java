package memberdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import vo.VO;

public class BoardDao implements IBoardDao{
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insert(VO vo) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();

			String sql = " insert into jdbc_board ( board_no, board_title, board_writer, board_date, board_content ) "
					+ " values ( board_seq.nextVal, ?, ?, sysdate, ?) ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getText());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int delete(String title) {

		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();

			String sql = " delete from jdbc_board where board_title = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setNString(1, title);

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return 0;
	}

	@Override
	public int update(VO vo) {
		
		int cnt = 0;
		
		try {
			conn = JDBCUtil.getConnection();

			String sql = " update jdbc_board set "
					+ " board_title = ? , board_writer = ?, board_content = ? "
					+ " where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getText());
			pstmt.setString(4, vo.getNo());
			System.out.println(vo.getTitle());
			cnt = pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<VO> displayAll() {

		
		List<VO> voList = new ArrayList<VO>();
		
		try {
			conn = JDBCUtil.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(" select * from jdbc_board");

			conn.commit();

			while (rs.next()) {
				String no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String name = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String text = rs.getString("board_content");

				VO vo = new VO();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setName(name);
				vo.setDate(date);
				vo.setText(text);
				
				voList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return voList;
	}
	
	@Override
	public boolean check(String no) {
		boolean isExist = false;
		
		VO vo = new VO();

		try {
			conn = JDBCUtil.getConnection();

			String sql = " select count(*) as cnt from jdbc_board where board_no = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, no);

			rs = pstmt.executeQuery();

			int cnt = 0;

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}
	
	@Override
	public List<VO> search(VO vo) {

		List<VO> boList = new ArrayList<VO>();

		try {
			conn = JDBCUtil.getConnection();

			String sql = "select * from jdbc_board where 1=1";
			if (vo.getTitle() != null && !vo.getTitle().equals("")) {
				sql += " and board_title = ? ";
			}
			if (vo.getName() != null && !vo.getName().equals("")) {
				sql += " and board_writer = ? ";
			}
			if (vo.getText() != null && !vo.getText().equals("")) {
				sql += " and board_content like '%' || ? || '%' ";
			}

			pstmt = conn.prepareStatement(sql);

			int index = 1;

			if (vo.getTitle() != null && !vo.getTitle().equals("")) {
				pstmt.setString(index++, vo.getTitle());
			}
			if (vo.getName() != null && !vo.getName().equals("")) {
				pstmt.setString(index++, vo.getName());
			}
			if (vo.getText() != null && !vo.getText().equals("")) {
				pstmt.setString(index++, vo.getText());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String name = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String text = rs.getString("board_content");

				VO vo2 = new VO();
				vo2.setTitle(title);
				vo2.setName(name);
				vo2.setText(text);
				vo2.setNo(no);
				vo2.setDate(date);
				
				boList.add(vo2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return boList;
		}
}