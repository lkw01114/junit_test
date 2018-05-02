package org.study.junit_test;

import org.junit.Assert;
import org.junit.Test;
import org.study.junit_test.dao.DataServer;
import org.study.junit_test.service.TodoService;

public class TodoTest {
	
	
	// 디비연결 테스트
	@Test
	public void testMakeConnect() {
		DataServer server = new DataServer();
		server.makeConnection();
		Assert.assertNotNull(server.getConn());
	}
	
	
	// 로그인 테스트
	@Test
	public void testLogin() {
		
		String userName = "kwangwoon";
		String password = "1234";
		
		TodoService ts = new TodoService();
		Assert.assertTrue( ts.login(userName, password) );
		//Assert.assertFalse( ts.login("11111", "AAAAA") );
		
	}
	
	@Test
	public void testAddTodo() {
		TodoService ts = new TodoService();
		ts.addTodo("kwangwoon", "blahblah");
		
		Assert.assertEquals("blahblah", ts.retrieveTodo("kwangwoon")); 
	}
	
	
	
	

}
