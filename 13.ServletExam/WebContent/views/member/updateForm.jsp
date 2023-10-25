<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String errMsg = (String)request.getAttribute("errMsg");
	MemberVO mv = (MemberVO)request.getAttribute("memVo");
	if(mv==null){
		mv = new MemberVO("","","","");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 변경</title>
<%
if(errMsg != null){
%>
<script type="text/javascript">
	alert("<%=errMsg%>")
</script>
<%} %>
</head>
<body>
	<form action="./update.do" method="post">
		<table>
			<tr>
				<td>I D:</td>
				<td><input type="text" name="memId" value="<%=mv.getMemId()%>"></td>
				<td></td>
			</tr>
			<tr>
				<td>이름:</td>
				<td><input type="text" name="memName" value="<%=mv.getMemName()%>"></td>
			</tr>
			<tr>
				<td>전화번호:</td>
				<td><input type="text" name="memTel" value="<%=mv.getMemTel()%>"></td>
			</tr>
			<tr>
				<td>주소:</td>
				<td><textarea name="memAddr" ><%=mv.getMemAddr()%></textarea></td>
			</tr>
		</table>
		<input type="submit" value="회원정보 수정">

	</form>
</body>
</html>