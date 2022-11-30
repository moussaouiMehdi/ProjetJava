package be.moussaoui.pojo;

import java.io.Serializable;

public abstract class User implements Serializable {

	private static final long serialVersionUID = -8664903867854457709L;
	private String username;
	private String password;
	private int id;
	
	
	public User() {
		
	}
	
	public User(int id, String username, String password) {
		this.username = username;
		this.password = password;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean Login(String username, String password) {
		return false;
	}

}
