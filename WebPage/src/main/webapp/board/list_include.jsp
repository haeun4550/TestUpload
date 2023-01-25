<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="postNum">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<%=posts.get(i).n%>
						<hr>
						<%
						}
						%>
					</div>
					<div class="postTitle">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<a href="../read/read.jsp?n=<%=posts.get(i).n%>&board=<%=board%>"><%=posts.get(i).title%></a>
						💬<%=posts.get(i).commentCnt%>
						<hr>
						<%
						}
						%>
					</div>
					<div class="postWriter">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<%=posts.get(i).id%>
						<hr>
						<%
						}
						%>
					</div>
					<div class="postDate">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<%=posts.get(i).dt%>
						<hr>
						<%
						}
						%>
					</div>
					<div class="postHit">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<%=posts.get(i).hit%>
						<hr>
						<%
						}
						%>
					</div>
					<div class="postRecommend">
						<%
						for (int i = 0; i < posts.size(); i++) {
						%>
						<%=posts.get(i).recommend%>
						<hr>
						<%
						}
						%>
					</div>