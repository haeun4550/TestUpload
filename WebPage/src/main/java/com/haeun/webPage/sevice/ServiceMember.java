package com.haeun.webPage.sevice;

import java.util.ArrayList;

import com.haeun.weaPage.dto.Dto;
import com.haeun.webPage.dao.Dao;

public class ServiceMember {
	Dao dao;
	public ServiceMember() {
		dao = new Dao();
	}
	public int count(String sql) {
		return dao.count(sql);
	}
	public ArrayList<Dto> memberList() {
		return dao.memberList();
	}
	public void memberUpdate(String id, String password, String password_re, String birth, String email){
		dao.memberUpdate(id, password, password_re, birth, email);
	}
}
