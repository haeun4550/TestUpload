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
	public String category;
	public BoardListProcessor(Dao dao, String currentPage, String dbBoard, String search, String category) {
		super();
		this.dao = dao;
		this.currentPage = currentPage;
		this.totalPageNum = getPageCount(dbBoard,search,category);
		this.dbBoard = dbBoard;
		this.search = search;
		this.category = category;
		getList();
		getPageBlock();
	}

	public void getList() {
		if(search==null&&category==null) { 
			posts = dao.postList(currentPage,dbBoard);
		}else if(search!=null&&category!=null&&search.equals("null")&&category.equals("null")) { 
			posts = dao.postList(currentPage,dbBoard);
		}else if(search!=null&&category.equals("null")){ 
			posts= dao.postList(currentPage,dbBoard,search); 
		}else if(search==null&&category!=null) { 
			posts = dao.postSubList(currentPage, dbBoard, category);
		}else if(search!=null&&search.equals("null")&&category!=null) {
			posts = dao.postSubList(currentPage, dbBoard, category);
		}else if(search!=null&&category!=null) {
			posts = dao.postList(currentPage, dbBoard, search, category);  
		}
	}
	
	public int getPageCount(String dbBoard, String search, String category) {
		totalPageNum = 0;
		if(search==null&&category==null) {
			postCount = dao.postCount(dbBoard);
		}else if(search!=null&&category!=null&&search.equals("null")&&category.equals("null")){
			postCount = dao.postCount(dbBoard);
		}else if(search!=null&&category.equals("null")){
			postCount = dao.postCount(dbBoard,search);	
		}else if(search==null&&category!=null) {
			postCount = dao.postSubCount(dbBoard, category);
		}else if(search!=null&&search.equals("null")&&category!=null){
			postCount = dao.postSubCount(dbBoard, category);
		}else if(search!=null&&category!=null) {
			postCount = dao.postCount(dbBoard,search,category);
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
