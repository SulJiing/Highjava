package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T03ServletParameterTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 	요청 객체로부터 파라미터 값을 가져오는 방법
		 	
		 	- getParameter() - 파라미터 값이 한개인 경우에 가져올 때 사용함
		 	- getParameterValues() - 파라미터 값이 여러개인 경우 사용함 	ex) checkbox 등
		 	- getParameterNames() - 요청 객체에 존재하는 모든 파라미터 이름을 가져올 때 사용함
		 */
		req.setCharacterEncoding("UTF-8"); // 꺼내기 전에 해야 함.
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String gender = req.getParameter("gender");
		String hobby = req.getParameter("hobby");
		String rlgn = req.getParameter("rlgn");
	
		String [] foods = req.getParameterValues("food");
		
		/////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html></html>");
		out.print("<p>username : " + username + "</p>");
		out.print("<p>password : " + password + "</p>");
		out.print("<p>gender : " + gender + "</p>");
		out.print("<p>rlgn : " + rlgn + "</p>");
		
		if (foods != null) {
			for(String food : foods) {
				out.print("<p>food : "+food+"</p>");
			}
		}
		Enumeration<String> params = req.getParameterNames();
		
		while(params.hasMoreElements()) {
			String paramName = params.nextElement();
			out.print("<hr>");
			out.print("<p>파라미터 이름 : "+paramName+"</p>");
			out.print("<p>파라미터 이름 : "+req.getParameter(paramName)+"</p>");
			out.print("<hr>");
		}
		out.print("</body></html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp); // GET인지 POST인지 상관이 없는 경우(같이 사용할 수 있게 됨)
	}
}
