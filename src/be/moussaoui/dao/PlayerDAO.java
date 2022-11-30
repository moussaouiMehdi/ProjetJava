package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.Player;

public class PlayerDAO implements DAO<Player>{
	private Connection conn;
	
	public PlayerDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Player obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Player find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Player> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
