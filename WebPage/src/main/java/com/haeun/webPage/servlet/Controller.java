package com.haeun.webPage.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;
import com.haeun.webPage.db.Db;

@WebServlet("/page/*")
public class Controller extends HttpServlet {
	String forwardPage;
	Dao dao;
	String dbBoard;
	String board;
	String n;
       
	@Override
	public void init() throws ServletException {
		dao = new Dao();
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
				dao.delete(n,dbBoard);
			}else if(action.equals("/write")) {
				forwardPage="/page/board?board="+board;
				Dto dto = new Dto(
						request.getParameter("title"),
						request.getParameter("id"),
						request.getParameter("content")
						);
				dao.write(dto, dbBoard);
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
					dao.update(sql);
				}
				if (cmd != null && cmd.equals("comment")) {
					String sql = String.format("update %s set hit=hit-1 where n=%s", dbBoard,n);
					dao.update(sql);
				}
				String sql = String.format("update %s set hit=hit+1 where n=%s", dbBoard, n);
				dao.update(sql);
				Dto d = dao.read(n, dbBoard);
				ArrayList<Dto> comments = dao.commentList(n, board);
				request.setAttribute("comments", comments);
				request.setAttribute("post", d);
			}else if(action.equals("/edit")) {
				forwardPage="/edit/edit.jsp";
				request.setAttribute("post", dao.read(n, dbBoard));
			}else if(action.equals("/edit_proc")) {
				forwardPage = "/page/read?n="+n+"&board="+board;
				dao.edit(
						new Dto(request.getParameter("title"),request.getParameter("content")),
						n,
						dbBoard
						);
			}else if(action.equals("/board")) {
				String currentPage = request.getParameter("page");
				int totalPageNum=0;
				int totalBlockNum=0;
				if(currentPage==null) {
					currentPage="1";
				}
				ArrayList<Dto> posts = dao.postList(currentPage, dbBoard);
				int postCount = dao.postCount(dbBoard);
				if((postCount%Db.PAGE)==0) {
					totalPageNum = postCount/Db.PAGE;
				}else {
					totalPageNum = (postCount/Db.PAGE)+1;
				}
				if((totalPageNum%Db.PAGE_PER_BLOCK)==0) {
					totalBlockNum = totalPageNum/Db.PAGE_PER_BLOCK;
				}else {
					totalBlockNum = (totalPageNum/Db.PAGE_PER_BLOCK)+1;
				}
				int currentBlockNum = (int)Math.ceil((double)(Integer.parseInt(currentPage))/Db.PAGE_PER_BLOCK);
				int blockStartNum = (currentBlockNum-1)*Db.PAGE_PER_BLOCK+1;
				int blockEndNum = currentBlockNum*Db.PAGE_PER_BLOCK;
				int nextPage = 0;
				int prevPage = 0;
				boolean canPrev = true;
				if(currentBlockNum==1) {
					canPrev = false;
				}else {
					canPrev = true;
					prevPage = (currentBlockNum-1)*Db.PAGE_PER_BLOCK;
				}
				boolean canNext = true;
				if(currentBlockNum<totalBlockNum) {
					canNext = true;
					nextPage = currentBlockNum*Db.PAGE_PER_BLOCK+1;
				}else {
					canNext = false;
				}
				request.setAttribute("posts", posts);
				request.setAttribute("blockStartNum", blockStartNum);
				request.setAttribute("blockEndNum", blockEndNum);
				request.setAttribute("nextPage", nextPage);
				request.setAttribute("prevPage", prevPage);
				request.setAttribute("canPrev", canPrev);
				request.setAttribute("canNext", canNext);
				request.setAttribute("totalPageNum", totalPageNum);
				forwardPage = "/board/board.jsp?page="+currentPage+"&board="+board;
			}else if(action.equals("/comment")) {
				String postNum = request.getParameter("postNum");
				Dto comment = new Dto(
						request.getParameter("re_id"),
						request.getParameter("comment"),
						request.getParameter("postNum"),
						0
						);
				dao.comment(comment, board);
				String sql= String.format("update %s set commentCnt=commentCnt+1 where n=%s",dbBoard,postNum);
				dao.update(sql);
				forwardPage = "/page/read?n="+postNum+"&cmd=comment&board="+board;
			}
			request.setAttribute("board", board);
			request.setAttribute("dbBoard", dbBoard);
			RequestDispatcher d = request.getRequestDispatcher(forwardPage);
			d.forward(request, response);
		}
	}
}
