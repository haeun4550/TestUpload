package com.haeun.webPage.board;

import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;
import com.haeun.webPage.db.Db;

public class BoardListProcessor {
	private Dao dao;
	public ArrayList<Dto> posts;
	public int totalPageNum=0;
	public int totalBlockNum=0;
	public int currentBlockNum=0;
	public int blockStartNum = 0;
	public int blockEndNum = 0;
	public int postCount = 0;
	public int nextPage = 0;
	public int prevPage = 0;
	public boolean canPrev = true;
	public boolean canNext = true;
	public String currentPage;
	public String dbBoard;
	public String search;
	public BoardListProcessor(Dao dao, String currentPage, String dbBoard, String search) {
		super();
		this.dao = dao;
		this.currentPage = currentPage;
		this.totalPageNum = getPageCount(dbBoard,search);
		this.dbBoard = dbBoard;
		this.search = search;
		getList();
		getPageBlock();
	}
	
	public void getList() {
		if(search==null) {
		posts = dao.postList(currentPage,dbBoard);
		}else {
			posts= dao.postList(currentPage,dbBoard,search);
		}
	}
	
	public int getPageCount(String dbBoard, String search) {
		totalPageNum = 0;
		if(search==null) {
		postCount = dao.postCount(dbBoard);
		}else {
		postCount = dao.postCount(dbBoard,search);	
		}
		if((postCount%Db.PAGE)==0) {
			totalPageNum = postCount/Db.PAGE;
		}else {
			totalPageNum = postCount/Db.PAGE +1;
		}
		return totalPageNum;
	}
	
	public void getPageBlock() {
		if((totalPageNum%Db.PAGE_PER_BLOCK)==0) {
			totalBlockNum = totalPageNum/Db.PAGE_PER_BLOCK;
		}else {
			totalBlockNum = totalPageNum/Db.PAGE_PER_BLOCK+1;
		}
		currentBlockNum = (int)Math.ceil((double)(Integer.parseInt(currentPage))/Db.PAGE_PER_BLOCK);
		blockStartNum = (currentBlockNum-1)*Db.PAGE_PER_BLOCK+1;
		blockEndNum = currentBlockNum*Db.PAGE_PER_BLOCK;
		if(currentBlockNum==1) {
			canPrev = false;
		}else {
			canPrev = true;
			prevPage = (currentBlockNum-1)*Db.PAGE_PER_BLOCK;
		}
		if(currentBlockNum<totalBlockNum) {
			canNext =true;
			nextPage = currentBlockNum*Db.PAGE_PER_BLOCK+1;
		}else {
			canNext = false;
		}
	}
	
	public ArrayList<Dto> getPosts(){
		return posts;
	}
}
