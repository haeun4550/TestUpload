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
	
	
	
	
	public Dto(String re_id, String comment, String postNum, int x) {
		super();
		this.re_id = re_id;
		this.comment = comment;
		this.postNum = postNum;
	}

	public Dto(String title, String content) {
		super();
		this.title=title;
		this.content=content;
		
	}
	
	public Dto(String title, String id, String comment) {
		super();
		this.title=title;
		this.comment=comment;
		this.id=id;
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
			String reportCnt, String reportCon, String reportOption) {
		super();
		this.n = n;
		this.id = id;
		this.reportCnt = reportCnt;
		this.title = title;
		this.dt = dt;
		this.hit = hit;
		this.content = content;
		this.recommend = recommend;
		this.reportCon = reportCon;
		this.reportOption = reportOption;
	}
	
	public Dto(String n, String title, String id, String content, String dt, String hit, String recommend) {
		super();
		this.n = n;
		this.id = id;
		this.title = title;
		this.dt = dt;
		this.hit = hit;
		this.content = content;
		this.recommend = recommend;
	}
}
