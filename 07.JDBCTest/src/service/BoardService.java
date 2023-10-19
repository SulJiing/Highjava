package service;

import java.util.List;

import memberdao.BoardDao;
import memberdao.BoardDaoMyBatis;
import memberdao.IBoardDao;
import vo.VO;

public class BoardService implements IBoardService{
	
	private IBoardDao boDao;
	
	public BoardService() {
//		boDao = new BoardDao();
		boDao = new BoardDaoMyBatis();
	}

	@Override
	public int registerBoard(VO vo) {
		int cnt = boDao.insert(vo);
		return cnt;
	}

	@Override
	public boolean checkBoard(String no) {
		return boDao.check(no);
	}

	@Override
	public int modifyBoard(VO vo) {
		int cnt = boDao.update(vo);
		return cnt;
	}

	@Override
	public int remove(String title) {
		int cnt = boDao.delete(title);
		return cnt;
	}

	@Override
	public List<VO> displayAll() {
		List<VO> voList = boDao.displayAll();
		return voList;
	}

	@Override
	public List<VO> searchBoard(VO vo) {
		List<VO> boList = boDao.search(vo);
		return boList;
	}
}
