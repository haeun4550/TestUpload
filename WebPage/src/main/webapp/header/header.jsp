<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="header">
	<div class="logo">
		<h2>
			<a href="/mainPage.jsp">🌸홈페이지🌸</a>
		</h2>
	</div>
	<div class="menu">
		<div class="photo">
			<h4>Photo</h4>
		</div>
		<div class="freeBoard">
			<h4>
				<a href="/board/board.jsp?board=free">Board</a>
			</h4>
		</div>
		<div class="game">
			<h4>Game</h4>
		</div>
		<div class="myPage">
			<h4>MyPage</h4>
		</div>
	</div>
	<div class="loginForm">
		<%
		String id = (String) session.getAttribute("id");
		if (id == null) {
		%>
<!-- 		<div class="loginState"> -->
		<h4>
			<a href="/login/loginForm.jsp">Login</a>
		</h4>
		<%
		} else {
		%>
		
		<%=id%>님, 환영합니다.
		<div class="login_dropdown">
			<p onclick="dropMenu()">▼</p>
			<div style="display: none" id="drop_content">
				<a href="/login/logout.jsp">logout</a> <br>
				<a href="/myPage/myPage.jsp">마이페이지</a>
			</div>
		</div>
		<script>
			function dropMenu(){
				let click = document.getElementById("drop_content");
				if(click.style.display ==="none"){
					click.style.display = "block";
				}else{
					click.style.display = "none"
				}
			}
				
		</script>
		<%
		}
		%>
	</div>
</div>
