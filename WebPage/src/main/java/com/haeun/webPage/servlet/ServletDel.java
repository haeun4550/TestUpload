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

@WebServlet("/ServletDel")
public class ServletDel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   Dao dao = new Dao();
	   dao.delete(request.getParameter("n"),request.getParameter("dbBoard"));
	   response.sendRedirect("board/board.jsp?board="+request.getParameter("board"));
	}

	
}

