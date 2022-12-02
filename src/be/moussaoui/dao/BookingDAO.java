package be.moussaoui.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.moussaoui.pojo.Booking;

public class BookingDAO implements DAO<Booking> {
	private Connection conn;
	
	public BookingDAO() {
		this.conn = DataBaseConnection.getInstance();
	}

	@Override
	public boolean insert(Booking obj) {
		boolean success=false;
		int idCreated = 0;
		try {
			String query="INSERT INTO booking (bookingDate, gameNumber, playerId) VALUES(?,?,?)";
			PreparedStatement pstmt = (PreparedStatement) this.conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, Date.valueOf(obj.getBookingDate()));
	        pstmt.setInt(2, obj.getGame().getId());
	        pstmt.setInt(3, obj.getPlayer().getPlayerId());
	        pstmt.executeUpdate(); 
	        ResultSet res = pstmt.getGeneratedKeys();
			if (res.next()) {
				idCreated = res.getInt(1);
			}
	        if(idCreated != 0) {
	        	
	        	success=true;
	        }

		}catch(SQLException e){
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Booking obj) {
		try {
			String query="DELETE FROM booking where bookingid=?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, obj.getBookingNumber());
			pstmt.executeUpdate();
			return true;

		}catch(SQLException e){
			e.printStackTrace();
		}
		
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
