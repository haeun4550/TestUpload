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
Dao dao = new Dao();
Dto d = dao.read(n);

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