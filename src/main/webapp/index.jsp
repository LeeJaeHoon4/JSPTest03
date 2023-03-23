<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%= request.getContextPath() %>/memo/list">메모장</a>
	<h1>안녕하세요 웰컴 파일(기본 시작 페이지) 입니다</h1>
	<p><%= request.getContextPath() %></p>
</body>
</html>