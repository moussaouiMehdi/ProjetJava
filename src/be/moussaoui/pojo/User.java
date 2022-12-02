package be.moussaoui.pojo;

import java.io.Serializable;

import be.moussaoui.dao.UserDAO;

public abstract class User implements Serializable {

	private static final long serialVersionUID = -8664903867854457709L;
	private String username;
	private String password;
	private int userId;
	
	
	public User() {
		
	}
	
	public User(int id, String username, String password) {
		this.username = username;
		this.password = password;
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public static User login(String username, String password) {
		return UserDAO.login(username, password);
	}
	
	public static boolean check(String username) {
		return UserDAO.check(username);
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}
	
}
