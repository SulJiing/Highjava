package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
public class UpdateMemberController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String memId = req.getParameter("memId");
		
		MemberVO mv = new MemberVO(memId,"","","");

		req.setAttribute("memVo", mv);
		
		System.out.println(memId);
		
//		IMemberService memService = MemberServiceImpl.getInstance();
		
//		memService.modifyMember(mv);
		
//		resp.sendRedirect(req.getContextPath() + "/member/list.do");
		req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		
		MemberVO mv = new MemberVO(memId,memName,memTel,memAddr);
		
		IMemberService memService = MemberServiceImpl.getInstance();
		
		boolean isExist = memService.checkMember(memId);
		if(!isExist) {
			req.setAttribute("errMsg", "잘못된 아이디입니다.");
			req.setAttribute("memVo", mv);
			req.getRequestDispatcher("/views/member/updateForm.jsp").forward(req, resp);
			return;
		}
		

		int cnt = memService.modifyMember(mv);
		String msg = "";
		if(cnt>0) {
			msg="성공";
		} else {
			msg="실패";
		}
		resp.sendRedirect(req.getContextPath() + "/member/list.do");
	}
}
