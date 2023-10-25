<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

// 	List<MemberVO> memList =(List<MemberVO>) request.getAttribute("memList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
</head>
<body>
	<form action="./detail.do" method="post"></form>
	<table border="1">
		<tr>
			<td>I D:</td>
			<td></td>
		</tr>
		<tr>
			<td>이름:</td>
			<td></td>
		</tr>
		<tr>
			<td>전화번호:</td>
			<td></td>
		</tr>
		<tr>
			<td>주소:</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="2">
			<a href="<%=request.getContextPath() %>/member/list.do">[목록]</a>
			<a href="<%=request.getContextPath() %>/member/update.do">[회원정보 수정]</a>
			<a href="#">[회원정보 삭제]</a>
			</td>
		</tr>
	</table>
</body>
</html>