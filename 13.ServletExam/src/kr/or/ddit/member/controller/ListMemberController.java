package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet(value = "/member/list.do") //.do는 sevlet 느낌을 살리기 위함
public class ListMemberController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		List<MemberVO> memList = memService.displayAllMember();
		
		req.setAttribute("memList", memList);

		req.getRequestDispatcher("/views/member/list.jsp").forward(req, resp); 
		// forward는 백엔드에서 처리할 수 있는 부분으로 넘겨주는 것(사용자는 모름)
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
