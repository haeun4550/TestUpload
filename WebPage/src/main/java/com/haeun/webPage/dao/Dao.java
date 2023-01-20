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
	
	public int postCount() {
		int count = 0;
		try {
			super.dbConnect();
			String sql = String.format("select count(*) from %s",Db.TABLE_BOARD);
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
	
	public void delete(String n) {
		try {
			super.dbConnect();
			String sql = String.format("delete from %s where n=%s",Db.TABLE_BOARD,n);
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
	
	public ArrayList<Dto> commentList(String n){
		ArrayList<Dto> comments = new ArrayList<>();
		try {
			super.dbConnect();
			String sql = String.format("select * from %s where postNum=%s",Db.TABLE_COMMENT,n);
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
	
//	public ArrayList<Dto> postList(){
//		ArrayList<Dto> posts = new ArrayList<>();
//		try {
//			super.dbConnect();
//			String sql = String.format("select * from %s",Db.TABLE_BOARD);
//			rs= st.executeQuery(sql);
//			while(rs.next()) {
//				posts.add(new Dto(
//						rs.getString("n"),
//						rs.getString("title"),
//						rs.getString("id"),
//						rs.getString("dt"),
//						rs.getString("hit"),
//						rs.getString("content"),
//						rs.getString("recommend"),
//						rs.getString("reportCnt"),
//						rs.getString("reportCon"),
//						rs.getString("reportOption")
//						));
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		super.dbClose();
//		return posts;
//	}
	
	public ArrayList<Dto> postList(String page){
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			super.dbConnect();
			int startPost = (Integer.parseInt(page)-1)*Db.PAGE;
			String sql = String.format("select * from %s n order by n desc limit %s,%s",Db.TABLE_BOARD,startPost,Db.PAGE);
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
						rs.getString("reportCon"),
						rs.getString("reportOption")
						));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return posts;
	}
	
	public void write(Dto d) {
		super.dbConnect();
		try {
			String sql = String.format("insert into %s(title,id,dt,hit,content,recommend,reportCnt) values('%s','%s',now(),0,'%s',0,0)",
					Db.TABLE_BOARD,d.title,d.id,d.content);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public void comment(Dto d) {
		super.dbConnect();
		try {
			String sql = String.format("insert into %s(postNum,re_id,comment) values('%s','%s','%s')",
					Db.TABLE_COMMENT,d.postNum,d.re_id,d.comment);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	public void edit(Dto d,String n) {
		super.dbConnect();
		try {
			String sql = String.format("update %s set title='%s', content='%s' where n=%s",
					Db.TABLE_BOARD,d.title,d.content,n);
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
	}
	
	public Dto read(String n) {
		Dto post = null;
		super.dbConnect();
		try {
			String sql = String.format("select * from %s where n=%s",Db.TABLE_BOARD,n);
			rs= st.executeQuery(sql);
			rs.next();
			post = new Dto(
					rs.getString("n"),
					rs.getString("title"),
					rs.getString("id"),
					rs.getString("content"),
					rs.getString("dt"),
					rs.getString("hit"),
					rs.getString("recommend")
					);
		}catch(Exception e) {
			e.printStackTrace();
		}
		super.dbClose();
		return post;
	}
}
