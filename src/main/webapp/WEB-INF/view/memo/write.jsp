<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>">HOME</a>
	<a href="<%=request.getContextPath()%>/memo/list">메모 목록</a>
	<h1>글쓰기 페이지 입니다.</h1>
	<hr>
	<form action="<%=request.getContextPath()%>/memo/new" method="post">
		<p>
			<input type="text" name = "writer" placeholder="작성자">
		</p>
		<p>
			<input type="text" name = "content" placeholder="내용">
		</p>
		<button>글 저장</button>
	</form>
</body>
</html>