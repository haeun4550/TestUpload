<%@page import="com.haeun.webPage.db.Db"%>
<%@page import="com.haeun.weaPage.dto.Dto"%>
<%@page import="java.util.ArrayList"%>
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
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/board.css">
<body>
	<%
	Dao dao = new Dao();
	String currentPage = request.getParameter("page");
	int totalPageNum=0;
	int totalBlockNum=0;
	if(currentPage==null){
		currentPage="1";
	}
	ArrayList<Dto> posts = dao.postList(currentPage);
	int postCount = dao.postCount();
	if((postCount%Db.PAGE)==0){
	totalPageNum = postCount/Db.PAGE; //딱 떨어질때
	}else{
		totalPageNum = (postCount/Db.PAGE)+1; //자투리 남을때
	}
	if((totalPageNum%Db.PAGE_PER_BLOCK)==0){
		totalBlockNum = totalPageNum/Db.PAGE_PER_BLOCK; //딱 떨어질때
	}else{
		totalBlockNum = (totalPageNum/Db.PAGE_PER_BLOCK)+1; //자투리남을때
	}
	int currentBlockNum = (int)Math.ceil((double)(Integer.parseInt(currentPage))/Db.PAGE_PER_BLOCK);
// 	System.out.println(currentBlockNum);
	int blockStartNum =(currentBlockNum-1)*Db.PAGE_PER_BLOCK+1;
	int blockEndNum = currentBlockNum*Db.PAGE_PER_BLOCK;
	int nextPage = 0;
	int prevPage= 0;
	boolean canPrev = true;
	if(currentBlockNum==1){
		canPrev = false;
	}else{
		canPrev = true;
	prevPage = (currentBlockNum-1)*Db.PAGE_PER_BLOCK;
	}
	boolean canNext = true;
	if(currentBlockNum<totalBlockNum){
		canNext = true;
	nextPage = currentBlockNum*Db.PAGE_PER_BLOCK +1;
	}else{
		canNext= false;
	}
	%>
	<%@ include file="../header/header.jsp"%>
	<div class="mid">
		<div id="left_mid">
			<div id="bar"></div>
			<div id="boardList">
				자유게시판<br> 회원게시판
			</div>
		</div>
		<div id="mid_mid">
			<div id="boardTitle">
				<br>
				<h2>자유게시판</h2>
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
					<%@ include file="list_include.jsp"%>
				</div>
				<div class="mid_bottom">
					<div class="pageBlock">
						<a href=board.jsp?page=1><%="<<"%></a>
						<%
				if(canPrev){
					%>
						<a href=board.jsp?page=<%=prevPage%>><%="<"%></a>
						<%
				}
				%>
						🔸
						<%
				if(blockEndNum>totalPageNum){
					for(int i=blockStartNum;i<=totalPageNum;i++){%>
						<a href=board.jsp?page=<%=i%>><%=i%></a>
						<%
					}
				}else{
				for(int i=blockStartNum;i<=blockEndNum;i++){ %>
						<a href=board.jsp?page=<%=i%>><%=i%></a>
						<%} 
						}%>
						🔸
						<%
				if(canNext){
					%>
						<a href=board.jsp?page=<%=nextPage%>><%=">"%></a>
						<%}
						%>
					<a href=board.jsp?page=<%=totalPageNum%>><%=">>"%></a>
					</div>
					<div class="doWriteBox">
					<a href="../write/write.jsp">글쓰기</a>
					</div>
				</div>

			</div>
		</div>
		<div id="right_mid"></div>
	</div>

</body>
</html>