<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.nowon.green.dto.MemoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<a href="<%=request.getContextPath() %>/">HOME</a>
	<h1>메모 리스트 페이지 입니다.</h1>
	<table>
		<thead>
			<tr> 
				<th>번호</th>
				<th>메모내용</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>				
		</thead>
		<tbody>
<%-- 		   <% 
 			List<MemoDTO> list=(List<MemoDTO>)request.getAttribute("list"); 			for(MemoDTO dto : list){
			%>
			<tr> 
				<td><%=dto.getNo() %></td>
				<td><%=dto.getContent() %></td>
				<td><%=dto.getWriter() %></td>
				<td><%=dto.getCdate() %></td>
 			</tr> 
			<%
			}
			%>  --%>
	<c:forEach var="dto" items="${requestScope.list}">
		<tr>
			<td>${dto.no }</td>
			<td>${dto.content }</td>
			<td>${dto.writer }</td>
			<td>${dto.cdate }</td>
		</tr>
	</c:forEach>
		</tbody>
	</table>

</body>
</html>