package memberdao;

import java.util.List;

import vo.VO;

public interface IBoardDao {

	public int insert(VO vo);
	
	public int delete(String title);
	
	public int update(VO vo);
	
	public boolean check(String no);
	
	public List<VO> displayAll();
	
	public List<VO> search(VO vo);
	
}

	
