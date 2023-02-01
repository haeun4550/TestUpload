<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/css/header.css">
<link rel="stylesheet" href="/css/write.css">
<link rel="stylesheet" href="/css/common.css">
<body>
	<%
	String sId = (String) session.getAttribute("id");
	String board = request.getParameter("board");
	%>
	<%@ include file="/header/header.jsp"%>
	<div class="mid">
		<div class="left_mid"></div>
		<div class="mid_mid">
			<div id="boardTitle">
				<br>
				<h2>글쓰기</h2>
				<div id="bar"></div>
			</div>
			<br> <br>
			<div class="writeBox">
				<form action="/page/write">
					<input name="board" type="hidden" value=<%=board%>>
					<input name="id" type="hidden" value=<%=sId%>>
					카테고리:<select name="category">
					<option value="game">게임</option>
					<option value="animal">동물</option>
					</select>
					<div id="writeTitle">
						<input name="title" placeholder="제목을 입력해주세요" autofocus>
					</div>
					<hr>
					<div id="writeContent">
						<textarea name="content" placeholder="본문을 입력해주세요."></textarea>
					</div>
					<br>
					<div id="writeSumitButton">
						<input type="submit" value="글쓰기"
							style="background-color: #ffdb99; border-style: none; width: 60px; height: 25px">
					</div>
				</form>
			</div>

		</div>
		<div class="right_mid"></div>


	</div>

</body>
</html>