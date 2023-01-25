<%@page import="com.haeun.webPage.db.Db"%>
<%@page import="com.haeun.weaPage.dto.Dto"%>
<%@page import="com.haeun.webPage.dao.Dao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/write.css">
<link rel="stylesheet" href="../css/common.css">
<body>
<%
String n = request.getParameter("n");
String board = request.getParameter("board");
String dbBoard= null;
Dao dao = new Dao();
if(board!=null&&board.equals("member")){
	dbBoard= Db.TABLE_BOARD;
}else if(board!=null&&board.equals("free")){
	dbBoard = Db.TABLE_FREEBOARD;
}
Dto d = dao.read(n,dbBoard);
%>
<%@ include file="../header/header.jsp"%>
<div class="mid">
<div class="left_mid"></div>
<div class="mid_mid">
	<div id="boardTitle">
				<br>
				<h2>수정하기</h2>
				<div id="bar"></div>
			</div> <br> <br>
			<div class="writeBox">
			<form action="/ServletEdit">
		<input name="n" type="hidden" value=<%=n%>>
		<input name="dbBoard" type="hidden" value=<%=dbBoard%>>
		<input name="board" type="hidden" value=<%=board%>>
			<div id="writeTitle">
	<input name="title" value=<%=d.title%> autofocus>
	</div> <hr>
	<div id="writeContent">
	<textarea name="content"><%=d.content%></textarea>
	</div> <br>
	<div id="writeSumitButton">
	<input type="submit" value="수정하기" style="background-color:#ffdb99;border-style: none;width:60px;height: 25px">
	</div>
	</form>
			</div>
	
</div>
<div class="right_mid"></div>


</div>

</body>
</html>