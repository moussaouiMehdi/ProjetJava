package be.moussaoui.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.moussaoui.pojo.Booking;

public class BookingDAO implements DAO<Booking> {
	private Connection conn;
	
	public BookingDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Booking> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
