package org.study.junit_test.service;

import org.study.junit_test.dao.DataServer;

public class TodoService {
	
	
	private DataServer server;
	
	public TodoService(){
		server = new DataServer();
	}
	
	/**
	 * 로그인
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean login(String userName, String password) {
		return server.login(userName, password);
	}
	
	/**
	 * 해당 유저에 대해서 todo를 등록한다.
	 * @param userName
	 * @param todo
	 * @return
	 */
	public boolean addTodo(String userName, String todo) {
		return server.updateTodo(userName, todo);
	}

	/**
	 * 해당 유저명에 해당하는 Todo를 반환한다.
	 * @param userName
	 * @return
	 */
	public String retrieveTodo(String userName) {
		return server.readTodo(userName);
	}
	
	
}
