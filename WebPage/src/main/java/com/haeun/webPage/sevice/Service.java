package com.haeun.webPage.sevice;

import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.board.BoardListProcessor;
import com.haeun.webPage.dao.Dao;

public class Service{
	Dao dao;
	public Service() {
		dao = new Dao();
	}
	public void delete(String n, String dbBoard) {
		dao.delete(n, dbBoard);
	}
	public void write(Dto dto, String dbBoard) {
		dao.write(dto,dbBoard);
	}
	public Dto read(String n, String dbBoard) {
		return dao.read(n, dbBoard);
	}
	public void edit(Dto dto, String n, String dbBoard) {
		dao.edit(dto, n, dbBoard);
	}
	public void update(String sql) {
		dao.update(sql);
	}
	public void comment(Dto dto, String board) {
		dao.comment(dto, board);
	}
	public ArrayList<Dto> commentList(String n, String board) {
		return dao.commentList(n, board);
	}
	public BoardListProcessor postList(String currentPage, String dbBoard,String search) {
		if(currentPage==null) {
			currentPage = "1";
		}
		BoardListProcessor blp = new BoardListProcessor(dao,currentPage,dbBoard,search);
		return blp;
	}
	
	public int postCount(String dbBoard) {
		return dao.postCount(dbBoard);
	}
	
	
	
	
	
	
	
	
}
