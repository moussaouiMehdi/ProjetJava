package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.User;

public class UserDAO implements DAO<User>{
	private Connection conn;
	
	
	public UserDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(User obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
