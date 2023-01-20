<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/login.css">
<body>
	<%@ include file="../header/header.jsp"%>
	<div class="aaaa">
<!-- 	<div class="headImg"> -->
<!-- 	<img src="../img/flower.jpeg" class="headerImg"> -->
<!-- 	</div> -->
		<div class="loginBox">
		로그인 <br>
		<form action="procLogin.jsp" method="get">
			<input name="id" placeholder="아이디" autofocus> <br> 
			<input name="password" placeholder="비밀번호"> <br>
			<input type="submit" value="로그인">
			</form> 
			<div>
			<a href="../register/register.jsp"><input type="button" value="회원가입"></a>
			</div>
		</div>
		
	</div>
</body>
</html>