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
<body>
	<%
	Dao dao = new Dao();
	String nowId = request.getParameter("id");
	String nowPw = request.getParameter("password");
	String sql = String.format("select count(*) from %s where id='%s' and password='%s'",
			Db.TABLE_MEMBER,nowId,nowPw);
	int count = dao.count(sql);
	if(count==1){
		System.out.println("로그인 성공");
		session.setAttribute("id",nowId);
		response.sendRedirect("../mainPage.jsp");
	}else{
		System.out.println("로그인 실패");
		nowId= null;
		nowPw = null;
		response.sendRedirect("loginForm.jsp");
	}
		
	%>
</body>
</html>