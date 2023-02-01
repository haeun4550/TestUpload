<%@page import="java.util.ArrayList"%>
<%@page import="com.haeun.weaPage.dto.Dto"%>
<%@page import="com.haeun.webPage.dao.Dao"%>
<%@page import="com.haeun.webPage.db.Db"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/read.css">
<link rel="stylesheet" href="/css/common.css">
<body>
	<%@ include file="/header/header.jsp"%>
	<%
	String sId = (String) session.getAttribute("id");
	String board = (String)request.getAttribute("board");
	String dbBoard = (String)request.getAttribute("dbboard");
	
	if (sId == null) {
		sId = "익명";
	} else {
		sId = (String) session.getAttribute("id");
	}
	Dto d = (Dto)request.getAttribute("post");
	%>
	
	
	<div class="mid">
		<div class="left_mid"></div>
		<div class="mid_mid">
			<div class="docInfo">
				<h1><%=d.title%></h1>
				<h3><%=d.id%></h3>
				<div class="docInfo2">
					<div>
						<span style="color: gray"><%=d.dt%> | 조회수 : <%=d.hit%></span> <a
							href="/page/read?n=<%=d.n%>&cmd=rec&board=<%=board%>"><img src="../img/heart.png"
							height="15" width="15"></a>
						<%=d.recommend%>
					</div>
					<div class="docEdit">
						<a href="/page/edit?n=<%=d.n%>&board=<%=board%>&category=<%=d.category%>">수정하기</a> / <a
							href="/page/del?n=<%=d.n%>&board=<%=board%>&dbBoard=<%=dbBoard%>">삭제하기</a>
					</div>
				</div>
			</div>
			<hr>
			<br>
			<div class="docContent">
				<%=d.content%>
			</div>
			💬<%=d.commentCnt%>
			<hr>
			<div class="comment">
				<%
				ArrayList<Dto> comments =(ArrayList<Dto>)request.getAttribute("comments");
				for (int i = 0; i < comments.size(); i++) {
				%>
				<%=comments.get(i).re_id%>
				:
				<%=comments.get(i).comment%>
				<br>
				<%
				}
				%>
				<form action="/page/comment">
					<%=sId%>
					<input name="postNum" type="hidden" value="<%=d.n%>">
					<input name="re_id" type="hidden" value="<%=sId%>"> 
					<input name="board" type="hidden" value="<%=board%>"> 
					<input name="dbBoard" type="hidden" value="<%=dbBoard%>"> 
					<input name="comment" placeholder="댓글입력">
				</form>
			</div>
		</div>
<!-- 		<div class="right_mid"></div> -->
	</div>

</body>
</html>