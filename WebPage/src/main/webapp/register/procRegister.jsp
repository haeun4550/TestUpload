<%@page import="com.haeun.weaPage.dto.Dto"%>
<%@page import="java.util.ArrayList"%>
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
	<%!static int a;%>
	<%
	String id = request.getParameter("id");
	String pw = request.getParameter("password");
	String re_pw = request.getParameter("re_password");
	String birth = request.getParameter("birth");
	String email = request.getParameter("email");
	Dao dao = new Dao();

	while (true) {
		int count = dao.count("select count(*) from " + Db.TABLE_MEMBER);
		if (count == 0) {
			break;
		} else {
			ArrayList<Dto> members = dao.memberList();
			for (int i = 0; i < members.size(); i++) {
		String dbId = members.get(i).id;
		if (id.equals(dbId)) {
			a = 1;
	%>
	<script>
		alert("중복된 아이디입니다.");
		history.back();
	</script>
	<%
	return;
	} else {
	a = 2;
	}
	}
	}
	if (a == 2) {
	break;
	}
	}

	dao.memberUpdate(id, pw, re_pw, birth, email);
	response.sendRedirect("../login/loginForm.jsp");
	%>


</body>
</html>