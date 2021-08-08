package com.github.mdogdope.JDBLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBLite {
	private Connection conn = null;
	private String DBName = new String();
	
	public JDBLite(String name) throws SQLException {
		this.DBName = name;
		this.conn = DriverManager.getConnection("jdbc:sqlite:" + name);
	}
	
	public void runString(String cmd) throws SQLException {
		Statement s = this.conn.createStatement();
		s.executeUpdate(cmd);
		s.close();
	}
	
	public void close() throws SQLException {
		this.conn.close();
	}
}
