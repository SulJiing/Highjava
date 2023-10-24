package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T04ServletCookieTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	/*
	 	쿠키 정보에 대하여...
	 	 => 웹서버와 브라우저는 애플리케이션을 사용하는 동안 필요한 값을 쿠키를 통해 공유하여 상태를 유지함.
	 	 
	 	 1. 구성요소?
	 	 - 이릉
	 	 - 값
	 	 - 유효시간(초)
	 	 - 도메인 : ex) www.somehost.com, .somehost.com
	 	 - 경로 : 쿠키를 공유할 기준경로를 지정한다. => 지정하지 않으며 실행한 URL의 경로부분이 사용됨
	 */
//		setCookieExam(req,resp); // 쿠키 생성 예제
		
//		readCookieExam(req,resp); // 쿠키정보 읽기 예제
		
		deleteCookieExaxm(req,resp); // 쿠키정보 삭제 예제
	}
	
	private void deleteCookieExaxm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	/*
	 	사용 중인 쿠키정보를 삭제하는 방법...
	 	
	 	1. 사용중인 쿠키정보를 이용하여 쿠키객체를 생성한다.
	 	
	 	2. 쿠키객체의 최대지속(유효)시간을 0으로 설정한다.
	 	
	 	3. 설정한 쿠키객체를 응답헤더에 추가하여 전송한다.
	 */
		Cookie [] cookies = req.getCookies();
		
		/////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 삭제 예제";
		
		out.print("<!DOCTYPE html><head><title>" + title + "</title></head><body>");
		if (cookies != null) {
			out.print("<h2>" + title + "</h2>");

			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userId")) {
					// 쿠키 제거하기
					cookie.setMaxAge(0);

					resp.addCookie(cookie);
					out.print("<p>삭제한 쿠키 : " + cookie.getName() + "</p><br>");
				}
				out.print("<p>name : " + cookie.getName() + "</p><br>");
				out.print("<p>value : " + URLDecoder.decode(cookie.getValue(), "UTF-8") + "</p><br>");
				out.print("<hr>");
			}
		} else {
			out.print("<h2>쿠키정보가 없습니다. </h2>");
		}
		out.print("</body></html>");
	}
		


	private void readCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Cookie [] cookies = req.getCookies();
		
		/////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키정보 읽기 예제";
		
		out.print("<!DOCTYPE html><head><title>" + title + "</title></head><body>");
		if (cookies != null) {
			out.print("<h2>" + title + "</h2>");

			for (Cookie cookie : cookies) {
				out.print("<p>name : " + cookie.getName() + "</p><br>");
				out.print("<p>value : " + URLDecoder.decode(cookie.getValue(),"UTF-8") + "</p><br>");
				out.print("<hr>");
			}
		} else {
			out.print("<h2>쿠키정보가 없습니다. </h2>");
		}
		out.print("</body></html>");
	}

	private void setCookieExam(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		req.setCharacterEncoding("UTF-8");

		// 쿠키 생성하기
		Cookie userId = new Cookie("userId", req.getParameter("userId"));

		// 쿠키값에 한글을 사용시 인코딩 처리를 해준다.
		Cookie name = new Cookie("name", URLEncoder.encode(req.getParameter("name"), "UTF-8"));

		// 쿠키 소멸시간 설정(초단위) => 지정하지 않으면 브라우저 종료시 쿠기가 함께 삭제된다.
		userId.setMaxAge(60 * 60 * 24); // 1일
		userId.setHttpOnly(true); // javascript를 이용한 직접 접근 방지
		
		name.setMaxAge(60*60*48);
		
		/////////////////////////////////////////////////////////
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		// 응답헤더에 쿠키정보 추가하기
		resp.addCookie(userId);
		resp.addCookie(name);
		
		PrintWriter out = resp.getWriter();
		
		String title = "쿠키설정 예제";
		
		out.print("<!DOCTYPE html><html><head><title>"+title+"</title></head>"
		+"<body><h1 align=\"center\">"+title+"</h1>"
				+"<ul><li><b>ID</b>: "+ req.getParameter("userId")+"</li>"
				+"<li><b>이름</b>: " + req.getParameter("name")+"</li></ul></body></html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}
}
