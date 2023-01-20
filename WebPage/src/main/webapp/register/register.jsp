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
<link rel="stylesheet" href="../css/register.css">
<body>
	<%@ include file="../header/header.jsp"%>
	<div class="mid">
		<div class="left_mid"></div>
		<div class="mid_mid"> 
		<br>
		<h3>회원가입</h3> <hr> <br>
		<h5><span style="color:red">*</span>필수 입력 사항</h5> <hr>
		<form action="procRegister.jsp" method="get">
		<div id="registerForm">
		<div class="block"><h4><span style="color:red">*</span>아이디</h4></div>
		<div class="block"><input name="id" autofocus></div>
		<div class="block"><h4><span style="color:red">*</span>비밀번호</h4></div>
		<div class="block"><input name="password" type="password"></div>
		<div class="block"><h4><span style="color:red">*</span>비밀번호 확인</h4></div>
		<div class="block"><input name="re_password" type="password"></div>
		<div class="block"><h4><span style="color:red">*</span>생년월일</h4></div>
		<div class="block"><input name="birth" type="date"></div>
		<div class="block"><h4><span style="color:red">*</span>이메일</h4></div>
		<div class="block"><input name="email" type="email"></div>
		</div>	
		<hr> <br>
		<div id="registerButton">
		<input id="regButton" type="submit" value="가입하기">
		</div>	
		</form>
		 
		
		</div>
		<div class="rigth_mid"></div>
		</div>
</body>
</html>