<%@page import="com.haeun.webPage.board.BoardListProcessor"%>
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
	String board = (String) request.getAttribute("board");
	String dbBoard = (String) request.getAttribute("dbBoard");
	BoardListProcessor blp = (BoardListProcessor) request.getAttribute("blp");
	ArrayList<Dto> posts = blp.getPosts();
	%>
	<%@ include file="/header/header.jsp"%>
	<div class="mid">
		<div id="left_mid">
			<div id="bar"></div>
			<div id="boardList">
				<a href="/page/board?board=free" style="font-size: large;">자유게시판</a><br>
				<a href=/page/board?board=free&category=animal>동물게시판</a> <br> <a
					href=/page/board?board=free&category=game>게임게시판</a><br> <a
					href="/page/board?board=member" style="font-size: large;">회원게시판</a><br>
				<a href=/page/board?board=member&category=animal>동물게시판</a> <br>
				<a href=/page/board?board=member&category=game>게임게시판</a> <br>
			</div>
		</div>
		<div id="mid_mid">
			<div id="boardTitle">
				<br>
				<%if(board.equals("member")){
					if(dbBoard!=null&&blp.category==null){%>
						<h2>회원게시판-전체글</h2>
					<%}else if(blp.search != null && blp.category != null && blp.search.equals("null") && blp.category.equals("null")){%>
						<h2>회원게시판-전체글</h2>
					<%}else if(blp.search != null && blp.category.equals("null")){%>
						<h2>회원게시판-전체글</h2>
					<%}else if(blp.category != null && blp.category.equals("animal")){%>
						<h2>회원게시판-동물게시판</h2>
					<%}else if(blp.category != null && blp.category.equals("game")){%>
						<h2>회원게시판-게임게시판</h2>
					<% }
				}else if(board.equals("free")){
					if(dbBoard!=null&&blp.category==null){%>
						<h2>자유게시판-전체글</h2>
					<%}else if(blp.search != null && blp.category != null && blp.search.equals("null") && blp.category.equals("null")){%>
						<h2>자유게시판-전체글</h2>
					<%}else if(blp.search != null && blp.category.equals("null")){%>
						<h2>자유게시판-전체글</h2>
					<%}else if(blp.category != null && blp.category.equals("animal")){%>
						<h2>자유게시판-동물게시판</h2>
					<%}else if(blp.category != null && blp.category.equals("game")){%>
						<h2>자유게시판-게임게시판</h2>
					<%
					}
				}
				%>
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
						<a
							href="/page/board?page=1&board=<%=board%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%="<<"%></a>
						<%
						if (blp.canPrev) {
						%>
						<a
							href="/page/board?page=<%=blp.prevPage%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%="<"%></a>
						<%
						}
						%>
						🔸
						<%
						if (blp.blockEndNum > blp.totalPageNum) {
							for (int i = blp.blockStartNum; i <= blp.totalPageNum; i++) {
						%>
						<a
							href="/page/board?page=<%=i%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%=i%></a>
						<%
						}
						} else {
						for (int i = blp.blockStartNum; i <= blp.blockEndNum; i++) {
						%>
						<a
							href="/page/board?page=<%=i%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%=i%></a>
						<%
						}
						}
						%>
						🔸
						<%
						if (blp.canNext) {
						%>
						<a
							href="/page/board?page=<%=blp.nextPage%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%=">"%></a>
						<%
						}
						%>
						<a
							href="/page/board?page=<%=blp.totalPageNum%>&board=<%=board%>&search=<%=blp.search%>&category=<%=blp.category%>"><%=">>"%></a>
					</div>
					<div class="doWriteBox">
						<a href="/write/write.jsp?board=<%=board%>">글쓰기</a>
					</div>
				</div>
			</div>
		</div>
		<!-- 		<div id="right_mid"></div> 나중에 추가하면 dropdown하고 겹치는 이슈해결하기-->
	</div>
	<div class="bottom">
		<form action="/page/board">
			<input name="board" type="hidden" value=<%=board%>> <input
				name="category" type="hidden" value=<%=blp.category%>> <input
				name="search" height="10" width="50"> <input type="submit"
				value="검색">
		</form>

	</div>

</body>
</html>