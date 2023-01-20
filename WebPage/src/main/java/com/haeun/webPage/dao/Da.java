package com.haeun.webPage.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Da {
	
	String DB_NAME = "my_dog";
	String DB_ID = "root";
	String DB_PW = "0000";
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	public void dbConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			st = con.createStatement();
		} catch (Exception e) {

		}
	}
	
	public void dbClose() {
		try {
			st.close();
			con.close();
			rs.close();
		} catch (Exception e) {

		}
	}
	
}
