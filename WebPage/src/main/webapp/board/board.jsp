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
	String board  = (String)request.getAttribute("board");
	String dbBoard  = (String)request.getAttribute("dbBoard");
	int blockStartNum  = (int)request.getAttribute("blockStartNum");
	int blockEndNum  = (int)request.getAttribute("blockEndNum");
	int nextPage  = (int)request.getAttribute("nextPage");
	int prevPage  = (int)request.getAttribute("prevPage");
	int totalPageNum  = (int)request.getAttribute("totalPageNum");
	boolean canPrev  = (boolean)request.getAttribute("canPrev");
	boolean canNext  = (boolean)request.getAttribute("canNext");
	String currentPage = request.getParameter("page");
	ArrayList<Dto> posts = (ArrayList<Dto>)request.getAttribute("posts");
	%>
	<%@ include file="/header/header.jsp"%>
	<div class="mid">
		<div id="left_mid">
			<div id="bar"></div>
			<div id="boardList">
				<a href="/page/board?board=free">자유게시판</a><br>
				<a href="/page/board?board=member">회원게시판</a>
			</div>
		</div>
		<div id="mid_mid">
			<div id="boardTitle">
				<br>
				<%if(dbBoard!=null&&board.equals("member")){%>
				<h2>회원게시판</h2>
				<%}else if(dbBoard!=null&&board.equals("free")){%>
				<h2>자유게시판</h2>
				<%}%>
				<div id="bar2"></div>
			</div>
			<br>
			<hr>
			<div class="post">
				<div class="postBar">
					<div>No</div>
					<div>제목</div>
					<div>작성자</div>
					<div>작성일</div>
					<div>조회수</div>
					<div>추천수</div>
				</div>
				<hr>
				<div class="postList">
					<%@ include file="/board/list_include.jsp"%>
				</div>
				<div class="mid_bottom">
					<div class="pageBlock">
						<a href="/page/board?page=1&board=<%=board%>&board=<%=board%>"><%="<<"%></a>
						<%
				if(canPrev){
					%>
						<a href="/page/board?page=<%=prevPage%>&board=<%=board%>"><%="<"%></a>
						<%
				}
				%>
						🔸
						<%
				if(blockEndNum>totalPageNum){
					for(int i=blockStartNum;i<=totalPageNum;i++){%>
						<a href="/page/board?page=<%=i%>&board=<%=board%>"><%=i%></a>
						<%
					}
				}else{
				for(int i=blockStartNum;i<=blockEndNum;i++){ %>
						<a href="/page/board?page=<%=i%>&board=<%=board%>"><%=i%></a>
						<%} 
						}%>
						🔸
						<%
				if(canNext){
					%>
						<a href="/page/board?page=<%=nextPage%>&board=<%=board%>"><%=">"%></a>
						<%}
						%>
					<a href="/page/board?page=<%=totalPageNum%>&board=<%=board%>"><%=">>"%></a>
					</div>
					<div class="doWriteBox"> 
					<a href="/write/write.jsp?board=<%=board%>">글쓰기</a>
					</div>
				</div>

			</div>
		</div>
<!-- 		<div id="right_mid"></div> 나중에 추가하면 dropdown하고 겹치는 이슈해결하기--> 
	</div>

</body>
</html>