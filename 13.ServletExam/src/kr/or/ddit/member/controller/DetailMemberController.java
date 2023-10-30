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

@WebServlet("/member/detail.do")
public class DetailMemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		IMemberService memService = MemberServiceImpl.getInstance();
		MemberVO mv = new MemberVO(memId,null,null,null);
		
		List<MemberVO> memList = memService.searchMember(mv);
		
		req.setAttribute("memList", memList);
		
		req.getRequestDispatcher("/views/member/detail.jsp").forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
//	req.setCharacterEncoding("UTF-8");
//	resp.setContentType("text/html");
//
//	String memId = req.getParameter("memId");
//	String memName = req.getParameter("memName");
//	String memTel = req.getParameter("memTel");
//	String memAddr = req.getParameter("memAddr");
//	
//	MemberVO mv = new MemberVO(memId,memName,memTel,memAddr);
//	
//	IMemberService memService = MemberServiceImpl.getInstance();
		doGet(req, resp);
	}
}
