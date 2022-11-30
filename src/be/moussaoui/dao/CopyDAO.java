package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.Copy;

public class CopyDAO  implements DAO<Copy>{
	private Connection conn;
	
	public CopyDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Copy find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Copy> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
