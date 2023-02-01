package com.haeun.weaPage.dto;

public class Dto {
	public String n;
	public String id;
	public String password;
	public String re_password;
	public String birth;
	public String email;
	public String joinApprove;
	public String reportCnt;
	public String title;
	public String dt;
	public String hit;
	public String content;
	public String recommend;
	public String reportCon;
	public String re_id;
	public String comment;
	public String postNum;
	public String reportOption;
	public String commentCnt;
	public String category;
	
	
	
	
	public Dto(String re_id, String comment, String postNum, int x) {
		super();
		this.re_id = re_id;
		this.comment = comment;
		this.postNum = postNum;
	}

	public Dto(String title, String content, String category) {
		super();
		this.title=title;
		this.content=content;
		this.category = category;
	}
	
	public Dto(String title, String id, String content, String category) {
		super();
		this.title=title;
		this.content=content;
		this.id=id;
		this.category = category;
	}
	
	public Dto(String n, String id, String password, String re_password, String birth, String email, String joinApprove,
			String reportCnt) {
		super();
		this.n = n;
		this.id = id;
		this.password = password;
		this.re_password = re_password;
		this.birth = birth;
		this.email = email;
		this.joinApprove = joinApprove;
		this.reportCnt = reportCnt;
	}
	
	public Dto(String n, String title, String id, String dt, String hit, String content, String recommend,
			String reportCnt, String commentCnt) {
		super();
		this.n = n;
		this.id = id;
		this.reportCnt = reportCnt;
		this.title = title;
		this.dt = dt;
		this.hit = hit;
		this.content = content;
		this.recommend = recommend;
		this.commentCnt = commentCnt;
	}
	
	public Dto(String n, String title, String id, String content, String dt, String hit, String recommend,String commentCnt, String category, int x) {
		super();
		this.n = n;
		this.id = id;
		this.title = title;
		this.dt = dt;
		this.hit = hit;
		this.content = content;
		this.recommend = recommend;
		this.commentCnt = commentCnt;
		this.category = category;
	}

	public Dto(String id, String password, String re_password, String birth, String email) {
		super();
		this.id = id;
		this.password = password;
		this.re_password = re_password;
		this.birth = birth;
		this.email = email;
	}
}
