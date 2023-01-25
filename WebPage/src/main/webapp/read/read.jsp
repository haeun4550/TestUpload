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
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/read.css">
<link rel="stylesheet" href="../css/common.css">
<body>
	<%@ include file="../header/header.jsp"%>
	<%
	String sId = (String) session.getAttribute("id");
	String board = request.getParameter("board");
	String dbBoard = null;
	if(board!=null&&board.equals("member")){
		dbBoard = Db.TABLE_BOARD;
	}else if(board!=null&&board.equals("free")){
		dbBoard = Db.TABLE_FREEBOARD;
	}
	if (sId == null) {
		sId = "익명";
	} else {
		sId = (String) session.getAttribute("id");
	}
	String n = request.getParameter("n");
	String cmd = request.getParameter("cmd");
	Dao dao = new Dao();
	if (cmd != null && cmd.equals("rec")) {
		String sql = String.format("update %s set recommend=recommend+1,hit=hit-1 where n=%s", dbBoard, n);
		dao.update(sql);
	}
	if (cmd != null && cmd.equals("comment")) {
		String sql = String.format("update %s set hit=hit-1 where n=%s", dbBoard, n);
		dao.update(sql);
	}
	String sql = String.format("update %s set hit=hit+1 where n=%s", dbBoard, n);
	dao.update(sql);
	Dto d = dao.read(n,dbBoard);
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
							href="read.jsp?n=<%=n%>&cmd=rec&board=<%=board%>"><img src="../img/heart.png"
							height="15" width="15"></a>
						<%=d.recommend%>
					</div>
					<div class="docEdit">
						<a href="../edit/edit.jsp?n=<%=n%>&board=<%=board%>">수정하기</a> / <a
							href="/ServletDel?n=<%=n%>&board=<%=board%>&dbBoard=<%=dbBoard%>">삭제하기</a>
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
				ArrayList<Dto> comments = dao.commentList(n,board);
				for (int i = 0; i < comments.size(); i++) {
				%>
				<%=comments.get(i).re_id%>
				:
				<%=comments.get(i).comment%>
				<br>
				<%
				}
				%>
				<form action="/ServletComment">
					<%=sId%>
					<input name="postNum" type="hidden" value="<%=n%>">
					<input name="re_id" type="hidden" value="<%=sId%>"> 
					<input name="board" type="hidden" value="<%=board%>"> 
					<input name="dbBoard" type="hidden" value="<%=dbBoard%>"> 
					<input name="comment" placeholder="댓글입력">
				</form>
			</div>
		</div>
		<div class="right_mid"></div>
	</div>

</body>
</html>