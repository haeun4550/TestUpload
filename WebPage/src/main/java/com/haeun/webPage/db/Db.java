package com.haeun.webPage.db;

public class Db {
	static private String DB_NAME = "my_dog";
	static private String DB_ID = "root";
	static private String DB_PW = "0000";
	
	/*table들*/
	public static final String TABLE_BOARD="memberBoard";
	public static final String TABLE_COMMENT="comment";
	public static final String TABLE_MEMBER="member";
	public static final String TABLE_RECOMMEND="recommend";
	public static final String TABLE_REPORT="report";
	public static final int PAGE=5;
	public static final int PAGE_PER_BLOCK=2;
}
