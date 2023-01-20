package com.haeun.webPage.servlet;
import java.io.IOException;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;

import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletComment")
public class ServletComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String postNum = request.getParameter("postNum");
	   Dto dto = new Dto(
			   request.getParameter("re_id"),
			   request.getParameter("comment"),
			   request.getParameter("postNum"),
			   0
			   );
	   Dao dao = new Dao();
	   dao.comment(dto);
	   response.sendRedirect("read/read.jsp?n="+postNum);
	   
	}

	
}
