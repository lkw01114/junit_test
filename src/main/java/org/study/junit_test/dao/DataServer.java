package org.study.junit_test.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataServer {
	
	
	static final String DB_URL = "jdbc:mysql://192.168.0.205/todo_db?useSSL=no&characterEncoding=utf8";
	static final String USER = "kwangwoon";
	static final String PASSWORD = "kwangwoon";	
	
	private Connection conn;
	
	public DataServer(){
		makeConnection();
	}
	
	public void makeConnection() {

		try {
			// 1. jdbc Driver 로딩
			Class.forName("com.mysql.jdbc.Driver");

			// 2. database 접속
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// 3. Query statement 생성
			Statement statement = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Connection getConn() {
		return conn;
	}


	public boolean login(String userName, String password) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" Select Count(userName) from member \r\n");
			sb.append(" where userName =  ? \r\n");
			sb.append(" and userPass =  ? ");
			System.out.println(sb.toString());
			PreparedStatement prepStatement = conn.prepareStatement(sb.toString());
			prepStatement.setString(1, userName);
			prepStatement.setString(2, password);
			ResultSet rs = prepStatement.executeQuery();
			if(rs.next()) {
				int count = rs.getInt(1);
				System.out.println("count >> " +  count);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateTodo(String userName, String todo) {
		
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" update member \r\n");
			sb.append(" set todo_list = ? \r\n");
			sb.append(" where userName = ? ");
			System.out.println("---------  updateTodo S----------");
			System.out.println(sb.toString());
			System.out.println("---------  updateTodo E----------");			
			PreparedStatement prepStatement = conn.prepareStatement(sb.toString());
			prepStatement.setString(1, todo);
			prepStatement.setString(2, userName);		
			int result = prepStatement.executeUpdate();
			System.out.println("result >>>>>>>>>>>>>>>>>> " + result );
			if(result != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public String readTodo(String userName) {
		
		StringBuffer sb = new StringBuffer();
		sb.append(" Select todo_list from member");
		sb.append(" where userName =  ? ");
		System.out.println(sb.toString());
		PreparedStatement prepStatement;
		try {
			prepStatement = conn.prepareStatement(sb.toString());
			prepStatement.setString(1, userName);
			ResultSet rs = prepStatement.executeQuery();
			if(rs.next()) {
				String todo = rs.getString(1);
				System.out.println( " todo >> " + todo);
				return todo;
			}					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
