package com.haeun.webPage.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.board.BoardListProcessor;
//import com.haeun.webPage.dao.Dao;
import com.haeun.webPage.db.Db;
import com.haeun.webPage.sevice.Service;

@WebServlet("/page/*")
public class Controller extends HttpServlet {
	String forwardPage;
	String dbBoard;
	String board;
	String n;
	Service service;
       
	@Override
	public void init() throws ServletException {
		service = new Service();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action =request.getPathInfo();
		board = request.getParameter("board");
		n = request.getParameter("n");
		if(board.equals("member")) {
			dbBoard = Db.TABLE_BOARD;
		}else if(board.equals("free")) {
			dbBoard = Db.TABLE_FREEBOARD;
		}
		if(action!=null) {
			if(action.equals("/del")) {
				forwardPage = "/page/board?board="+board;
				service.delete(n,dbBoard);
			}else if(action.equals("/write")) {
				forwardPage="/page/board?board="+board;
				Dto dto = new Dto(
						request.getParameter("title"),
						request.getParameter("id"),
						request.getParameter("content"),
						request.getParameter("category")
						);
				service.write(dto, dbBoard);
			}else if(action.equals("/read")) {
				String cmd = request.getParameter("cmd");
				if(cmd==null) {
					forwardPage = "/read/read.jsp?n="+n+"&board="+board;
				}else if(cmd!=null&& cmd.equals("rec")) {
					forwardPage = "/read/read.jsp?cmd=rec&n="+n+"&board="+board;
				}else if(cmd!=null&& cmd.equals("comment")) {
					forwardPage = "/read/read.jsp?cmd=comment&n="+n+"&board="+board;
				}
				if (cmd != null && cmd.equals("rec")) {
					String sql = String.format("update %s set recommend=recommend+1,hit=hit-1 where n=%s", dbBoard, n);
					service.update(sql);
				}
				if (cmd != null && cmd.equals("comment")) {
					String sql = String.format("update %s set hit=hit-1 where n=%s", dbBoard,n);
					service.update(sql);
				}
				String sql = String.format("update %s set hit=hit+1 where n=%s", dbBoard, n);
				service.update(sql);
				Dto d = service.read(n, dbBoard);
				ArrayList<Dto> comments = service.commentList(n, board);
				request.setAttribute("comments", comments);
				request.setAttribute("post", d);
			}else if(action.equals("/edit")) {
				forwardPage="/edit/edit.jsp";
				request.setAttribute("post", service.read(n, dbBoard));
			}else if(action.equals("/edit_proc")) {
				forwardPage = "/page/read?n="+n+"&board="+board;
				service.edit(
						new Dto(request.getParameter("title"),request.getParameter("content"),request.getParameter("category")),
						n,
						dbBoard
						);
			}else if(action.equals("/board")) {
				String currentPage = request.getParameter("page");
				String search = request.getParameter("search");
				String category = request.getParameter("category");
				BoardListProcessor blp = service.postList(currentPage,dbBoard,search,category);
				request.setAttribute("blp", blp);
				forwardPage = "/board/board.jsp?page="+currentPage+"&board="+board+"&search="+search+"&category="+category;
//			
			}else if(action.equals("/comment")) {
				String postNum = request.getParameter("postNum");
				Dto comment = new Dto(
						request.getParameter("re_id"),
						request.getParameter("comment"),
						request.getParameter("postNum"),
						0
						);
				service.comment(comment, board);
				String sql= String.format("update %s set commentCnt=commentCnt+1 where n=%s",dbBoard,postNum);
				service.update(sql);
				forwardPage = "/page/read?n="+postNum+"&cmd=comment&board="+board;
			}else if(action.equals("/search")) {
				//TODO 검색기능추가
				forwardPage="/board/board.jsp?search=aaaa&board="+board;
			}
			request.setAttribute("board", board);
			request.setAttribute("dbBoard", dbBoard);
			RequestDispatcher d = request.getRequestDispatcher(forwardPage);
			d.forward(request, response);
		}
	}
}


