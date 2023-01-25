package com.haeun.webPage.servlet;
import java.io.IOException;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;
import com.haeun.webPage.db.Db;

import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ServletWrite")
public class ServletWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   Dto dto = new Dto(
			   request.getParameter("title"),
			   request.getParameter("id"),
			   request.getParameter("content")
			   );
	   String board = request.getParameter("board");
	   String dbBoard = null;
	   Dao dao = new Dao();
	   if(board.equals("member")) {
		   dbBoard = Db.TABLE_BOARD;
	   }else if(board.equals("free")) {
		   dbBoard = Db.TABLE_FREEBOARD;
	   }
	   dao.write(dto,dbBoard);
	   response.sendRedirect("board/board.jsp?board="+board);
	   
	}

	
}
