package service;

import java.util.List;

import vo.VO;

public interface IBoardService {
	
	public int registerBoard(VO vo);
	
	public boolean checkBoard(String no);
	
	public int modifyBoard(VO vo);
	
	public int remove(String title);
	
	public List<VO> displayAll();
	
	List <VO> searchBoard(VO vo);
}
