package com.haeun.webPage.dao;

import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.db.Db;

public class Dao extends Da {
	
	public int count(String query) {
		int count = 0;
		try {
			super.dbConnect();
			rs= st.executeQuery(query);
			rs.next();
			count= Integer.parseInt(rs.getString("count(*)"));
			return count;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return 0;
	}
	
	public int postCount(String board) {
		int count = 0;
		try {
			super.dbConnect();
			String sql = String.format("select count(*) from %s",board);
			rs= st.executeQuery(sql);
			rs.next();
			count= Integer.parseInt(rs.getString("count(*)"));
			return count;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return 0;
	}
	
	public int postCount(String board, String search) {
		int count = 0;
		try {
			super.dbConnect();
			String sql = String.format("select count(*) from %s where title like '%%%s%%';",board,search);
			rs= st.executeQuery(sql);
			rs.next();
			count= Integer.parseInt(rs.getString("count(*)"));
			return count;
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return 0;
	}
	
	public void memberUpdate(String id,String pw,String re_pw,String birth,String email) {
		try {
			super.dbConnect();
			String sql = String.format("insert into %s(id,password,re_password,birth,email,joinApprove,reportCnt) values('%s','%s','%s','%s','%s',0,0)",
					Db.TABLE_MEMBER,id,pw,re_pw,birth,email);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public void update(String sql) {
		try {
			super.dbConnect();
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public void delete(String n,String board) {
		try {
			super.dbConnect();
			String sql = String.format("delete from %s where n=%s",board,n);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public ArrayList<Dto> memberList(){
		ArrayList<Dto> members = new ArrayList<>();
		try {
			super.dbConnect();
			String sql = String.format("select * from %s",Db.TABLE_MEMBER);
			rs= st.executeQuery(sql);
			while(rs.next()) {
				members.add(new Dto(
						rs.getString("n"),
						rs.getString("id"),
						rs.getString("password"),
						rs.getString("re_password"),
						rs.getString("birth"),
						rs.getString("email"),
						rs.getString("joinApprove"),
						rs.getString("reportCnt")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return members;
	}
	
	public ArrayList<Dto> commentList(String n,String board){
		ArrayList<Dto> comments = new ArrayList<>();
		try {
			super.dbConnect();
			String sql = String.format("select * from %s where postNum=%s and board='%s'",Db.TABLE_COMMENT,n,board);
			rs= st.executeQuery(sql);
			while(rs.next()) {
				comments.add(new Dto(
						rs.getString("re_id"),
						rs.getString("comment"),
						rs.getString("postNum"),						
						0
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return comments;
	}
	
	public ArrayList<Dto> postList(String page,String board){
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			super.dbConnect();
			int startPost = (Integer.parseInt(page)-1)*Db.PAGE;
			String sql = String.format("select * from %s n order by n desc limit %s,%s",board,startPost,Db.PAGE);
			rs= st.executeQuery(sql);
			while(rs.next()) {
				posts.add(new Dto(
						rs.getString("n"),
						rs.getString("title"),
						rs.getString("id"),
						rs.getString("dt"),
						rs.getString("hit"),
						rs.getString("content"),
						rs.getString("recommend"),
						rs.getString("reportCnt"),
						rs.getString("commentCnt")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return posts;
	}
	
	public ArrayList<Dto> postList(String page,String board, String search){
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			super.dbConnect();
			int startPost = (Integer.parseInt(page)-1)*Db.PAGE;
			String sql = String.format("select * from %s where title like '%%%s%%' and n order by n desc limit %s,%s",board,search,startPost,Db.PAGE);
			rs= st.executeQuery(sql);
			while(rs.next()) {
				posts.add(new Dto(
						rs.getString("n"),
						rs.getString("title"),
						rs.getString("id"),
						rs.getString("dt"),
						rs.getString("hit"),
						rs.getString("content"),
						rs.getString("recommend"),
						rs.getString("reportCnt"),
						rs.getString("commentCnt")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return posts;
	}
	
	public ArrayList<Dto> memberPostList(String page,String board){
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			super.dbConnect();
			int startPost = (Integer.parseInt(page)-1)*Db.PAGE;
			String sql = String.format("select * from %s n order by n desc limit %s,%s",board,startPost,Db.PAGE);
			rs= st.executeQuery(sql);
			while(rs.next()) {
				posts.add(new Dto(
						rs.getString("n"),
						rs.getString("title"),
						rs.getString("id"),
						rs.getString("dt"),
						rs.getString("hit"),
						rs.getString("content"),
						rs.getString("recommend"),
						rs.getString("reportCnt"),
						rs.getString("commentCnt")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return posts;
	}
	
	public void write(Dto d,String board) {
		super.dbConnect();
		try {
			String sql = String.format("insert into %s(title,id,dt,hit,content,recommend,reportCnt,commentCnt) values('%s','%s',now(),0,'%s',0,0,0)",
					board,d.title,d.id,d.content);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	             
	public void comment(Dto d,String board) {
		super.dbConnect();
		try {
			String sql = String.format("insert into %s(postNum,re_id,comment,board) values('%s','%s','%s','%s')",
					Db.TABLE_COMMENT,d.postNum,d.re_id,d.comment,board);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	public void edit(Dto d,String n,String board) {
		super.dbConnect();
		try {
			String sql = String.format("update %s set title='%s', content='%s' where n=%s",
					board,d.title,d.content,n);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public Dto read(String n,String board) {
		Dto post = null;
		super.dbConnect();
		try {
			String sql = String.format("select * from %s where n=%s",board,n);
			rs= st.executeQuery(sql);
			rs.next();
			post = new Dto(
					rs.getString("n"),
					rs.getString("title"),
					rs.getString("id"),
					rs.getString("content"),
					rs.getString("dt"),
					rs.getString("hit"),
					rs.getString("recommend"),
					rs.getString("commentCnt"),
					0
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return post;
	}


public Dto selectMember(String id) {
	Dto member = null;
	super.dbConnect();
	try {
		String sql = String.format("select * from %s where id='%s'",Db.TABLE_MEMBER,id);
		rs= st.executeQuery(sql);
		rs.next();
		member = new Dto(
				rs.getString("n"),
				rs.getString("id"),
				rs.getString("password"),
				rs.getString("re_password"),
				rs.getString("birth"),
				rs.getString("email"),
				rs.getString("joinApprove"),
				rs.getString("reportCnt")
				);
	}catch(Exception e) {
		e.printStackTrace();
	}
	super.dbClose();
	return member;
}
}
