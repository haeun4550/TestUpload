package com.haeun.webPage.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;
import com.haeun.webPage.db.Db;
import com.mysql.cj.Session;

@WebServlet("/member/*")
public class ControllerMember extends HttpServlet {
	Dao dao;
	String forwardPage;

	@Override
	public void init() throws ServletException {
		dao = new Dao();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		HttpSession session = request.getSession();
		if (action != null) {
			if (action.equals("/login")) {
				String nowId = request.getParameter("id");
				String nowPw = request.getParameter("password");
				String sql = String.format("select count(*) from %s where id='%s' and password='%s'", Db.TABLE_MEMBER,
						nowId, nowPw);
				int count = dao.count(sql);
				if (count == 1) {
					System.out.println("로그인 성공");
					session.setAttribute("id", nowId);
					forwardPage = "/mainPage.jsp";
				} else {
					System.out.println("로그인 실패");
					nowId = null;
					nowPw = null;
					forwardPage = "/login/loginForm.jsp";
				}
			} else if (action.equals("/logout")) {
				session.removeAttribute("id");
				forwardPage = "/mainPage.jsp";
			}else if(action.equals("/register")) {
				int a = 0;
				String id= request.getParameter("id");
				String password = request.getParameter("password");
				String re_password = request.getParameter("re_password");
				String birth = request.getParameter("birth");
				String email = request.getParameter("email");
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
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('중복된 아이디입니다.'); location.href='/register/register.jsp'</script>");
						out.flush();
						return;
					}else {
						a=2;
					}
						
					}
					if(a==2) {
						break;
					}
				}
			}
				dao.memberUpdate(id, password, re_password, birth, email);		
				forwardPage = "/login/loginForm.jsp";

		}
			RequestDispatcher d = request.getRequestDispatcher(forwardPage);
			d.forward(request, response);
	}
	}
}

			
			
