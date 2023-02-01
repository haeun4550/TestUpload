<%@page import="com.haeun.webPage.dao.Dao"%>
<%@page import="com.haeun.webPage.db.Db"%>
<%@page import="com.haeun.weaPage.dto.Dto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/common.css">
<link rel="stylesheet" href="/css/board.css">
<body>
<%
Dao dao = new Dao();
String sId = (String) session.getAttribute("id");
Dto dto = dao.selectMember(sId);


%>
	<%@ include file="/header/header.jsp"%>
	<div class="mid">
		<div id="left_mid">
		<div id="bar"></div>
			<div id="boardList">
				<a href="/myPage/myPage.jsp?id=<%=sId%>">내정보</a><br>
				<a href="/myPage/myPost.jsp?id=<%=sId%>">글관리</a>
			</div>
		</div>
		<div id="mid_mid">
			<div id="boardTitle">
				<br>
				<h2>글관리</h2>
				<div id="bar2"></div>
			</div>
			<br>
			<div>
				<h4>🔸작성한 게시글 : <%=dto.id%></h4> <br>
				<h4>🔸작성한 댓글 : <%=dto.password%></h4><br>
				<hr>
			</div>
		</div>
	</div>
	<div id="right_mid"></div>





</body>
</html>