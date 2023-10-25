<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>

<script type="text/javascript">
function send(id) {
    // 클릭한 <tr> 요소의 ID 값을 가져옵니다.
    console.log(id+" Hi");

    // 다른 페이지로 이동하거나 데이터를 전달할 수 있습니다.
    // 여기에서는 경로를 이동하는 예제를 보여줍니다.
     window.location.href = "<%=request.getContextPath()%>/member/detail.do?memId=" + id;
// window.onload = function(){
// 	const memDataRows = document.getElementsByClassName("memData");
// 	for(let i=0; i<memDataRows.length; i++){
// 		memDataRows[i].addEventListener("click", function(){
// 			const memId = this.getAttribute("id");
// 			console.log(memId)
<%-- 			location.href = "<%=request.getContextPath() %>/member/detail.do?memId=" + memId  --%>
// 		});
// 	}
}
</script>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>주소</th>
		</tr>
<%
	int memSize = memList.size();
	if(memSize > 0) {
		for(MemberVO mv : memList) {
%>
<%-- 		<tr <!-- class="memData" id="// <%=mv.getMemId()%>" -->> --%>
			<tr>
			<td onclick="send('<%=mv.getMemId()%>')"><a href="<%=request.getContextPath() %>/member/detail.do"><% out.print(mv.getMemId()); %></a></td>
			<td><%=mv.getMemName() %></td>
			<td><%=mv.getMemTel() %></td>
			<td><%=mv.getMemAddr() %></td>
		</tr>
<%
		}
	} else {
%>
		<tr>
			<td colspan = "4">회원정보가 존재하지 않습니다.</td>
		</tr>
<%
	}
%>
	<tr align="center">
		<td colspan="4"><a href="<%=request.getContextPath() %>/member/insert.do">[회원 등록]</a></td>
	</tr>

	<tr align="center">
		<td colspan="4"><a href="<%=request.getContextPath() %>/member/update.do">[회원 정보 변경]</a></td>
	</tr>
	
	</table>

</body>
</html>