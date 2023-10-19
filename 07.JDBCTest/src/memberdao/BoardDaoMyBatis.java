package memberdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.JDBCUtil;
import util.MyBatisUtil;
import vo.VO;

public class BoardDaoMyBatis implements IBoardDao {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	

	@Override
	public int insert(VO vo) {
		
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;

		try {

			cnt = session.insert("board.insert", vo);
			
			session.commit();

		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int delete(String title) {
		
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;

		try {
			
			cnt = session.delete("board.delete", title);
			
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public int update(VO vo) {
		SqlSession session = MyBatisUtil.getInstance();
		int cnt = 0;

		try {
			cnt = session.update("board.update", vo);
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return cnt;
	}

	@Override
	public List<VO> displayAll() {
		
		SqlSession session = MyBatisUtil.getInstance();

		List<VO> voList = new ArrayList<VO>();

		try {
			voList = session.selectList("board.displayAll");
			session.commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return voList;
	}

	@Override
	public boolean check(String no) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		boolean isExist = false;

		try {
			int cnt = session.selectOne("board.check",no);
			session.commit();
			
			if (cnt > 0) {
				isExist = true;
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isExist;
	}

	@Override
	public List<VO> search(VO vo) {

		List<VO> boList = new ArrayList<VO>();
		
		SqlSession session = MyBatisUtil.getInstance();

		try {
			boList = session.selectList("board.search", vo);
			session.commit();
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return boList;
	}
}