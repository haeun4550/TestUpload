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
	String sId = (String)session.getAttribute("id");
	if(sId==null){
		sId="익명";
	}else{
		sId = (String)session.getAttribute("id");
	}
	String n = request.getParameter("n");
	Dao dao = new Dao();
	String sql = String.format("update %s set hit=hit+1 where n=%s",Db.TABLE_BOARD,n);
	dao.update(sql);
	Dto d = dao.read(n);
	%>
	<div class="mid">
	<div class="left_mid"></div>
	<div class="mid_mid">
	<div class="docInfo"><h1><%=d.title%></h1>
	<h3><%=d.id%></h3>
	<div class="docInfo2">
	<div>
	<span style="color: gray"><%=d.dt%> | 조회수 : <%=d.hit%></span>
	💛<%=d.recommend%>
	</div>
	<div class="docEdit">
	<a href="../edit/edit.jsp?n=<%=n%>">수정하기</a> / <a href="/ServletDel?n=<%=n%>">삭제하기</a>
	</div>
	</div>
	</div>
	<hr> <br>
	<div class="docContent">
	<%=d.content%>
	</div>
	
	<hr>
	<div class="comment">
	<%
	ArrayList<Dto> comments = dao.commentList(n);
	for(int i=0;i<comments.size();i++){%>
		<%=comments.get(i).re_id%> : <%=comments.get(i).comment%>
		<br>
	<%	
	}
	%>
	<form action="/ServletComment">
	<%=sId%>
	<input name="postNum" type="hidden" value="<%=n%>">
	<input name="re_id" type="hidden" value="<%=sId%>">
	<input name="comment" placeholder="댓글입력">
	
	</form>
	</div>
	</div>
	<div class="right_mid"></div>
	
	
	
	
	
	</div>
	
</body>
</html>