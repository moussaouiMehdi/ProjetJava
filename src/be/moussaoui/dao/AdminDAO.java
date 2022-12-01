package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.Administrator;

public class AdminDAO implements DAO<Administrator>{
	private Connection conn;
	
	public AdminDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Administrator obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Administrator obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Administrator obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Administrator find(int id) {
		return null;
	}

	@Override
	public ArrayList<Administrator> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
